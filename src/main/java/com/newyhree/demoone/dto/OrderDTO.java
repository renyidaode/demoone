package com.newyhree.demoone.dto;

import com.newyhree.demoone.dataObject.OrderDetail;
import com.newyhree.demoone.enums.OrderStatusEnum;
import com.newyhree.demoone.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    //订单id
    private String orderId;

    //买家名字
    private String buyerName;

    //卖家电话
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信openid
    private String buyerOpenid;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态, 默认0为新下单
    private Integer orderStatus;

    //支付状态, 默认0未支付
    private Integer payStatus;

    //与数据库匹配数据时忽略此数据
//    @Transient
    List<OrderDetail> orderDetailList;
}
