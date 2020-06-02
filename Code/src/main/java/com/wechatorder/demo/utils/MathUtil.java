package com.wechatorder.demo.utils;

/**
 * 比较金额
 * Created by Chris on 2020/4/7.
 */
public class MathUtil {
    private static final Double MONEY_RANG = 0.01;

    /**
     * 比较2个金额是否相等：两个金额绝对值小于0.0.1时，认为相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2){
        Double result = Math.abs(d1 - d2);
        if(result < MONEY_RANG){
            return true;
        }
        else{
            return false;
        }
    }

}
