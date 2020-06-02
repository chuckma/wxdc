package com.wechatorder.demo.constant;

/**
 * Redis配置常量
 * Created by Chris on 2020/4/16.
 */
public interface RedisConstant {
    String TOKEN_PREFIX = "token_%s";
    Integer EXPIRE = 7200;  //2h
}
