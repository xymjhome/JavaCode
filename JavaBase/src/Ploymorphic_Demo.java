public class Ploymorphic_Demo {
    public static void main(String[] args) {
        A a = new B();
        a.show();

        B b = new C();
        b.show();
    }
}

class A{
    public void show()
    {
        System.out.println("AAAA");
        show2();
    }
    public void show2()
    {
        System.out.println("A show2");
    }
}

class B extends A{
    public void show2()
    {
        System.out.println("B show2");
    }
}
class C extends B{
    public void show()
    {
        System.out.println("Cccccc");
       super.show();
    }
    public void show2()
    {
        System.out.println("C show2");
    }
}