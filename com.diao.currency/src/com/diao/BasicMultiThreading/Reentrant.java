package com.diao.BasicMultiThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ZhanpengDiao on 18/7/17.
 */
public class Reentrant {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    public static void increment() {

        lock.lock(); // t1 and t2 will not run this at the same time
        try {
            for (int i = 0; i < 1000; i++) { // could cause deadlock
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            increment();
        });

        Thread t2 = new Thread(() -> {
            increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.print("Counter is: " + counter);
    }
}
