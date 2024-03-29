package com.newyhree.demoone.enums;

import lombok.Getter;

@Getter
public enum ResultEnum{


    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    product_stock_error(11,"库存不足"),
    order_not_exit(12, "订单不存在"),
    orderDETAIL_not_exit(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS(17,"订单支付状态不正确"),
    CART_EMPTY(18, "购物车不能为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    WECHA_MP_ERROR(20,"微信公众号方面错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"微信支付异步通知金额校验不通过"),
    PRODUCT_STATUS_ERROR(22, "商品状态不正确"),
    LOGIN_FAIL(25, "登录失败，登录信息不正确"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
