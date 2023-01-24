package com.company;


import javax.management.openmbean.ArrayType;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int arr1[] = { 0, 1, 2, 3, 4, 5 };
        int arr2[] = { 5, 10, 20, 30, 40, 50 };

        ArraysKopieren.arraycopy(arr1, 0, arr2, 0, 3);

        for (int i : arr2){
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("- - - - - - - - - - - - - -");

        Integer[] arr3 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Integer[] arr4 = { 5, 10, 20, 30, 40, 50 };

        arr4 = ArraysKopieren.copyOf(arr3, 8);

        for (int i : arr4){
            System.out.print(i + " ");
        }
    }
    public static class ArraysKopieren{
        public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
            System.arraycopy(src, srcPos, dest, destPos, length);
        }
        public static <T> T[] copyOf(T[] arr, int length){
            return Arrays.copyOf(arr, length);
        }

    }
}
