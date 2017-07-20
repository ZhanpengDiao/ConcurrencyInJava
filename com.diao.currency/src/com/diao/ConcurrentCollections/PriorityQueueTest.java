package com.diao.ConcurrentCollections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by ZhanpengDiao on 19/7/17.
 */

class Person implements Comparable<Person> {

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.age;
    }

    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName());
    }
}

class FirstPWorker implements Runnable {

    private BlockingQueue<Person> blockingQueue;

    public FirstPWorker(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put(new Person(12, "Adam"));
            blockingQueue.put(new Person(45, "Joe"));
            blockingQueue.put(new Person(38, "Daniel"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(32, "Noel"));
            Thread.sleep(1000);
            blockingQueue.put(new Person(34, "kevin"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondPWorker implements Runnable {

    private BlockingQueue<Person> blockingQueue;

    public SecondPWorker(BlockingQueue<Person> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class PriorityQueueTest {

    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();

        new Thread(new FirstPWorker(queue)).start();
        new Thread(new SecondPWorker(queue)).start();

    }
}
