package com.newyhree.demoone.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetail {


    @Id
    private String detailId;

    //订单Id
    private String orderId;

    //商品Id
    private String productId;

    //商品名称
    private String productName;

    //商品单价
    private BigDecimal productPrice;

    //数量
    private Integer productQuantity;

    //小图
    private String productIcon;
}
