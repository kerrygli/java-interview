package com.kerry.binarySearch;

import java.util.concurrent.ConcurrentHashMap;

public class BinarySearch {


    public static void main(String[] args) {
        int[] testArray = {1, 3, 7, 11, 22, 23, 59, 88, 99};

        int search = search2(testArray, 2, 0, testArray.length - 1);
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        System.out.println(search);
    }


    public static int search(int[] arr, int value, int start, int end) {

        if (start > end) {
            return -1;
        }

        int middleIndex = (start + end) / 2;

        if (value > arr[middleIndex]) {
            return search(arr, value, start + 1, end);
        } else if (value < arr[middleIndex]) {
            return search(arr, value, start, end - 1);
        }else {
            return arr[middleIndex];
        }
    }


    public static int search2(int[] arr, int value, int start, int end) {

        int defaultValue = -1;

        while (start < end) {
            int middleIndex = (start + end) / 2;
            if (value > arr[middleIndex]) {
                start++;
            } else if (value < arr[middleIndex]) {
                end--;
            }else {
                return arr[middleIndex];
            }

        }
        return defaultValue;

    }
}
