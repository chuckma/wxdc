package com.wechatorder.demo.controller;

/**
 * 该类用于测试微信网页跳转、获取openid等功能
 * Created by Chris on 2020/3/8.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ===========    测试类   ===========
 * 该类仅用于测试公众平台，是否能够获取openid（用手工的方式获取）
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class TestWeChatController {
    /**
     * 测试URL：
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx95022e7cf64fc8ab&redirect_uri=http://chrissell.natapp1.cc/sell/weixin/auth&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
     * @param code
     */
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx95022e7cf64fc8ab&secret=42753b826eef0e3e1fd1c8465395fb73&code="
                + code
                + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
