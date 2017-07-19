package com.diao.ConcurrentCollections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZhanpengDiao on 19/7/17.
 */
class Worker2 implements Runnable{
    private int id;
    private CyclicBarrier cyclicBarrier;
    private Random random;

    public Worker2(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doWork() throws InterruptedException, BrokenBarrierException {
        System.out.println("Thread with id: " + this.id + " starts the task ...");
        Thread.sleep(random.nextInt(3000));
        System.out.println("Thread with id: " + this.id + " finished ...");
        cyclicBarrier.await();
        System.out.println("Thread with id: " + this.id + " after awake ...");
    }

    public String toString() {
        return "" + this.id;
    }
}

public class CyclicBarrierTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() { // this method is going to be called when counting down to 0
                System.out.println("All the tasks are finished ...");
            }
        });

        for(int i = 0; i < 5; i ++ ) {
            executorService.execute(new Worker2(i + 1, cyclicBarrier));
        }
        executorService.shutdown();
    }
}
