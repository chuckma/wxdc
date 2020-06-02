package com.wechatorder.demo.utils;

import com.wechatorder.demo.VO.ResultVO;

/**
 * 封装结果类：将success、error的情况都进行封装
 * Created by Chris on 2020/3/2.
 */
public class ResultVOUtil {
    /**
     * code = 0 表示成功
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    /**
     * 错误
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
