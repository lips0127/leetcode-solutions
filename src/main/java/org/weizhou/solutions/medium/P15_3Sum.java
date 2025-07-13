package java.org.weizhou.solutions.medium;

import java.org.weizhou.solutions.core.LeetCodeSolution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15_3Sum implements LeetCodeSolution {
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> answerlist = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return answerlist;
        }
        // 需要排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
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
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    // Q:其实这里为什么要在while里面继续搞一个while，我不是理解;20250714
                    // A:因为已经排过序了，所以理论上如果要nums[i] + nums[j] + nums[k] == 0，
                    // 假设以[0,-4,-2,2,4]为例子，nums[i] = 0，那么找到的第一组为：0，-4，4
                    // 显然，出现一侧确定性时，另外一次除非左边的值是重复值，否则左侧不可再用，所以必须是同时left++，right++
                    answerlist.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 找到0值以后，可能还存在其他的可能性，所以要继续寻找
                    while(left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while(left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return answerlist;
    }

    @Override
    public Object test() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        return threeSum(nums);
    }
}
