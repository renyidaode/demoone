package com.newyhree.demoone.service.impl;

import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.enums.ResultEnum;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.service.BuyerService;
import com.newyhree.demoone.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String buyerOpenid, String orderId) throws SellException {
        return checkOrderOwner(buyerOpenid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String buyerOpenid, String orderId) throws SellException {
        OrderDTO orderDTO = checkOrderOwner(buyerOpenid, orderId);
        if (orderDTO==null){
            log.error("【取消订单】查不到该订单, orderId={}", orderId);
            throw new SellException(ResultEnum.orderDETAIL_not_exit);
        }
        return null;
    }

    private OrderDTO checkOrderOwner(String buyerOpenid, String orderId) throws SellException {
        OrderDTO orderDTO = orderService.GetOne(orderId);
        if (orderDTO == null){
            return null;
        }
        //判断该是否属于自己订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(buyerOpenid)){
            log.error("【查询订单失败】订单OpenID不一致， openId={}, orderDTO={}", buyerOpenid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
