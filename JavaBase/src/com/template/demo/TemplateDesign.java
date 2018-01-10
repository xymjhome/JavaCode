package com.template.demo;

public class TemplateDesign {
    public static void main(String[] args) {
        DemoCode demoCode = new DemoCode();
        long time = demoCode.getUseTime();//直接调用继承的父类方法
        System.out.println(time);
    }
}

//计算一段程序运行时间的模板抽象类，模板中真正测试的方法放在子类中进行实现；  即所谓的模板设计模式
abstract class GetTime{
    public final long getUseTime(){
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        return  end - start;
    }

   abstract  public void code();
}

//子类实现模板中具体的测试方法，但是模板方法不需要子类实现，子类直接继承父类使用即可，所以需要把此方法置为final
class DemoCode extends GetTime{

    @Override
    public void code() {
        for (int i = 0; i < 100000000; i++) {
            System.out.println("*");
        }
    }
}
