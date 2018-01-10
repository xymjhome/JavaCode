import java.util.Scanner;

public class Demo_Scanner {


    public static void main(String[] args) {

        Scanner sc =  new Scanner(System.in);
        int i = sc.nextInt();//键盘输入数字的时候默认会加上\r\n
        System.out.println(i);
        String str = sc.nextLine();//读取到\r\n结束
        System.out.println("i = "+ i+"   str =  " +str);




    }
}
