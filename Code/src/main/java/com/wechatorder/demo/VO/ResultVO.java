package com.wechatorder.demo.VO;

import java.io.Serializable;

/**
 * http请求返回的最外层对象
 * Created by Chris on 2020/3/2.
 */
//public class ResultVO<T>{
//===== 用于测试redis缓存功能 =====
public class ResultVO<T> implements Serializable {
    //测试redis缓存功能：必须添加序列号，否则会出现redis无法序列化数据的问题
    //一般来说：前端经常使用序列化，传输数据
    private static final long serialVersionUID = -108181729158963706L;

    //错误代码
    private Integer code;
    //提示信息
    private String msg;
    //具体内容
    private T data;

    public ResultVO(){

    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
