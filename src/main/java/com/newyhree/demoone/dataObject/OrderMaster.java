package com.newyhree.demoone.dataObject;


import com.newyhree.demoone.enums.OrderStatusEnum;
import com.newyhree.demoone.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
//自动更新updateTime
@DynamicUpdate
public class OrderMaster implements Serializable {

    //订单id
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //支付状态, 默认0未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();


}
