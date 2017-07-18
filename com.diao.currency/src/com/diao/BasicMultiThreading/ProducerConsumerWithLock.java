package com.diao.BasicMultiThreading;

/**
 * Created by ZhanpengDiao on 18/7/17`
 */
import sun.awt.windows.ThemeReader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLock {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void producer() throws InterruptedException {
        lock.lock(); // can lock many times
        System.out.println("producer method ...");
        condition.await(); // similar to wait()
        System.out.println("producer again ...");
        lock.unlock(); // call unlock as many time as lock
    }

    public static void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println("consumer method ...");
        condition.signal();
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
