package java.org.weizhou.solutions.medium;

import java.org.weizhou.solutions.core.LeetCodeSolution;
import java.util.*;
import java.util.stream.Collectors;

public class P438_FindAllAnagramsInAStr implements LeetCodeSolution {
    @Override
    public void execute() {
        LeetCodeSolution.super.execute();
    }

    @Override
    public Object test() {
        return null;
    }

    // 定长滑动区间法
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();
        int[] cntS = new int[26];
        int[] cntP = new int[26];
        // 定长滑动区间
        // 记录字符串P中每个字符出现的频率，因为本题要求异位次，所以只要数组equals
        // 说明就符合。
        for (int i = 0; i < p.length(); i++) {
            cntP[p.charAt(i) - 'a']++;
        }
        int right, left;
        for (right = 0; right < s.length(); right++) {
            cntS[s.charAt(right) - 'a']++;
            left = right - p.length() + 1;
            if (left < 0) {
                continue;
            }
            // 因为右断点是从0起始的，所以左侧下标不做词频统计
            //cntS[s.charAt(left) - 'a']++;
            if(Arrays.equals(cntS, cntP)){
                result.add(left);
            }
            cntS[s.charAt(left) - 'a']--;
        }
        return result;
    }
}
