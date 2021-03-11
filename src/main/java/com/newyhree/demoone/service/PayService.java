package com.newyhree.demoone.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.exception.SellException;

public interface PayService {


    PayResponse creat(OrderDTO orderDTO);

    PayResponse notify(String notifyData) throws SellException;

    RefundResponse refund(OrderDTO orderDTO);
}
