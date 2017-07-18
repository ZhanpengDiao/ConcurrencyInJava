package com.diao.BasicMultiThreading;

/**
 * Created by ZhanpengDiao on 17/7/17.
 */
public class Synchronized {

    private static int counter = 0;

    // thread related overhead
    // when executing the same method with synchronized, threads must communicate with each other.
    public static synchronized void increment() {
        ++counter;
    }

    public static void process() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i = 0; i < 100; i ++) {
                    increment();
                }
            }

        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i = 0; i < 100; i ++) {
                    increment();
                }
            }

        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        process();
        System.out.println(counter);
    }
}
