package com.company;

public class Schüler extends Thread {
    public String name;
    public int punkte = 0;


    public Schüler(String n){
        name = n;
    }

    public void start() {
        Thread t = new Thread(new Würfeln(name));
        t.start();
    }
}
