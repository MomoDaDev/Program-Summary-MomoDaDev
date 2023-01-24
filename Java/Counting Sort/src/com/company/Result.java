package com.company;

public class Result {
    public int[] array;
    public int zugriffe;

    public Result(int[] array, int zugriffe) {
        this.array = array;
        this.zugriffe = zugriffe;
    }

    @Override
    public String toString() {
        return "Result{" +
                "\nLänge des Arrays: " + array.length +
                "\nZugriffe  = " + zugriffe +
                "\nZugriffe / n = " + (zugriffe / array.length) +
                "\nZugriffe / n² = " + (zugriffe / Math.pow(array.length, 2)) +
                " }";
    }
}
