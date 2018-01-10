import java.util.Calendar;
import java.util.Scanner;

public class DemoCalendar {
    public static void main(String[] args) {
        while(true)
        {
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            int time = Integer.parseInt(s);

            boolean isRun = getRunYear(time);
            System.out.println(isRun);
        }
    }

    private static boolean getRunYear(int time) {
        Calendar c = Calendar.getInstance();
        c.set(time,2,1);
        c.add(Calendar.DAY_OF_MONTH,-1);

        return c.get(Calendar.DAY_OF_MONTH) == 29;
    }
}
