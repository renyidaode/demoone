package com.newyhree.demoone.controller;


import com.lly835.bestpay.model.PayResponse;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.enums.ResultEnum;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.service.OrderService;
import com.newyhree.demoone.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId,
                               @RequestParam("returnUrl")String returnUrl,
                               Map<String, Object>map) throws SellException {

    //查询订单
        OrderDTO orderDTO = orderService.GetOne(orderId);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
    //发起支付
        PayResponse payResponse = payService.creat(orderDTO);

        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }
}
