package com.wechatorder.demo.ConnectMP;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * ====== 测试类：公众号链接（只运行一次，用于配置测试公众号时使用） =====
 * 用于链接微信测试号的：接口配置，
 * URL：http://chrissell.natapp1.cc/sell/wechat/wx.do
 * Token：chris
 *
 * 一、验证Token步骤：
 * （1）在微信测试号上填写对应的接口配置：上面的URL、Token
 * （2）运行IntelliJ中的应用
 * （3）在微信测试号上，点击提交（保存），即可通过配置
 *
 * 二、微信测试公众号，其他配置：
 * （1）JS接口安全域名
 *     填写自己的外网服务器域名，只填写域名，即：不填写http://、https://
 * （2）网页帐号 ：用于网页授权获取用户基本信息（否则访问网站时，会出现scope错误、redirect_uri不一致，code：1003）
 *     点击修改，配置自己的外网服务器域名，只填写域名，即：不填写http://、https://。（可以使用NatApp进行内网穿透）
 *
 * Created by Chris on 2020/3/9.
 */
@RequestMapping("/wechat")
@Controller
@Slf4j
public class VerifyMpToken {

    private static String WECHAT_TOKEN = "chris";
    private static String token = "chris";

    @RequestMapping(value = "/wx.do")
    public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.error("WechatController   ----   WechatController");
        System.out.println("========WechatController========= ");
        log.info("请求进来了...");

        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            // out.print(name + "=" + value);

            String log = "name =" + name + "     value =" + value;
            // log.error(log);
        }

        String signature = request.getParameter("signature");/// 微信加密签名
        String timestamp = request.getParameter("timestamp");/// 时间戳
        String nonce = request.getParameter("nonce"); /// 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串
        PrintWriter out = response.getWriter();

        if (checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 校验签名
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        System.out.println("signature:" + signature + "timestamp:" + timestamp + "nonc:" + nonce);
        String[] arr = new String[] { token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        System.out.println(tmpStr.equals(signature.toUpperCase()));
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
