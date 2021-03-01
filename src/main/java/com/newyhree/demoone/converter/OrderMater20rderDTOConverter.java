package com.newyhree.demoone.converter;

import com.newyhree.demoone.dataObject.OrderMaster;
import com.newyhree.demoone.dto.OrderDTO;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMater20rderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMastersList){

        return orderMastersList.stream().map(e->
                convert(e)).collect(Collectors.toList());
    }
}
