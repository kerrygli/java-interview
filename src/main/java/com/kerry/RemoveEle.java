package com.kerry;

public class RemoveEle {


    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3,4,3};


        int size = removeElement(nums, 4);
        System.out.println(size);
    }

    private static int removeElement(int[] nums, int var) {

        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {

            if (var != nums[fastIndex]) {
                nums[slowIndex++] = nums[fastIndex];
            }
        }

        return slowIndex;

    }
}
