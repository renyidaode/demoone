package com.newyhree.demoone.controller;


import com.newyhree.demoone.VO.ResultVo;
import com.newyhree.demoone.converter.OrderForm20rderDTOConverter;
import com.newyhree.demoone.dataObject.OrderDetail;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.enums.ResultEnum;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.form.OrderForm;
import com.newyhree.demoone.service.OrderService;
import com.newyhree.demoone.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) throws SellException {

        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm20rderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
             log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVoUtils.success(map);

    }
    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDetail>> list(@RequestParam("buyerOpenid")String buyerOpenid,
                                            @RequestParam(value = "page", defaultValue = "0")Integer page,
                                            @RequestParam(value = "size", defaultValue = "10")Integer size) throws SellException {
        if (StringUtils.isEmpty(buyerOpenid)){
            log.error("【查询订单列表】openId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid, pageRequest);
        return ResultVoUtils.success(orderDTOPage.getContent());
    }

    //订单详情

    //修改订单
}
