package com.diao.ConcurrentCollections;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZhanpengDiao on 19/7/17.
 */

class Worker implements Runnable{
    private int id;
    private CountDownLatch countDownLatch;
    private Random random;

    public Worker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();
    }

    private void doWork() throws InterruptedException {
        System.out.println("Thread with id: " + this.id + " start working ...");
        Thread.sleep(1000);
    }

}

public class Latch {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CountDownLatch latch = new CountDownLatch(5);
        for(int i = 0; i < 6; i ++) {
            executorService.execute(new Worker(i + 1, latch));
        }

        latch.await(); // wait until every single work to finish their work
        System.out.println("All tasks are done ...");
        executorService.shutdown();
    }
}
