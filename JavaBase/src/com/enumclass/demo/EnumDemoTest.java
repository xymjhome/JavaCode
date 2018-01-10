package com.enumclass.demo;

public class EnumDemoTest {
    public static void main(String[] args) {
        //demo1();

//        demo2();
        Week3 w3 = Week3.MON;
        w3.show();
    }

    public static void demo2() {
        Week2 w2 = Week2.MON;
        System.out.println(w2.getName());
    }

    public static void demo1() {
        Week1 w1 = Week1.MON;
        System.out.println(w1);
    }
}
