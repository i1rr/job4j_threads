package blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int limit;
    private int count = 0;

    public SimpleBlockingQueue(int boundSize) {
        this.limit = boundSize;
    }

    public synchronized int getQueueLength() {
        int rsl = 0;
        for (T s : queue) {
            rsl++;
        }
        return rsl;
    }

    public synchronized void offer(T value) {
        while (count == limit) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(value);
        count++;
        this.notifyAll();
    }

    public synchronized T poll() {
        while (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        count--;
        this.notifyAll();
            return queue.poll();
        }
}