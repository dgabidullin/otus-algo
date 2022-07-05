package impl;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int[] arr) {
        for (int i = 1; i <= arr.length - 1; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] n = {2, 4, 1, 3};
        InsertionSort.sort(n);
        System.out.println(Arrays.toString(n));
    }
}
