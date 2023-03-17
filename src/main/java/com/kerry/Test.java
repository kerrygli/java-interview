package com.kerry;

import java.util.HashMap;
import java.util.Map;

public class Test {


    public static void main(String[] args) {


        int[] arr = new int[]{};
        int target = 10;
        int index = findIndex(arr,target);
        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);

    }

    private static int findIndex(int[] arr,int target) {
        int index = 0;
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]==target){
                return i;
            }
            if(arr[i]>arr[i+1]){
                index=0;
            }
        }

        return  index;
    }
}
