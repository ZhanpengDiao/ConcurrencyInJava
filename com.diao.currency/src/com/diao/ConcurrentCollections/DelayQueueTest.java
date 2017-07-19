package com.diao.ConcurrentCollections;

/**
 * Created by ZhanpengDiao on 19/7/17.
 */

import javax.xml.bind.annotation.XmlType;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.DelayQueue;

class DelayedWorker implements Delayed {

    private long duration;
    private String message;

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) { // how much time we have to wait
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this.getDuration() < ((DelayedWorker) o).getDuration()) {
            return -1;
        } else if (this.getDuration() > ((DelayedWorker) o).getDuration()) {
            return 1;
        }
        return 0;
    }
}

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<DelayedWorker> delayedQueue = new DelayQueue<>();

        delayedQueue.put(new DelayedWorker(1000, "this is the first worker ..."));
        delayedQueue.put(new DelayedWorker(10000, "this is the second worker ..."));
        delayedQueue.put(new DelayedWorker(4000, "this is the third worker ..."));

        while(true) {
            System.out.println(delayedQueue.take().getMessage());
        }
    }

}
