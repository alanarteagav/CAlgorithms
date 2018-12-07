package com.example.alanarteagav.calgorithms;

public class Algorithms {

    final static int MAX = 100000000;


    public static void calculaPrimo(int n) {
        int i, j, count = 0;
        if (n == 1)
            return;
        for(i = 3;i <= MAX;i += 2) {
            boolean esPrimo = true;
            double jMax = Math.sqrt(i);
            for(j = 3;j <= jMax;j += 2)
                if(i%j==0) {
                    esPrimo = false;
                    break;
                }
            if(esPrimo)
                if(++count == n)
                    return;
        }
    }


    public static void quickSort(int n){
        int[] numbers = new int[n];
        int j = 0;
        for (int i = n; i > 0; i--)
            numbers[j++] = i;

        quickSortAux(numbers, 0, numbers.length - 1);
    }


    public static void quickSortAux(int[] arr, int left, int right) {

        int pivotIndex = left + (right - left) / 2;
        int pivotValue = arr[pivotIndex];

        int i = left, j = right;

        while(i <= j) {

            while(arr[i] < pivotValue) {
                i++;
            }

            while(arr[j] > pivotValue) {
                j--;
            }

            if(i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }

            if(left < i) {
                quickSortAux(arr, left, j);
            }

            if(right > i) {
                quickSortAux(arr, i, right);
            }

        }

    }

}
