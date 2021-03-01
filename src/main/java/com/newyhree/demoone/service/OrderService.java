package com.newyhree.demoone.service;

import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.exception.SellException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO) throws SellException;

    //查询订单列表
    List<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    //查询单个订单
    OrderDTO findOne(Integer orderId);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //支付订单
    OrderDTO paid(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

}
