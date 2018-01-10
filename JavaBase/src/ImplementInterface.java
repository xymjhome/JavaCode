import java.awt.event.KeyAdapter;

public class ImplementInterface implements Demo_Interface{

    //一个类实现一个接口，需要实现里面的所有方法
    @Override
    public void test1() {

    }

    @Override
    public void test2() {

    }
}

abstract class Test implements Demo_Interface{
    //抽象类可以不完全实现

    @Override
    public void test1() {

    }
}
