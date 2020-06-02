package com.wechatorder.demo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Chris on 2020/4/7.
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
