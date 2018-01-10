import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo_Regex {
    public static void main(String[] args) {
        String s = "abvabv13245566211nn,sagjl;kj13261625211";

        String regx = "1[3578]\\d{9}";
        Pattern pat = Pattern.compile(regx);
        Matcher m = pat.matcher(s);

        System.out.println(m.matches());//从第一个字符比较，false
        //System.out.println(m.find());
        while (m.find())
            System.out.println(m.group());


        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(100));
        }
    }
}
