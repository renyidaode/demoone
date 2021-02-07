package com.newyhree.demoone.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVo {

    @JsonProperty("id")
    private String productId;

    //商品名称
    @JsonProperty("name")
    private String productName;

    //商品价格
    @JsonProperty("price")
    private BigDecimal productPrice;

    //商品描述
    @JsonProperty("decription")
    private String productDescription;

    //商品小图
    @JsonProperty("icon")
    private String productIcon;
}
