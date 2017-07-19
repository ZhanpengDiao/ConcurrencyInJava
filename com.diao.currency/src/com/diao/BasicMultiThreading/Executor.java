package com.diao.BasicMultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZhanpengDiao on 19/7/17.
 */

class worker implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 5; i ++) {
            System.out.println(i + " ...");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Executor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int j = 0; j < 4; j++) {
            executorService.submit(new worker());
        }
    }
}
