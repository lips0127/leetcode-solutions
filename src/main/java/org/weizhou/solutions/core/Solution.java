package org.weizhou.solutions.core;

public interface Solution {
    default void execute() {
        try {
            System.out.println("[" + this.getClass().getSimpleName() + "] ");
            long start = System.nanoTime();
            Object result = test();
            long duration = (System.nanoTime() - start) / 1000;
            System.out.println("|-结果:" + result);
            System.out.println("|_耗时:" + duration);
            System.out.println();
        } catch (Exception e) {
            System.out.println("执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    Object test();
}

