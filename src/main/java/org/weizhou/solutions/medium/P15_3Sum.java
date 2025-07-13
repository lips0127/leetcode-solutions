package java.org.weizhou.solutions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> answerlists = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return answerlists;
        }
        int i = 0;
        // 需要排序
        Arrays.sort(nums);
        for (i = 0; i < nums.length-3; i++) {
            // 剪枝
            if(nums[i] > 0) {
                continue;
            }
            // 去重
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                while(nums[i]+nums[left]+nums[right] < 0){
                    left++;
                }
                while(nums[i]+nums[right] > 0){
                    right--;
                }
                answerlists.add(Arrays.asList(nums[i], nums[left], nums[right]));
            }
        }
        return answerlists;
    }
}
