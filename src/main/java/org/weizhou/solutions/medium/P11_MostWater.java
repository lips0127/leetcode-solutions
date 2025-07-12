package java.org.weizhou.solutions.medium;

import java.org.weizhou.solutions.core.LeetCodeSolution;

public class P11_MostWater implements LeetCodeSolution {
    // 移动较矮的那一个下标
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int tempMaxArea = 0;
        while (left < right) {
            tempMaxArea = Math.max(
                    calcArea(Math.min(height[left], height[right]), right - left), tempMaxArea 
            );
            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return tempMaxArea;
    }

    public static int calcArea(int height, int width) {
        return height * width;
    }

    @Override
    public Object test() {
        return new Object();
    }
}
