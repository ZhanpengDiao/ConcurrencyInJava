/**
 * Created by ZhanpengDiao on 17/7/17.
 * Runnable
 */
package com.diao.BasicMultiThreading;

class Runner1 implements Runnable{

    @Override
    public void run() {
        // going to execute in a distinct thread
        for(int i = 0; i < 10; i++) {
            System.out.println("Runner1: " + i);
        }
    }

}

class Runner2 implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Runner2: " + i);
        }
    }

}

public class App {

    public static void main(String[] args) {

        // initiate thread
        Thread thread1 = new Thread(new Runner1());
        Thread thread2 = new Thread(new Runner2());

        // start() will call the run() method within the argument
        thread1.start();
        thread2.start();

    }
}

