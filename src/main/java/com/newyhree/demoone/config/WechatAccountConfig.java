package com.newyhree.demoone.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {


    //公众平台Id
    private String mpAppId;
    //公众平台密钥
    private String mpAppSecret;
    //开发平台Id
    private String openAppId;
    //开放平台密钥
    private String openAppSecret;



    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    //微信支付异步通知地址
    private String notifyUrl;
}
