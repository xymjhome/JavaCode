import java.util.HashMap;
import java.util.Map;

public class Demo_Map {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();

        map.put("aa",22);
        map.put("bb",26);
        map.put("cc",25);
        map.put("dd",24);
        map.put("ee",23);

        for (Map.Entry<String,Integer>  en:map.entrySet()
             ) {
            System.out.println(en.getKey() +" == "+en.getValue());
        }
    }
}
