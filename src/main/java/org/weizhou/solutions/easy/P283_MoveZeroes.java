package org.weizhou.solutions.easy;

import org.weizhou.solutions.core.LeetCodeSolution;

import java.util.Arrays;

/**
 283. 移动零
 已解答
 简单
 相关标签
 premium lock icon
 相关企业
 提示
 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 请注意 ，必须在不复制数组的情况下原地对数组进行操作。



 示例 1:

 输入: nums = [0,1,0,3,12]
 输出: [1,3,12,0,0]
 示例 2:

 输入: nums = [0]
 输出: [0]


 提示:

 1 <= nums.length <= 104
 -231 <= nums[i] <= 231 - 1


 进阶：你能尽量减少完成的操作次数吗？

 面试中遇到过这道题?
 1/5
 是
 否
 通过次数
 1,868,918/2.9M
 通过率
 64.1%
 相关标签
 icon
 © 2025 领扣网络（上海）有限公司
 */

/*
使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。

右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。

注意到以下性质：

左指针左边均为非零数；

右指针左边直到左指针处均为零。

因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。

作者：力扣官方题解
链接：https://leetcode.cn/problems/move-zeroes/solutions/489622/yi-dong-ling-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */


public class P283_MoveZeroes implements LeetCodeSolution {
    public void moveZeroesSolve1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length, left =0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
            right++;
        }
    }

    public void moveZeroesSolve2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int left = 0, right = 0;
        while(right < nums.length) {
            // 目标：是把非零的数逐渐放到最前面
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        for (int i = left; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public Object test(){
        int[] nums = new int[]{0,1,0,3,12};
//        moveZeroesSolve1(nums);
        moveZeroesSolve2(nums);
        return Arrays.toString(nums);
    }
}


