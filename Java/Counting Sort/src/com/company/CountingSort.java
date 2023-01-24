package com.company;

public class CountingSort {
    public static Result countingsort (int[] A, int k) {
        int zugriffe = 0;
        int[] C = new int[k + 1];

        // initialisiere das Array C mit Nullen
        for (int m = 0; m <= k; m ++) {
            C[m] = 0;
            zugriffe++;
        }
        // end for

        // Zaehle
        for (int j = 1; j < A.length; j++) {
            C[A[j]] += 1;
        }
        // end for
        // Nun steht in C [ m ] wie haeufig der Wert m in A vorkommt.

        // Adressrechnung
        for (int m = 1; m <= k; m++) {
            C[m] += C[m - 1];
            zugriffe += 2;
        }
        // end for

        int[] B = new int[A.length];

        // kopieren auf jeweilige Zieladresse
        for (int j = A.length - 1; j >= 0; j--) {
            B[C[A[j]]] = A[j];
            C[A[j]] -= 1; // Zielposition um 1 herunterzaehlen
            zugriffe += 6;
        }
        // end for

        Result result = new Result(B, A.length + k);

        return result;
    }
}
