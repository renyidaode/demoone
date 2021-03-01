package com.newyhree.demoone.utils;

import java.util.Random;

public class KeyUtils {

    //

    public static String genUniquekey(){
        Random random = new Random();


        Integer number = random.nextInt(900000) +100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
