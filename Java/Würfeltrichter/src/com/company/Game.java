package com.company;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Schüler> schüler;

    public String muster;
    public int rundengespielt;

    public Game(List<Schüler> s, String m){
        schüler = new ArrayList<Schüler>();
        for (int i = 0; i < s.size(); i++) {
            schüler.add(s.get(i));
        }
        muster = m;
        rundengespielt = 0;
    }
}
