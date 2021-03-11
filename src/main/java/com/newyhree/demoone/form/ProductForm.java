package com.newyhree.demoone.form;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //商品库存
    private Integer productStock;

    //商品描述
    private String productDescription;

    //商品小图
    private String productIcon;

    //商品状态 商品状态,0正常1下架
    private Integer productStatus;

    //商品编号
    private Integer categoryType;

}
