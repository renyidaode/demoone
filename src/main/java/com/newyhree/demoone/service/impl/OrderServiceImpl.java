package com.newyhree.demoone.service.impl;

import com.newyhree.demoone.converter.OrderMater20rderDTOConverter;
import com.newyhree.demoone.dataObject.OrderDetail;
import com.newyhree.demoone.dataObject.OrderMaster;
import com.newyhree.demoone.dataObject.ProductInfo;
import com.newyhree.demoone.dto.CartDTO;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.enums.OrderStatusEnum;
import com.newyhree.demoone.enums.PayStatusEnum;
import com.newyhree.demoone.enums.ResultEnum;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.repository.OrderDetailRepostitory;
import com.newyhree.demoone.repository.OrderMasterRepository;
import com.newyhree.demoone.service.OrderService;
import com.newyhree.demoone.service.PayService;
import com.newyhree.demoone.service.ProductService;
import com.newyhree.demoone.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepostitory orderDetailRepostitory;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) throws SellException {

        String orderId = KeyUtils.genUniquekey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//        List<CartDTO> cartDTOList = new ArrayList<>();

        //查询商品单价，数量
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.GetOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtils.genUniquekey());
            orderDetail.setOrderId(orderId);
            //spring自带属性copy，把productInfo->orderDetail
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepostitory.save(orderDetail);

//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }

        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        //拷贝orderDto内为null的属性也拷贝到orderMaster上
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO , orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());
        orderMasterRepository.save(orderMaster);

        //减少库存
        //功能同64
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public PageImpl<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);


        List<OrderDTO> orderDTOList = OrderMater20rderDTOConverter.convert(orderMasterPage.getContent());
        //返回pageable和总数
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

    }

    @Override
    public OrderDTO GetOne(String orderId) throws SellException {
        //查询订单master
        OrderMaster orderMaster = orderMasterRepository.getOne(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.order_not_exit);
        }

        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepostitory.findByOrderId(orderId);
        if (orderDetailList.isEmpty()){
            throw new SellException(ResultEnum.orderDETAIL_not_exit);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) throws SellException {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
             log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【取消订单】更新失败， orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情");
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e-> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果支付，用户退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) throws SellException {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.UNPAID.getCode())) {
            log.error("【支付订单】订单状态不正确, orderId={}, PayStatus={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS);
        }
        //修改支付状态
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO, orderMaster);
//        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) throws SellException {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        //修改订单状态
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO, orderMaster);
//        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMater20rderDTOConverter.convert(orderMasterPage.getContent());
        //返回pageable和总数
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

    }
}
