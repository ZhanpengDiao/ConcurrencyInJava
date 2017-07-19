package com.diao.BasicMultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by ZhanpengDiao on 18/7/17.
 *
 * semaphore maintains a set of permits
 * acquire() -> if permit is available, go and take the lock
 * release() -> add a permit
 *
 * JUST KEEPS CONUT NUM AVAILABLE
 */

enum Downloader {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3, true); // (num of permits, fairness) // can have up to 3 permits

    public void downloadData() throws InterruptedException {
        semaphore.acquire(); // tell semaphore i would like to get a lock
        download();
        semaphore.release(); // teill sema to release the lock
    }

    private void download() throws InterruptedException {
        System.out.println("downloading data from the web ...");
        Thread.sleep(2000);
    }
}

public class SemaphoreTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < 20; i ++) {
            executorService.execute(() -> {
                try {
                    Downloader.INSTANCE.downloadData(); // with the help of INSTANCE, can access downloadData
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
