package com.newyhree.demoone.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.enums.ResultEnum;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.service.OrderService;
import com.newyhree.demoone.service.PayService;
import com.newyhree.demoone.utils.JsonUtil;
import com.newyhree.demoone.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    //预付单返回前端调起支付
    @Override
    public PayResponse creat(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信支付】发起支付,request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付,response={}", JsonUtil.toJson(payResponse));

        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) throws SellException {
        //验证签名--sdk
        //支付的状态--sdk
        //支付金额
        //支付人

        //返回异步通知的方法
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知", JsonUtil.toJson(payResponse));

        //查询订单
        OrderDTO orderDTO = orderService.GetOne(payResponse.getOrderId());

        //判断订单是否存在
        if (orderDTO == null){
            log.error("【微信支付】异步通知，订单不存在，orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.order_not_exit);
        }

        //判断金额是否一致
        if (MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())){
            log.error("【w微信支付】异步通知,订单金额不一致,orderId={},系统金额={}，订单金额={}",
                        payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单的支付状态
        orderService.paid(orderDTO);


        //一直回调，修改完订单状态后会抛出异常
        return payResponse;
    }


    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}", refundRequest);
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}", refundResponse);

        return refundResponse;
    }
}
