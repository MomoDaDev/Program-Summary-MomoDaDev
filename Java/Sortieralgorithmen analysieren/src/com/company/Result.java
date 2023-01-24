package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Result {
    public int[] result;
    public double countOfAccess;

    public Result(int[] result, int countOfAccess) {
        this.result = result;
        this.countOfAccess = countOfAccess;
    }
    public void writeResultAsCSV(String name) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(name + ".csv"));
            bw.write(getResultString(name));
            bw.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    public String getResultString(String name) {
        return name + ": {" +
                "\nn = (" + SortierAnalyse.getArray_comma_separated(result) + ")" +
                "\nCountOfAccess = " + countOfAccess +
                "\nCountOfAccess / n = " + countOfAccess / result.length +
                "\nCountOfAccess / n² = " + (countOfAccess / Math.pow(result.length, 2)) +
                "\n}";
    }
}
