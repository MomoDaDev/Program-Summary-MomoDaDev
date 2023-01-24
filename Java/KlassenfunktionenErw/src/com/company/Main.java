package com.company;
import farbe.TestEnums;
import zähl.*;
import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Teste Zähler:");
	    TestZähler.testeZähler();
        System.out.println();

        System.out.println("Teste Enums:");
        TestEnums.testeEnums();
        System.out.println();

        System.out.println(Math.random());
        System.out.println(random());
    }
}
