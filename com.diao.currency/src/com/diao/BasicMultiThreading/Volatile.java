package com.diao.BasicMultiThreading;

/**
 * Created by ZhanpengDiao on 17/7/17.
 */

class Worker implements Runnable {

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }

    // volatile: read from RAM instead of cache
    // performance lost
    private volatile boolean isTerminated = false;

    @Override
    public void run() {

        while(!isTerminated) {
            System.out.println("Hello from worker.");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class Volatile {

    public static void main(String[] args) throws InterruptedException {

        Worker worker1 = new Worker();

        Thread thread1 = new Thread(worker1);
        thread1.start();

        Thread.sleep(3000);

        worker1.setTerminated(true);
        System.out.println("Finished.");

    }

}
