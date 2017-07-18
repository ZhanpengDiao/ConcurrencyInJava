package com.diao.BasicMultiThreading;

/**
 * Created by ZhanpengDiao on 18/7/17.
 */
class Processor {

    public void produce() throws InterruptedException {
        // this: locking
        synchronized (this) {
            System.out.println("we are in the producer method ...");
            wait(); // intrinsic lock release, wait for notify
            System.out.println("again producer method ...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("consume method wakes up!");
        synchronized (this) {
            System.out.println("consumer method ...");
            notify(); // notify all waiting threads
            Thread.sleep(3000); // notify() waits for all tasks finish
        }
    }

}

public class WaitAndNotify {


    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
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
