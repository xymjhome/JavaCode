package com.crm.test;
public class SingleDemo{
    private SingleDemo(){

    }

    private static volatile SingleDemo instance = null;

    public static SingleDemo getInstance() {
        if (instance == null) {
            synchronized (SingleDemo.class) {
                if (instance == null) {
                    instance = new SingleDemo();
                }
            }
        }
        return instance;
    }
    //    private static class SingleHolder{
//        private static SingleDemo instance = new SingleDemo();
//    }
//
//    public static SingleDemo getInstance(){
//        return SingleHolder.instance;
//    }
}
