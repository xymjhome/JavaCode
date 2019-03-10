package com.store.myconventer;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyConventer implements Converter {
    @Override
    public Object convert(Class aClass, Object o) {
        // Class 要装成的类型
        // Object 页面上传入的值

        //将object 转成 date
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        try {
            Date date = format.parse((String) o);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
