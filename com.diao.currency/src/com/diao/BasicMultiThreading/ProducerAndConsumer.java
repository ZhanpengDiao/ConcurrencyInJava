package com.diao.BasicMultiThreading;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhanpengDiao on 18/7/17.
 */
class Processor2  {

    private List<Integer> lst = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BUTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {

        synchronized (lock) {
            while(true) {
                if (lst.size() == LIMIT) {
                    System.out.println("waiting for removing items from the list ...");
                    lock.wait(); // if simply call wait(), it response to the current obj, not lock
                } else {
                    lst.add(value);
                    System.out.println("adding: " + value + " ...");
                    value ++;
                    lock.notify(); // notify will continuously run until bumping wait()
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {

        synchronized (lock) {
            while(true) {
                if(lst.size() == BUTTOM) {
                    System.out.println("waiting for add items to the list ...");
                    lock.wait();
                } else {
                    System.out.println("removing: " + lst.remove(--value) + " ...");
                    lock.notify();
                }
                Thread.sleep(500);
            }

        }

    }

}

public class ProducerAndConsumer {

    static Processor2 processor = new Processor2();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

}
