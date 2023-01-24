package com.company;

import java.util.Arrays;

public class Main {

    //private static final int arraySize = 10000;
    public static void main(String[] args) {
    /*
        int[] intArray = SortierAnalyse.getIntArray(arraySize, 0, 30000);
        int[] intArrayAscending = new int[arraySize];
        Arrays.setAll(intArrayAscending, i -> i + 1);
        int[] intArrayDescending = new int[arraySize];
        Arrays.setAll(intArrayDescending, i -> i - 1);

        // Random

        Result SelectionSort = SortierAnalyse.SelectionSort(intArray);
        Result BubbleSort = SortierAnalyse.BubbleSort(intArray);
        Result InsertionSort = SortierAnalyse.InsertionSort(intArray);

        System.out.println(SelectionSort.getResultString("SelectionSort"));
        System.out.println(BubbleSort.getResultString("BubbleSort"));
        System.out.println(InsertionSort.getResultString("InsertionSort"));

        SelectionSort.writeResultAsCSV("SelectionSort");
        BubbleSort.writeResultAsCSV("BubbleSort");
        InsertionSort.writeResultAsCSV("InsertionSort");

        // Quicksort
        Quicksort qs = new Quicksort(intArray);
        qs.sort(0, qs.result.result.length - 1);
        System.out.println(qs.result.getResultString("Quicksort"));
        qs.result.writeResultAsCSV("Quicksort");

        // Ascending

        Result SelectionSortAscending = SortierAnalyse.SelectionSort(intArrayAscending);
        Result BubbleSortAscending = SortierAnalyse.BubbleSort(intArrayAscending);
        Result InsertionSortAscending = SortierAnalyse.InsertionSort(intArrayAscending);

        System.out.println(SelectionSortAscending.getResultString("SelectionSortAscending"));
        System.out.println(BubbleSortAscending.getResultString("BubbleSortAscending"));
        System.out.println(InsertionSortAscending.getResultString("InsertionSortAscending"));

        SelectionSortAscending.writeResultAsCSV("SelectionSortAscending");
        BubbleSortAscending.writeResultAsCSV("BubbleSortAscending");
        InsertionSortAscending.writeResultAsCSV("InsertionSortAscending");

        // Quicksort
        Quicksort qsAscending = new Quicksort(intArrayAscending);
        qsAscending.sort(0, qsAscending.result.result.length - 1);
        System.out.println(qsAscending.result.getResultString("QuicksortAscending"));
        qsAscending.result.writeResultAsCSV("QuicksortAscending");

        // Descending

        Result SelectionSortDescending = SortierAnalyse.SelectionSort(intArrayDescending);
        Result BubbleSortDescending = SortierAnalyse.BubbleSort(intArrayDescending);
        Result InsertionSortDescending = SortierAnalyse.InsertionSort(intArrayDescending);

        System.out.println(SelectionSortDescending.getResultString("SelectionSortDescending"));
        System.out.println(BubbleSortDescending.getResultString("BubbleSortDescending"));
        System.out.println(InsertionSortDescending.getResultString("InsertionSortDescending"));

        SelectionSortDescending.writeResultAsCSV("SelectionSortDescending");
        BubbleSortDescending.writeResultAsCSV("BubbleSortDescending");
        InsertionSortDescending.writeResultAsCSV("InsertionSortDescending");

        // Quicksort
        Quicksort qsDescending = new Quicksort(intArrayDescending);
        qsDescending.sort(0, qsDescending.result.result.length - 1);
        System.out.println(qsDescending.result.getResultString("QuicksortDescending"));
        qsDescending.result.writeResultAsCSV("QuicksortDescending");
        */
        System.out.println("Berechnet . . .");
        String[] zugriffe = new String[] { "Zugriffe:", "Zugriffe/n", "Zugriffe/n²"};
        Result[][] results = new Result[4][5]; // 4 is for the type of algorithm; 6 for n
        int[] n_zahlen = new int[] {10, 100, 1000, 10000, 100000};
        System.out.println("|0%            100%|");
        for (int i = 0; i < n_zahlen.length; i++) { // for 10 - 1 000 000
            int[] intArray = SortierAnalyse.getIntArray(n_zahlen[i], 0, 30000);

            results[0][i] = SortierAnalyse.SelectionSort(intArray);
            System.out.print("=");
            results[1][i] = SortierAnalyse.BubbleSort(intArray);
            System.out.print("=");
            results[2][i] = SortierAnalyse.InsertionSort(intArray);
            System.out.print("=");
            Quicksort qs = new Quicksort(intArray);
            qs.sort(0, qs.result.result.length - 1);
            results[3][i] = qs.result;
            System.out.print("=");
        }
        System.out.println();
        System.out.println("Finished");
        System.out.println();

        String leftAlignFormat = "| %-15s | %-12s | %-12s | %-14s | %-17s | %-18s |%n";

        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
        System.out.format("| SelectionSort   | 10           | 100          | 1000           | 10000             | 100000             |%n");
        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
        for (int i = 0; i < 3; i++) {
            System.out.format(leftAlignFormat, zugriffe[i],
                    String.format("%.6f",results[0][0].countOfAccess / Math.pow(n_zahlen[0], i)),
                    String.format("%.6f",results[0][1].countOfAccess / Math.pow(n_zahlen[1], i)),
                    String.format("%.6f",results[0][2].countOfAccess / Math.pow(n_zahlen[2], i)),
                    String.format("%.6f",results[0][3].countOfAccess / Math.pow(n_zahlen[3], i)),
                    String.format("%.6f",results[0][4].countOfAccess / Math.pow(n_zahlen[4], i)));
        }
        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");

        System.out.println();

        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
        System.out.format("| BubbleSort      | 10           | 100          | 1000           | 10000             | 100000             |%n");
        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
        for (int i = 0; i < 3; i++) {
            System.out.format(leftAlignFormat, zugriffe[i],
                    String.format("%.6f",results[1][0].countOfAccess / Math.pow(n_zahlen[0], i)),
                    String.format("%.6f",results[1][1].countOfAccess / Math.pow(n_zahlen[1], i)),
                    String.format("%.6f",results[1][2].countOfAccess / Math.pow(n_zahlen[2], i)),
                    String.format("%.6f",results[1][3].countOfAccess / Math.pow(n_zahlen[3], i)),
                    String.format("%.6f",results[1][4].countOfAccess / Math.pow(n_zahlen[4], i)));
        }
        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");

        System.out.println();

        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
        System.out.format("| InsertionSort   | 10           | 100          | 1000           | 10000             | 100000             |%n");
        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
        for (int i = 0; i < 3; i++) {
            System.out.format(leftAlignFormat, zugriffe[i],
                    String.format("%.6f",results[2][0].countOfAccess / Math.pow(n_zahlen[0], i)),
                    String.format("%.6f",results[2][1].countOfAccess / Math.pow(n_zahlen[1], i)),
                    String.format("%.6f",results[2][2].countOfAccess / Math.pow(n_zahlen[2], i)),
                    String.format("%.6f",results[2][3].countOfAccess / Math.pow(n_zahlen[3], i)),
                    String.format("%.6f",results[2][4].countOfAccess / Math.pow(n_zahlen[4], i)));
        }
        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");

        System.out.println();

        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
        System.out.format("| Quicksort       | 10           | 100          | 1000           | 10000             | 100000             |%n");
        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
        for (int i = 0; i < 3; i++) {
            System.out.format(leftAlignFormat, zugriffe[i],
                    String.format("%.6f",results[3][0].countOfAccess / Math.pow(n_zahlen[0], i)),
                    String.format("%.6f",results[3][1].countOfAccess / Math.pow(n_zahlen[1], i)),
                    String.format("%.6f",results[3][2].countOfAccess / Math.pow(n_zahlen[2], i)),
                    String.format("%.6f",results[3][3].countOfAccess / Math.pow(n_zahlen[3], i)),
                    String.format("%.6f",results[3][4].countOfAccess / Math.pow(n_zahlen[4], i)));
        }
        System.out.format("+-----------------+--------------+--------------+----------------+-------------------+--------------------+%n");
    }
}
