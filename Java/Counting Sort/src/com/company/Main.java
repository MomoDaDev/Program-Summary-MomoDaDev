package com.company;


public class Main {
    static int INTERVALLGRENZE = 100;
    public static void main(String[] args) {
        int[] n10 = FillArray.fillIntArraywithRandom(10, INTERVALLGRENZE);
        int[] n100 = FillArray.fillIntArraywithRandom(100, INTERVALLGRENZE);
        int[] n1000 = FillArray.fillIntArraywithRandom(1000, INTERVALLGRENZE);
        int[] n10000 = FillArray.fillIntArraywithRandom(10000, INTERVALLGRENZE);
        int[] n100000 = FillArray.fillIntArraywithRandom(100000, INTERVALLGRENZE);

        // Testing if FillArray.fillIntArraywithRandom(10, INTERVALLGRENZE); works
        System.out.print("Printing n10: { ");
        for (int i = 0; i < n10.length; i++) {
            System.out.print(n10[i] + " ");
        }
        System.out.println("}");

        Result r10 = CountingSort.countingsort(n10, INTERVALLGRENZE);

        // Testing if CountingSort.countingsort(n10, INTERVALLGRENZE); works
        System.out.print("Printing n10 sorted: { ");
        for (int i = 0; i < n10.length; i++) {
            System.out.print(n10[i] + " ");
        }
        System.out.println("}");

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        // die restlichen Array werden sortiert
        Result r100 = CountingSort.countingsort(n100, INTERVALLGRENZE);
        Result r1000 = CountingSort.countingsort(n1000, INTERVALLGRENZE);
        Result r10000 = CountingSort.countingsort(n10000, INTERVALLGRENZE);
        Result r100000 = CountingSort.countingsort(n100000, INTERVALLGRENZE);

        // Ausgabe der restlichen Arrays
        System.out.println(r10.toString());
        System.out.println(r100.toString());
        System.out.println(r1000.toString());
        System.out.println(r10000.toString());
        System.out.println(r100000.toString());
    }
}
