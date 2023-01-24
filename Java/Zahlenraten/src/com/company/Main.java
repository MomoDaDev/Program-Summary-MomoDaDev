package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("****************************************");
        System.out.println("         ZUFALLSZAHL RATEN");
        System.out.println("   Sie muessen eine zufaellige Zahl");
        System.out.println("     zwischen 1 und 1.000 raten");
        System.out.println("  Dabei haben Sie maximal 10 Versuche");
        System.out.println("Nach den 10 Versuchen haben Sie verloren !");
        System.out.println("****************************************");
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int num = random.nextInt(1000) + 1;
        System.out.println();
        boolean win = false;
        int guess;

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.print(" Ihr " + (i + 1) + ". Versuch:  ");
            guess = input.nextInt();
            if (guess > num){
                System.out.println("Die Zahl war zu hoch.");
            }
            if (guess < num){
                System.out.println("Die Zahl war zu niedrig.");
            }
            if (guess == num){
                win = true;
                break;
            }
        }
        System.out.println();
        if(win){
            System.out.println("**********************************");
            System.out.println("       Herzlichen Glueckwunsch");
            System.out.println("         Sie haben gewonnen");
            System.out.println("**********************************");
        }
        else{
            System.out.println("**********************************");
            System.out.println("             Game over");
            System.out.println("         Sie haben verloren");
            System.out.println("**********************************");
        }
    }
}
