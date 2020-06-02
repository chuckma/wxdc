package com.wechatorder.demo.controller;

import com.wechatorder.demo.config.ProjectUrlConfig;
import com.wechatorder.demo.enums.ResultEnum;
import com.wechatorder.demo.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信公众号实际的控制类
 *
 * 一、获取用户的openid步骤（重点）
 * （0）用户测试的URL：
 *      （直接跳转到百度）
 *      http://chrissell.natapp1.cc/sell/wechat/authorize?returnUrl=http://www.baidu.com
 *      （微信客户端打开的链接：跳转到虚拟机上的工程，后端代码是自己IntelliJ的工程 --- 应该使用这个URL）
 *      http://chrissell.natapp1.cc/sell/wechat/authorize?returnUrl=http://sell.com
 *
 * （1）用户直接在浏览器输入上面的URL，跳转到百度
 *
 * （2）调用方法的流程：浏览器输入URL --> authorize() --> wxMpService.oauth2buildAuthorizationUrl() --> userInfo()
 *
 * （3）原理：浏览器将参数 returnUrl 传到服务器上 -->
 *            使用 authorize（）获取该参数 -->
 *            构造网页授权url，含微信的网页授权部分，使用wxMpService.oauth2buildAuthorizationUrl（）构造 -->
 *            当用户同意授权后，会回调所设置的url并把authorization code传过来，然后用这个code获得access token openid -->
 *            最后跳转到目标网页=returnUrl
 *
 * Created by Chris on 2020/3/9.
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    //微信公众号
    @Autowired
    private WxMpService wxMpService;
    //微信开放平台
    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 用户测试的URL：（直接跳转到百度）
     * http://chrissell.natapp1.cc/sell/wechat/authorize?returnUrl=http://www.baidu.com
     *
     * 跳转之后的百度页面的URL（会添加该用户的openid）：https://www.baidu.com/?openid=olGbLwMfCOJHBH-NYAfBMn6UqYKs
     *
     * @param returnUrl
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        //1、配置

        //2、调用方法
        String url = "http://chrissell.natapp1.cc/sell/wechat/userInfo";
        //构造网页授权url，含微信的网页授权部分 --- 可以对url进行编码：utf-8
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE
                , URLEncoder.encode(returnUrl));
        return  "redirect:" + redirectUrl;
    }

    /**
     * 获取用户信息：openId
     * @param code
     * @param returnUrl
     * @return
     */
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            //当用户同意授权后，会回调所设置的url并把authorization code传过来，然后用这个code获得access token openid
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}",  e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
