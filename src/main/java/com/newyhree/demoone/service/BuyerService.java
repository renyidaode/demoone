package com.newyhree.demoone.service;

import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;

public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String buyerOpenid, String orderId) throws SellException;


    //取消订单
    OrderDTO cancelOrder(String buyerOpenid, String orderId) throws SellException;
}
