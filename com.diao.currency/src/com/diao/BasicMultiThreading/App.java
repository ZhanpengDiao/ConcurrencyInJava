/**
 * Created by ZhanpengDiao on 17/7/17.
 * Runnable
 */
package com.diao.BasicMultiThreading;

class Runner1 extends Thread{

    @Override
    public void run() {
        // going to execute in a distinct thread
        for(int i = 0; i < 10; i++) {
            System.out.println("Runner1: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Runner2 extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Runner2: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class App {

    // main/application thread
    public static void main(String[] args) throws InterruptedException {

        // initiate thread
//        Thread thread1 = new Thread(new Runner1());
//        Thread thread2 = new Thread(new Runner2());
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();

        // start() will call the run() method within the argument
//        thread1.start();
//        thread2.start();
        runner1.start();
        runner2.start();

        // wait for the thread to die
        runner1.join();
        runner2.join();

        System.out.println("All tasks finished.");
    }
}

