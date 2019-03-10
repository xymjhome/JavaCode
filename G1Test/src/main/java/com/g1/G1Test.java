package com.g1;

import com.g1.bean.Person;

import java.util.ArrayList;
import java.util.List;

public class G1Test {

    public static void main(String[] args) {
        System.out.println("start main");
        if (args.length < 0) {
            System.out.println("arg not enough");
        }
        int size = Integer.parseInt(args[0]);
        List<Person> lists = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Person person1 = new Person("test1", 10);
            Person person2 = new Person("test2", 20);
            lists.add(person1);
        }
        System.out.println("person size: " + lists.size());
        System.out.println(lists);

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
