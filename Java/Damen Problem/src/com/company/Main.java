package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int n = 6; // field size

    /*

      1   2   3   4   5   6
    +---+---+---+---+---+---+
  1 |   |   |   |   |   |   |
    +---+---+---+---+---+---+
  2 |   |   |   |   |   |   |
    +---+---+---+---+---+---+
  3 |   |   |   |   |   |   |
    +---+---+---+---+---+---+
  4 |   |   |   |   |   |   |
    +---+---+---+---+---+---+
  5 |   |   |   |   |   |   |
    +---+---+---+---+---+---+
  6 |   |   |   |   |   |   |
    +---+---+---+---+---+---+

     */
    public static void main(String[] args) {
        /*
        List<int[]> testdamen = new ArrayList<>();
        testdamen.add(new int[] {4,0}); // index 0 = X; index 1 = Y
        testdamen.add(new int[] {2,4}); // index 0 = X; index 1 = Y
        testdamen.add(new int[] {5,2}); // index 0 = X; index 1 = Y
        testdamen.add(new int[] {0,5}); // index 0 = X; index 1 = Y
        System.out.println("Test:");
        System.out.println(createField(testdamen, n));
        System.out.println("Did it collide? " + checkcollision(testdamen, n));
        System.out.println("---------------------------------------");
        */

        // set up damen
        List<int[]> damen = new ArrayList<>();

        int countOfSolution = 0;

        // place damen

        // Dame1
        for (int row1 = 0; row1 < n; row1++) { // row for dame 1
            for (int col1 = 0; col1 < n; col1++) { // col for dame 1
                // Dame2
                for (int row2 = 1; row2 < n; row2++) { // row for dame 2
                    for (int col2 = 1; col2 < n; col2++) { // col for dame 2
                        // Dame3
                        for (int row3 = 2; row3 < n; row3++) { // row for dame 3
                            for (int col3 = 2; col3 < n; col3++) { // col for dame 3
                                // Dame4
                                for (int row4 = 3; row4 < n; row4++) { // row for dame 4
                                    for (int col4 = 3; col4 < n; col4++) { // col for dame 4
                                        damen.clear();
                                        damen.add(new int[] { row1, col1 });
                                        damen.add(new int[] { row2, col2 });
                                        damen.add(new int[] { row3, col3 });
                                        damen.add(new int[] { row4, col4 });
                                        if (!checkcollision(damen, n)) {
                                            countOfSolution++;
                                            if (countOfSolution == 1) {
                                                System.out.println("First solution: ");
                                                System.out.println(createField(damen, n));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Fieldsize: " + n);
        System.out.println("Count of solutions: " + countOfSolution);

        System.out.println("end of program");
    }
    private static boolean checkcollision(List<int[]> damen, int n) {
        for (int i = 0; i < damen.size(); i++) {
            for (int k = 0; k < damen.size(); k++) {
                if (i != k){
                    // check horizontal
                    if (damen.get(i)[0] == damen.get(k)[0]) { // if row is not empty
                        return true;
                    }
                    // check vertical
                    if (damen.get(i)[1] == damen.get(k)[1]) { // if col is not empty
                        return true;
                    }

                    // the diagonal checks automatically check if another queen stands in it

                    // check diagonal bottom left to top right
                    for (int j = 1 - n; j < n; j++) {
                        if (damen.get(i)[0] - j == damen.get(k)[0] && damen.get(i)[1] + j == damen.get(k)[1]) {
                            return true;
                        }
                    }
                    // check diagonal bottom left to top right
                    for (int j = 1 - n; j < n; j++) {
                        if (damen.get(i)[0] + j == damen.get(k)[0] && damen.get(i)[1] + j == damen.get(k)[1]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    private static String createField(List<int[]> damen, int n) {
        String field = " ";

        for (int i = 0; i < n; i++) { //    1   2   3   4   5   6
            field += "   " + (i + 1);
        }

        //    +---+---+---+---+---+---+
        //  1 |   |   |   |   |   |   |
        // .......
        for (int i = 0; i < n; i++) {
            // +---+---+---+---+---+---+
            field += "\n  ";
            for (int j = 0; j < n; j++) {
                field += "+---";
            }
            field += "+";
            //1 |   |   |   |   |   |   |
            field += "\n" + (i + 1) + " ";
            for (int j = 0; j < n; j++) {
                boolean drawdame = false;
                for (int k = 0; k < damen.size(); k++) {
                    if (damen.get(k)[0] == i && damen.get(k)[1] == j) {
                        drawdame = true;
                    }
                }
                if (drawdame)
                    field += "| O ";
                else
                    field += "|   ";
            }
            field += "|";
        }
        // +---+---+---+---+---+---+
        field += "\n  ";
        for (int j = 0; j < n; j++) {
            field += "+---";
        }
        field += "+";

        return field;
    }
}
