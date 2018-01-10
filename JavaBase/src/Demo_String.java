import java.awt.*;

public class Demo_String {
    public static void main(String[] args) {

        Frame f = new Frame("my frame");
        f.setVisible(true);

        byte[] arr = {97,98,99,100};
        String str = new String(arr);
        System.out.println(str);


        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        String s3 = s1 + s2;
        System.out.println(s3);

        String s11 = new String("abc");
        String s22 = "abc";
        System.out.println(s11==s22);
        System.out.println(s11.equals(s22));

        System.out.println(String.valueOf(100));
    }
}
