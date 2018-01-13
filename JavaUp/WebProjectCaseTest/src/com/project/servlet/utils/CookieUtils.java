package com.project.servlet.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
   public static Cookie getCookieByName(String name,Cookie[] cookies){
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
