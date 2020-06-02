package com.wechatorder.demo.aspect;

import com.wechatorder.demo.constant.CookieConstant;
import com.wechatorder.demo.constant.RedisConstant;
import com.wechatorder.demo.exception.SellerAuthorizeException;
import com.wechatorder.demo.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 使用AOP，在功能跳转页面时，验证用户
 * （这部分做不了，没有服务号）
 * Created by Chris on 2020/4/16.
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.wechatorder.demo.controller.Seller*.*(..))" +
            "&& !execution(public * com.wechatorder.demo.controller.SellerUserController.*(..))")
    public void verify(){}

    /**
     * 如果将 切面：@Before("verify()") 打开，就可以开启登陆服务
     * （但是，这部分做不了，没有服务号）
     */
//    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
