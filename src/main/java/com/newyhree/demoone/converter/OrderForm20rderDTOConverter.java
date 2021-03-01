package com.newyhree.demoone.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.newyhree.demoone.dataObject.OrderDetail;
import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.enums.ResultEnum;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm20rderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) throws SellException {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

         orderDTO.setBuyerName(orderForm.getBuyerName());
         orderDTO.setBuyerPhone(orderForm.getBuyerPhone());
         orderDTO.setBuyerOpenid(orderForm.getBuyerOpenid());
         orderDTO.setBuyerAddress(orderForm.getBuyerAddress());
//        BeanUtils.copyProperties(orderDTO, orderForm);


        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误， string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
