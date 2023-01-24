package com.company;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[] c = Felder.erzeugeCharFeld();

        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
        System.out.println();
        for(char i : c){
            System.out.print(i + " ");
        }
    }
    public static class Felder{
        public static char[] erzeugeCharFeld(){
            char[] c = new char[26];
            for (int i = 0; i < 26; i++) {
                c[i] = (char)(i + 65);
            }
            return c;
        }
    }
}
