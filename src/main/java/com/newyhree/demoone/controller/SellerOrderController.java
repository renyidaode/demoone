package com.newyhree.demoone.controller;


import com.newyhree.demoone.VO.ResultVo;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.service.OrderService;
import com.newyhree.demoone.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1")Integer page,
                             @RequestParam(value = "size", defaultValue = "10")Integer size,
                             Map<String, Object>map){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage",page);
        return new ModelAndView("order/list", map);
    }


    @PostMapping("/canel")
    public ResultVo cancel(@RequestParam("orderId")String orderId) throws SellException {
            OrderDTO orderDTO = orderService.GetOne(orderId);
            if (orderDTO == null){
                log.error("【卖家端取消订单】查询不到订单");
            }

            OrderDTO result = orderService.cancel(orderDTO);
        return ResultVoUtils.success();
    }

    @GetMapping("/detail")
    public ResultVo detail(@RequestParam("orderId")String orderId) throws SellException {
        OrderDTO orderDTO = orderService.GetOne(orderId);
        return ResultVoUtils.success(orderDTO);
    }

    @PostMapping("/finish")
    public ResultVo finish(@RequestParam("orderId")String orderId) throws SellException {
        OrderDTO orderDTO = orderService.GetOne(orderId);
        if (orderDTO == null){
            log.error("【卖家端完结订单】查询不到订单");
        }

        OrderDTO result = orderService.finish(orderDTO);
        return ResultVoUtils.success();
    }
}
