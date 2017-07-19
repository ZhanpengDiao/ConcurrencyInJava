package com.diao.ConcurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ZhanpengDiao on 19/7/17.
 */

class FirstWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;
    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        int counter = 0;

        while(true) {
            try {
                blockingQueue.put(counter); // if the queue is full, this thread is going to wait
                System.out.println("putting items: " + counter + " to the queue ...");
                counter++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class SecondWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;
    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {

        while(true) {
            try {
                int number = blockingQueue.take(); // if the queue is empty, this thread is going to wait
                System.out.println("taking items: " + number + " from the queue ...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);

        FirstWorker firstWorker = new FirstWorker(blockingQueue);
        SecondWorker secondWorker = new SecondWorker(blockingQueue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }

}
