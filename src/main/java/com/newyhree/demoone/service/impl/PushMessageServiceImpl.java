package com.newyhree.demoone.service.impl;

import com.newyhree.demoone.dto.OrderDTO;
import com.newyhree.demoone.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;


    @Override
    public void orderStatus(OrderDTO orderDTO) throws WxErrorException {

//        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
//        templateMessage.setTemplateId("/asdsadsads");
//        //openID
//        templateMessage.setToUser(orderDTO.getOrderId());
//
//        List<WxMpTemplateData> data = Arrays.asList(
//                new WxMpTemplateData("name", orderDTO.getBuyerName()),
//                new WxMpTemplateData("address", orderDTO.getBuyerAddress()),
//                new WxMpTemplateData("phone", orderDTO.getBuyerPhone()),
//                new WxMpTemplateData("createTime", orderDTO.getCreateTime())
//        );
//
//        templateMessage.setData(data);
//        try {
//            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
//        } catch (WxErrorException e) {
//            log.error("【模板信息】发送失败, {}", e);
//        }
    }
}
