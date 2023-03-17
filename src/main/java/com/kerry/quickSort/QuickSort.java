package com.kerry.quickSort;

public class QuickSort {


    public static void main(String[] args) {
        int[] testArr = {1, 3, 7, 11, 22, 23, 59, 88, 99};


         quickSort(testArr, 0, testArr.length - 1);
        for (int i = 0; i < testArr.length; i++) {
            System.out.print(testArr[i]+"-");
        }
    }

    private static void quickSort(int[] arr, int start, int end) {


        int baseValue = arr[start];
        int i = start;
        int j = end;
        do {
            //从左往右依次找到比基准值大的值
            while (arr[i] < baseValue && i < end) {
                i++;
            }

            while (arr[j] < baseValue && j > start) {
                j--;
            }
            //如果左边含有比中间值大的，右边含有比中间值小的，则进行交换
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        //从右往左遍历一边
        if (start < j) {
            quickSort(arr, start, j);
        }
        //从左往右遍历一边
        if (end > i) {
            quickSort(arr, i, end);
        }

    }
}
