package com.company;

public class Quicksort {
    public Result result;

    public Quicksort(int[] intArr) {
        result = new Result(intArr, 0);
    }

    // Quicksort
    public int[] sort(int l, int r) {
        int q;
        if (l < r) {
            q = partition(l, r);
            sort(l, q);
            sort(q + 1, r);
        }
        result.countOfAccess += 1; // increase countOfAccess
        return result.result;
    }

    int partition(int l, int r) {

        int i, j, x = result.result[(l + r) / 2];
        result.countOfAccess += 1; // increase countOfAccess
        i = l - 1;
        j = r + 1;
        while (true) {
            do {
                i++;
                result.countOfAccess += 1; // increase countOfAccess
            } while (result.result[i] < x);

            do {
                j--;
                result.countOfAccess += 1; // increase countOfAccess
            } while (result.result[j] > x);

            if (i < j) {
                int k = result.result[i];
                result.result[i] = result.result[j];
                result.result[j] = k;
                result.countOfAccess += 4; // increase countOfAccess
            } else {
                return j;
            }
        }
    }
}
