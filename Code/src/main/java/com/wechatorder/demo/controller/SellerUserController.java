package com.wechatorder.demo.controller;

import com.wechatorder.demo.config.ProjectUrlConfig;
import com.wechatorder.demo.constant.CookieConstant;
import com.wechatorder.demo.constant.RedisConstant;
import com.wechatorder.demo.dataobject.SellerInfo;
import com.wechatorder.demo.enums.ResultEnum;
import com.wechatorder.demo.service.SellerService;
import com.wechatorder.demo.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户控制端：登陆、登出功能
 * Created by Chris on 2020/4/16.
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 登陆操作：
     * 先到MySQL中查找openid（存在，说明该用户有登陆权限）
     * 设置token到redis、cookie
     * @param openid
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam("openid") String openid,
                        HttpServletResponse response,
                        ModelMap map) {

        //1. openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return "common/error";
        }

        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return "redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list";

    }

    /**
     * 登出操作：
     * 根据cookie的值，到redis中删除，
     * 然后再删除cookie的值
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return "common/success";
    }

}
