package com.diao.BasicMultiThreading;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhanpengDiao on 19/7/17.
 */

// specify what type of obj want to return
class Processor3 implements Callable<String>{
    private int id;

    public Processor3(int id) {
        this.id = id;
    }

    public String call() throws InterruptedException {
        Thread.sleep(1000);
        return "id: " + id;
    }
}

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> lst = new ArrayList<>(); // result of callable is stored in Future obj
        for(int i = 0; i < 5; i ++) {
            Future<String> future = executorService.submit(new Processor3(i + 1));
                    lst.add(future);
        }

        for(Future future: lst) {
            System.out.println(future.get());
        }
    }
}
