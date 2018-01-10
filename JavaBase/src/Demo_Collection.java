import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Demo_Collection {
    public static void main(String[] args) {
        List ls = new ArrayList<>();
        ls.add('a');
        ls.add('a');
        ls.add('v');
        ls.add('b');
        ls.add('b');
        ls.add('c');
        System.out.println(ls);

       // ls.remove('a');
        System.out.println(ls);
        Collection lc = new ArrayList<>();
        lc.add('a');
        lc.add('a');
        lc.add('v');
        lc.add('b');
        lc.add('b');
        lc.add('c');
        System.out.println(lc);
        lc.remove('a');
        lc.remove('b');
        System.out.println(lc);


        String s = "aaaa";
        System.out.println(s.length());


        //去除集合中的重复元素
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        list.add("c");
        list.add("c");

        getSingleList(list);

        System.out.println(list);
    }

    //方法参数内传递List是引用类型，所以在方法内改变List，main函数中也会改变
    private static void getSingleList(List<String> list) {
        HashSet<String> hs = new HashSet<>();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);
    }

}
