package com.company;

public class Arbeite implements Runnable{
    @Override
    public void run() {
        System.out.println("Roboter gestartet");
        while (Sortieren.alleTeile.size() > 0){
            try{
                Teil t = Sortieren.alleTeile.get(0);
                Sortieren.alleTeile.remove(0);

                if(Sortieren.currentRegal.currentgewicht + t.gewicht <= Sortieren.currentRegal.maximalgewicht){
                    Sortieren.currentRegal.teile.add(t);
                    Sortieren.currentRegal.currentgewicht += t.gewicht;
                }
                else{
                    Sortieren.alleRegale.add(Sortieren.currentRegal);
                    Sortieren.currentRegal = new Regal();
                    System.out.println("Regal gefüllt");
                }
            } catch (Exception e){ }
        }
        Sortieren.geleert = true;
    }
}
