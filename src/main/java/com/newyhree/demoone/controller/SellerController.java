package com.newyhree.demoone.controller;


import com.newyhree.demoone.VO.ResultVo;
import com.newyhree.demoone.constant.CookieConstant;
import com.newyhree.demoone.constant.RedisConstant;
import com.newyhree.demoone.dataObject.SellerInfo;
import com.newyhree.demoone.enums.ResultEnum;
import com.newyhree.demoone.exception.SellException;
import com.newyhree.demoone.service.SellerService;
import com.newyhree.demoone.utils.CookieUtil;
import com.newyhree.demoone.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ResultVo login(@RequestParam("openId")String openId,
                          HttpServletResponse httpServletResponse) throws SellException {

        //拿openID去与数据库匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenId(openId);
        if (sellerInfo == null){
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }
        //设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openId, expire, TimeUnit.SECONDS);
        //设置token至cookie
        CookieUtil.set(httpServletResponse, CookieConstant.TOKEN, token,expire );

        return ResultVoUtils.success();
    }


    @GetMapping("/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response){

        //从cookie查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null){
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie));
        }
        //清除cookie
        CookieUtil.set(response, CookieConstant.TOKEN, null, 0);

    }
}
