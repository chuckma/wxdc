package com.wechatorder.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Chris on 2020/4/16.
 */
@Data
@ConfigurationProperties(prefix = "project-url")
@Component
public class ProjectUrlConfig {
    /**
     * 微信公众平台授权url
     */
    public String wechatMpAuthorize;
    /**
     * 微信开放平台授权url
     */
    public String wechatOpenAuthorize;
    /**
     * 点餐系统
     */
    public String sell;
}
