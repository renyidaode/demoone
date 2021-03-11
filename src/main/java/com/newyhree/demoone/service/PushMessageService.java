package com.newyhree.demoone.service;

import com.newyhree.demoone.dto.OrderDTO;
import me.chanjar.weixin.common.exception.WxErrorException;

public interface PushMessageService {


    //订单状态消息变更
    void orderStatus(OrderDTO orderDTO) throws WxErrorException;
}
