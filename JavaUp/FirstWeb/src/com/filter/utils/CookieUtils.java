package com.filter.utils;

import javax.servlet.http.Cookie;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
public class CookieUtils {

    public static Cookie getCookieByName(String name, Cookie[] cookies){
        if (cookies != null && name != null) {
            for (Cookie c : cookies) {
                System.out.println(c.getName());
                if (name.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }
}
