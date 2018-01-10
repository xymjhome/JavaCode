package com.reflect.demo;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
.###27.05_反射(通过反射获取带参构造方法并使用)
* Constructor
	* Class类的newInstance()方法是使用该类无参的构造函数创建对象, 如果一个类没有无参的构造函数,
	* 就不能这样创建了,可以调用Class类的getConstructor(String.class,int.class)方法获取一个指定的
	* 构造函数然后再调用Constructor类的newInstance("张三",20)方法创建对象
###27.06_反射(通过反射获取成员变量并使用)
* Field
	* Class.getField(String)方法可以获取类中的指定字段(可见的), 如果是私有的可以用getDeclaedField("name")方法获取,
	* 通过set(obj, "李四")方法可以设置指定对象上该字段的值, 如果是私有的需要先调用setAccessible(true)设置访问权限,
	* 用获取的指定的字段调用get(obj)可以获取指定对象中该字段的值
###27.07_反射(通过反射获取方法并使用)
* Method
	* Class.getMethod(String, Class...) 和 Class.getDeclaredMethod(String, Class...)方法可以获取类中的指定方法,
	* 调用invoke(Object, Object...)可以调用该方法,Class.getMethod("eat") invoke(obj) Class.getMethod("eat",int.class) invoke(obj,10)
###27.08_反射(通过反射越过泛型检查)
* A:案例演示
	* ArrayList<Integer>的一个对象，在这个集合中添加一个字符串数据，如何实现呢？
###27.09_反射(通过反射写一个通用的设置某个对象的某个属性为指定的值)
* A:案例演示
	* public void setProperty(Object obj, String propertyName, Object value){}，此方法可将obj对象中名为propertyName
	的属性的值设置为value。
 */
public class Demo_Reflect {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.reflect.demo.Person");
        //1、通过反射获取带参构造方法并使用
        Constructor constructor = clazz.getConstructor();
        Person p1 = (Person) constructor.newInstance();
        System.out.println(p1);

        Constructor constructor1 = clazz.getConstructor(String.class,int.class);
        Person p2 = (Person) constructor1.newInstance("小马",26);
        System.out.println(p2);

        //2、通过反射获取成员变量并使用
        //Field file = clazz.getField("name");//私有成员变量，获取不了
        //file.set(p2,"xiaoming");

        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);//设置允许访问权限
        field.set(p2,"xiaoming");
        System.out.println(p2);

        //3、通过反射获取方法并使用
        Method method = clazz.getMethod("play");
        method.invoke(p2);

        Method method1 = clazz.getMethod("play",String.class);
        method1.invoke(p2,p2.getName());


        //4、调用通用的设置属性方法
        setProperty(p2,"name","wangwu");
        System.out.println(p2);

    }

    public static void setProperty(Object obj,String propertyName,Object value){
        Class clazz = obj.getClass();//获取对象的所对应类的字节码文件
        try {
            Field field = clazz.getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(obj,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
