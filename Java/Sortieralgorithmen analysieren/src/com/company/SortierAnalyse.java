package com.company;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortierAnalyse {
    private static Random rnd = new Random();
    public static int[] getIntArray(int n, int min, int max) {
        int[] intArray = new int[n];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = rnd.nextInt(max + 1) + min;
        }
        return intArray;
    }
    public static String getArray_comma_separated(int[] intArray) {
        return IntStream.of(intArray)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));
    }

    // Sort-Algorithms
    public static Result SelectionSort(int[] intArray) {
        Result result = new Result(intArray, 0);
        for (int i = 0; i < result.result.length - 1; i++) {
            result.countOfAccess += 1; // increase countOfAccess
            // Search the smallest element in the remaining array
            int minPos = i;
            int min = result.result[minPos];
            result.countOfAccess += 1; // increase countOfAccess
            for (int j = i + 1; j < result.result.length; j++) {
                result.countOfAccess += 1; // increase countOfAccess
                result.countOfAccess += 1; // increase countOfAccess
                if (result.result[j] < min) {
                    minPos = j;
                    min = result.result[minPos];
                    result.countOfAccess += 1; // increase countOfAccess
                }
            }

            // Swap min with element at pos i
            if (minPos != i) {
                result.result[minPos] = result.result[i];
                result.result[i] = min;
                result.countOfAccess += 3; // increase countOfAccess
            }
        }
        return result;
    }

    public static Result BubbleSort(int[] intArray) {
        Result result = new Result(intArray, 0);
        int temp;
        for(int i=1; i<result.result.length; i++) {
            result.countOfAccess += 1; // increase countOfAccess
            for(int j=0; j<result.result.length-i; j++) {
                result.countOfAccess += 1; // increase countOfAccess
                result.countOfAccess += 2; // increase countOfAccess
                if(result.result[j]>result.result[j+1]) {
                    // switch position
                    temp=result.result[j];
                    result.result[j]=result.result[j+1];
                    result.result[j+1]=temp;
                    result.countOfAccess += 4; // increase countOfAccess
                }
            }
        }
        return result;
    }

    public static Result InsertionSort(int[] intArray) {
        Result result = new Result(intArray, 0);
        int temp;
        for (int i = 1; i < result.result.length; i++) {
            result.countOfAccess += 1; // increase countOfAccess
            temp = result.result[i];
            result.countOfAccess += 1; // increase countOfAccess
            int j = i;
            while (j > 0 && result.result[j - 1] > temp) {
                result.countOfAccess += 1; // increase countOfAccess
                result.result[j] = result.result[j - 1];
                result.countOfAccess += 1; // increase countOfAccess
                j--;
            }
            result.result[j] = temp;
            result.countOfAccess += 1; // increase countOfAccess
        }
        return result;
    }
}
