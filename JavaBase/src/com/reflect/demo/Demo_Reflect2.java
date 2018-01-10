package com.reflect.demo;

import java.lang.reflect.Method;
import java.util.ArrayList;

/*

###27.08_反射(通过反射越过泛型检查)
* A:案例演示
	* ArrayList<Integer>的一个对象，在这个集合中添加一个字符串数据，如何实现呢？
	*
	*

    泛型只在编译期有效，在运行期会被擦除掉
 */
public class Demo_Reflect2 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(111);
        list.add(311);
        list.add(333);

        Class clazz = Class.forName("java.util.ArrayList");
        Method method = clazz.getMethod("add",Object.class);
        method.invoke(list,"acbe");
        System.out.println(list);
    }
}
