package java.org.weizhou.solutions.medium;

import java.org.weizhou.solutions.core.LeetCodeSolution;
import java.util.HashMap;

public class P03_LongestSubstrNoRepeat implements LeetCodeSolution {
    @Override
    public Object test() {
        return null;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int left = 0, right = 0;
        int tempMaxSubstrLen = 0;

        // 需要有一个数据结构来记录当前最长的字符串是什么
        // 也许我们可以用set？
        HashMap<Character, Integer> subStrHashMap = new HashMap<>();
        while (right < s.length()) {
            // 考虑什么情况需要变动left
            // 为什么需要检查 subStrHashMap.get(char) >= left
            // 反例abba
            // 这个检查是为了​​避免处理历史无效重复​​，确保只响应​​在当前窗口内​​出现的重复字符。以下是一个经典反例说明它的必要性：
            if(subStrHashMap.containsKey(s.charAt(right)) && subStrHashMap.get(s.charAt(right)) >= left) {
                left = subStrHashMap.get(s.charAt(right)) + 1;
            }
            subStrHashMap.put(s.charAt(right), right);
            tempMaxSubstrLen = Math.max(tempMaxSubstrLen, right - left + 1);
            // right是不需要回退的，因为right遇到重复的，并且此重复的位置是大于left位置，那么就可以直接把left指向right之前重复位置的后一个位置
            // 此时right的位置显然可以积蓄统计tempMaxLength，例子:abcdae.
            right++;
        }
        return tempMaxSubstrLen;
    }
}
