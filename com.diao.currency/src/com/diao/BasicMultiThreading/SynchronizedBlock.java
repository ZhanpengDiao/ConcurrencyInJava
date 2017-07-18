package com.diao.BasicMultiThreading;

/**
 * Created by ZhanpengDiao on 18/7/17.
 */
public class SynchronizedBlock {

    private static int count1 = 0;
    private static int count2 = 0;

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    // if add synchronized keyword in method declaring syntax
    // when one method is called, another cannot be called
    public synchronized static void add() {
        synchronized (lock1) {
            count1++;
        }
    }

    public synchronized static void addAgain() {
        synchronized (lock2) {
            count2++;
        }
    }

    public static void compute() {
        for(int i = 0; i < 100; i ++) {
            add();
            addAgain();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> compute());
        Thread thread2 = new Thread(() -> compute());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("COUNT1: " + count1 + ". COUNT2: " + count2 );
    }
}
