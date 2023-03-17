package com.kerry;

import java.util.HashMap;
import java.util.Map;

public class LongSubstring {


    public static void main(String[] args) {

        String str = "bbbb";
        int i = lengthOfLongestSubString(str);
        System.out.println(i);

    }


    public static int lengthOfLongestSubString(String s) {
        if (s.length() == 0) {
            return 0;
        }
        //key:字符；value:字符下标
        Map<Character, Integer> map = new HashMap<>();
        //存取最后返回的长度
        int max = 0;
        //记录最左侧字符下标
        int left = 0;

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                //如果包含某个字符，则重置开始位置索引
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
