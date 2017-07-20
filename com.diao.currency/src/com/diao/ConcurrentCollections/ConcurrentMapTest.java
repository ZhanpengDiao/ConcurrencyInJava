package com.diao.ConcurrentCollections;

/**
 * Created by ZhanpengDiao on 20/7/17.
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class FirstMWorker implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public FirstMWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            map.put("B", 1);
            map.put("H", 2);
            Thread.sleep(1000);
            map.put("F", 3);
            map.put("A", 4);
            Thread.sleep(1000);
            map.put("E", 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class SecondMWorker implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public SecondMWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("E"));
            System.out.println(map.get("C"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class ConcurrentMapTest {

    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        new Thread(new FirstMWorker(map)).start();
        new Thread(new SecondMWorker(map)).start();

    }
}
