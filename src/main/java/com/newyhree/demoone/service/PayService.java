package com.newyhree.demoone.service;

import com.lly835.bestpay.model.PayResponse;
import com.newyhree.demoone.dto.OrderDTO;

public interface PayService {


    PayResponse creat(OrderDTO orderDTO);
}
