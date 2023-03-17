package com.kerry;

public class MergeArr {


    public static void main(String[] args) {
        int[] arr1 = {1, 3, 7, 11, 22, 23, 59, 88, 99};
        int[] arr2 = {2, 4, 5, 9, 22, 23};

        int[] mergeArr = mergeArr(arr1, arr2);
        for (int i = 0; i < mergeArr.length; i++) {
            System.out.print(mergeArr[i]);
        }

    }


    public static int[] mergeArr(int[] arr1, int[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        int maxLength = length2 + length1;

        int[] newArr = new int[maxLength];
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        while (index < maxLength) {

            if (index1 < length1 && index2 < length2) {

                if (arr1[index1] <= arr2[index2]) {
                    newArr[index] = arr1[index1];
                    index1++;

                } else {
                    newArr[index] = arr2[index2];
                    index2++;
                }
            } else if (index1 >= length1) {

                newArr[index] = arr2[index2];
                index2++;
            } else {
                newArr[index] = arr1[index1];
                index1++;
            }
            index++;
        }


        return newArr;
    }
}
