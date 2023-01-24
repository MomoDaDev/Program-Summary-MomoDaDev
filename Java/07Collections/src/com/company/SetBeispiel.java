package com.company;

import java.util.HashSet;

public class SetBeispiel {
    public static void test(){
        HashSet hashSet = new HashSet();

        String a = "a";
        String b = a;
        String c = "c";
        Integer integer1 = new Integer("30");
        Integer integer2 = new Integer("1");

        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(c);
        hashSet.add(integer1);
        hashSet.add(integer2);

        for (int i = 0; i < hashSet.size(); i++) {
            System.out.println(hashSet.toArray()[i]);
        }
    }
}
