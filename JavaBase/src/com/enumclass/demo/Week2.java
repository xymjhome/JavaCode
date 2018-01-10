package com.enumclass.demo;

public enum Week2 {
    MON("星期一"),TUR("星期二"),THR("星期三");

    private String name ;
    private Week2(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
