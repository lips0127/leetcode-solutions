package java.org.weizhou.solutions.medium;

import java.org.weizhou.solutions.core.LeetCodeSolution;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 128. 最长连续序列
 * 尝试过
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 示例 3：
 *
 * 输入：nums = [1,0,1,2]
 * 输出：3
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,086,953/2.2M
 * 通过率
 * 50.0%
 */

public class P128_longestConsecutiveSeq implements LeetCodeSolution {
    // 时间复杂度不足，在78/81个用例无法通过
    public int longestConsecutiveSolve1(int[] nums) {
        if (nums.length == 0) return 0;
        //数组塞入hashSet
        Set<Integer> numsCollect = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        //转成hashSet之后，重新回去遍历数组
        int resultLength = 1;
        int tempMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if(numsCollect.contains(nums[i]-1)) {
                continue;
            }
            int targetNum = nums[i] + 1;
            while (numsCollect.contains(targetNum)) {
                resultLength++;
                targetNum++;
            }
            if(resultLength > tempMax){
                tempMax = resultLength;
            }
            resultLength = 1;
        }
        // 因为序列长度本身是要包含首个数字的，所以此处需要+1
        return tempMax;
    }

    // 我们要明确这道题目，要提速，就需要将不可能是序列第一个元素的给continue掉
    // solve2优化了solve1但是实际上还是会有提交执行超时的问题
    public int longestConsecutiveSolve2(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int resultLength = 1;
        int tempMaxLength = 1;
        for (int num : nums) {
            // Set中有比你小的，当前元素无法作为最长子序列的第一个字符
            if (numsSet.contains(num - 1)) {
                continue;
            }
            int targetNum = num + 1;
            while (numsSet.contains(targetNum)) {
                resultLength++;
                targetNum++;
            }
            if (resultLength > tempMaxLength) {
                tempMaxLength = resultLength;
            }
            resultLength = 1;
        }
        return tempMaxLength;
    }

    /// 官方题解，其实和第二题很接近了，但是第二题没有做到遍历Set
    public int longestConsecutiveSolve3(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int tempMaxLength = 1;
        int currentLength;
        for (int num : numsSet) {
            currentLength = 1;
            if (numsSet.contains(num - 1)) {
                continue;
            }
            int targetNum = num + 1;
            while(numsSet.contains(targetNum)) {
                targetNum++;
                currentLength++;
            }
            tempMaxLength = Math.max(tempMaxLength, currentLength);
        }
        return tempMaxLength;
    }

    @Override
    public Object test() {
        int[] nums = new int[]{1,3,5,2,1,4};
        return longestConsecutiveSolve2(nums);
    }
}
