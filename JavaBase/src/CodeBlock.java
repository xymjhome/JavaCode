public class CodeBlock {
    public static void test(){
        Zi z = new Zi();

    }
}

class Fu{
    static {
        System.out.println("Fu 静态代码块");
    }

    {
        System.out.println("Fu 构造代码块");
    }

    public Fu()
    {
        System.out.println("Fu 构造函数");
    }
}

class Zi extends Fu{
    static {
        System.out.println("Zi 静态代码块");
    }

    {
        System.out.println("Zi 构造代码块");
    }

    public Zi()
    {
        System.out.println("Zi 构造函数");
    }
}
