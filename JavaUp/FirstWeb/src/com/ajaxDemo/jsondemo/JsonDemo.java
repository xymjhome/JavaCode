package com.ajaxDemo.jsondemo;


import com.ajaxDemo.domain.Province;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/20 0020.
 */
public class JsonDemo {
    @Test
    //数组
    public void demo1(){
        String[] strArr = {"aa","bb","cc","dd"};
        JSONArray jsonArray = JSONArray.fromObject(strArr);
        System.out.println(jsonArray);


    }

    @Test
    //list
    public void demo2(){
        List<String> list=new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");

        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray);

//        JSONObject jsonObject = JSONObject.fromObject(list);
//        System.out.println(jsonObject);
    }

    @Test
    //bean
    public void demo3(){
        Province province = new Province();
        province.setName("北京");
        province.setProvinceid(1);

        JSONObject jsonObject = JSONObject.fromObject(province);

        System.out.println(jsonObject);
    }

    @Test
    //map
    public void demo4(){
        Map<String, Object> map=new HashMap<>();
        map.put("username", "tom");
        map.put("age", 18);

        JSONObject jsonObject = JSONObject.fromObject(map);

        System.out.println(jsonObject);
    }

}
