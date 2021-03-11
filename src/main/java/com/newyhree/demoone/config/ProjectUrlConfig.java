package com.newyhree.demoone.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "projecturl")
public class ProjectUrlConfig {

    //微信公众平台授权url

    public String wechatMpAuthorize;


    //微信开放平台授权url
    public String wechatOpenAuthorize;


    //项目url

    public String sell;
}
