package com.productcurd.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getPid(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    public static String getToken(){
        return getPid();
    }

    public static void main(String[] args) {
        System.out.println(getPid());
    }
}
