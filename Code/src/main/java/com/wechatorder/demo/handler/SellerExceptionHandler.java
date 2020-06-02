package com.wechatorder.demo.handler;

import com.wechatorder.demo.VO.ResultVO;
import com.wechatorder.demo.config.ProjectUrlConfig;
import com.wechatorder.demo.exception.ResponseBankException;
import com.wechatorder.demo.exception.SellException;
import com.wechatorder.demo.exception.SellerAuthorizeException;
import com.wechatorder.demo.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 卖家登陆异常处理
 * Created by Chris on 2020/4/16.
 */
@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://chrissell.natapp1.cc/sell/wechat/qrAuthorize?returnUrl=http://chrissell.natapp1.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public String handlerAuthorizeException(ModelMap map) {
        return "redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    //改成403
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException() {

    }
}
