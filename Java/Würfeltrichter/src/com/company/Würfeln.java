package com.company;

public class Würfeln implements Runnable {
    public Würfeln(String name){
        spielername = name;
    }
    public String spielername;
    @Override
    public void run() {
        while (Spielorganisator.continuegame){
            int number = Spielorganisator.rnd.nextInt(10);
            Spielorganisator.gesamtgewürfelt.add(number + spielername);
            Spielorganisator.game.rundengespielt++;

            //checkifwinner
            if (Spielorganisator.gesamtgewürfelt.size() >= Spielorganisator.game.muster.length()){
                String attempt = "";
                int potentialwinnersindex = Spielorganisator.gesamtgewürfelt.size() - Spielorganisator.game.muster.length();
                for (int i = 0; i < Spielorganisator.game.muster.length(); i++) {
                    attempt += Spielorganisator.gesamtgewürfelt.get(Spielorganisator.gesamtgewürfelt.size() - Spielorganisator.game.muster.length() + i).substring(0, 1);
                }
                if (attempt.equals(Spielorganisator.game.muster)){
                    String winnername = Spielorganisator.gesamtgewürfelt.get(potentialwinnersindex).substring(1);
                    for (int i = 0; i < Spielorganisator.game.schüler.size(); i++) {
                        if(Spielorganisator.game.schüler.get(i).name.equals(winnername)){
                            Spielorganisator.game.schüler.get(i).punkte++;
                            System.out.println(winnername + " hat gewonnen!");
                        }
                    }
                }
            }
            try {
                Thread.sleep(Spielorganisator.rnd.nextInt(1000) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
