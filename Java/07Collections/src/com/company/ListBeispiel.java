package com.company;

import java.util.*;

public class ListBeispiel {
    public static <T> void test(){
        ArrayList mylist = new ArrayList();

        String a = "a";
        String b = a;
        String c = "c";
        Integer integer1 = new Integer("30");
        Integer integer2 = new Integer("1");

        mylist.add(a);
        mylist.add(b);
        mylist.add(c);
        mylist.add(integer1);
        mylist.add(integer2);

        for (int i = 0; i < mylist.size(); i++) {
            System.out.println(mylist.get(i));
        }
    }
}
