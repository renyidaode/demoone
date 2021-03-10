package com.newyhree.demoone.form;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.logging.Logger;

@Data
public class OrderForm {

    //买家名字
    @NotEmpty(message = "姓名必填")
    private String buyerName;

    //卖家电话
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信openid
    private String buyerOpenid;

    private String items;
}
