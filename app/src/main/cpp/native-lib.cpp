#include <stdio.h>
#include <jni.h>
#include <math.h>

#define MAX 100000000

JNIEXPORT void JNICALL
Java_com_example_alanarteagav_calgorithms_MainActivity_calculaprimo(JNIEnv *env, jobject instance, jint n) {
    int i,j,count = 0;

    if (n == 1)
        return;
    for(i = 3;i <= MAX;i += 2) {
        int isPrime = 1;
        int jMax = sqrt(i);
        for(j = 3;j <= jMax;j += 2)
            if(i % j == 0) {
                isPrime = 0;
                break;
            }
        if(isPrime)
            if(++count == n)
                return;
    }
}


JNIEXPORT void quicksortaux(int arr[], int left, int right) {

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
            quicksortaux(arr, left, j);
        }

        if(right > i) {
            quicksortaux(arr, i, right);
        }

    }

}

JNIEXPORT void JNICALL
Java_com_example_alanarteagav_calgorithms_MainActivity_quicksort(JNIEnv *env, jobject instance, jint n) {
    int numbers[n];
    int j = 0;
    for (int i = n; i > 0; i--)
        numbers[j++] = i;

    quicksortaux(numbers, 0, sizeof(numbers)/sizeof(numbers[0]) );
}


