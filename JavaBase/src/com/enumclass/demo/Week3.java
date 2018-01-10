package com.enumclass.demo;

public enum Week3 {
    MON("星期一"){
        @Override
        public void show() {
            System.out.println(this.getName());
        }
    },TUR("星期二"){
        @Override
        public void show() {
            System.out.println(this.getName());
        }
    },THR("星期三"){
        @Override
        public void show() {
            System.out.println(this.getName());
        }
    };

    private String name ;
    private Week3(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public void show();
}
