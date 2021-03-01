package com.newyhree.demoone.service.impl;

import com.newyhree.demoone.dataObject.OrderDetail;
import com.newyhree.demoone.dataObject.ProductInfo;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.enums.ResultEnum;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.service.OrderService;
import com.newyhree.demoone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) throws SellException {

        //查询商品单价，数量
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
        }


        //计算总价

        //写入订单数据库

        //减少库存



        return null;
    }

    @Override
    public List<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO findOne(Integer orderId) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }
}
