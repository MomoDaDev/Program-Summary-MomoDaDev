package com.company;

import java.util.*;

public class Regal {
    public int maximalgewicht = 300000; //Gramm
    public List<Teil> teile;
    public double currentgewicht;

    public Regal(){
        teile = Collections.synchronizedList(new LinkedList<Teil>());
        currentgewicht = 0;
    }
}
