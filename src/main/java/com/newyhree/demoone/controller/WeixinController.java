package com.newyhree.demoone.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
        log.info("");
        log.info("code={}", code);


//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx795cc0b3603de1be&secret=8ac79aa7d8d05d7f1b4b991616aa56c8&code="+ code +"&grant_type=authorization_code";
        String url = "http://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx795cc0b3603de1be&secret=8ac79aa7d8d05d7f1b4b991616aa56c8";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
