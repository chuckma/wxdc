package com.wechatorder.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信公众号配置的属性:
 * 读取application.yml文件中的wechat配置的数据
 * Created by Chris on 2020/3/9.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    //公众平台的appId
    private String mpAppId;
    //公众平台的appSecret
    private String mpAppSecret;
    //开放平台的id
    private String openAppId;
    //开放平台的密钥
    private String openAppSecret;
    //商户号
    private String mchId;
    //商户密钥
    private String mchKey;
    //商户证数路径
    private String keyPath;
    //微信支付异步通知的地址
    private String notifyUrl;
    //微信模板id
    private Map<String, String> templateId;
}
