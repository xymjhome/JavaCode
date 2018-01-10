public class InnerClass_Demo {
    public static void main(String[] args) {
        Outer.Inner oi = new Outer().new Inner();
        oi.show();

        Outer2.Inner2 oi2 = new Outer2.Inner2();
        oi2.show();

        Outer3.Inner3.show();
    }
}

class Outer{
    public int num = 10;
    class Inner{
        public int num = 20;
        public void show()
        {
            int num = 30;
            System.out.println(num);
            System.out.println(this.num);
            System.out.println(Outer.this.num);
        }
    }
}
class Outer2{
    public int num = 10;
    static class Inner2{
        public int num = 20;
        public void show()
        {
            int num = 30;
            System.out.println(num);
            System.out.println(this.num);
            System.out.println(new Outer2().num);
        }
    }
}
class Outer3{
    public int num = 10;
    static class Inner3{
        public int num = 20;
        public static void show()
        {
            int num = 30;
            System.out.println(num);
            System.out.println(new Inner3().num);
            System.out.println(new Outer3().num);
        }
    }
}
