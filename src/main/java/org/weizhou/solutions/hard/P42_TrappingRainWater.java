package java.org.weizhou.solutions.hard;

import java.org.weizhou.solutions.core.LeetCodeSolution;

public class P42_TrappingRainWater implements LeetCodeSolution {
    public int trap(int[] height) {
        // 不具备接雨水条件
        if (height.length < 3) {
            return 0;
        }
        // 接雨水的核心要义是移动双指针的同时，用当前高度去分次统计每一个小水洼，进而组成大的水洼
        int left = 0, right = height.length - 1;
        // 当前左右最大高度
        int leftMax = 0, rightMax = 0, ans = 0;
        // 快慢指针循环
        while (left < right) {
            // 如果左侧相对的值更低，极端情况下，为了接到更多的水，需要先移动左侧
            if (height[left] < height[right]) {
                // 更新当前左侧最高柱子
                leftMax = Math.max(leftMax, height[left]);
                // 如何统计左侧当前leftMax和height[left]的水，进而统计到总水量里面里面
                // 为什么每一测的水总量是如此统计呢，因为从左边往右找的过程中，我们总是找到第一个最高的左侧柱子
                // 那么从这个柱子开始，只要比他低的柱子，都可以用来蓄水，直到找到一个同样高度的，不再可以蓄水，所以
                // 例如: ans += t - t
                // 如果出现了一个新高的值，那么会更新leftMax，进而ans += newLeftMax - newLeftMax，相当于加0，也就是
                // 结束了上一个左侧最高能够收集的雨水的小水洼
                ans += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }


    @Override
    public Object test() {
        return null;
    }
}
