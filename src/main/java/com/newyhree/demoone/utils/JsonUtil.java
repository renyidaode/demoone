package com.newyhree.demoone.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    //打印json
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
