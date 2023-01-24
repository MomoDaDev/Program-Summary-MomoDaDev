package com.company;

public class Roboter {
    public void starte(){
        Thread t = new Thread(new Arbeite());
        t.start();
    }
}
