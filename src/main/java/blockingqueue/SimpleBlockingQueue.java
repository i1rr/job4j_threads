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

    public SimpleBlockingQueue(int boundSize) {
        this.limit = boundSize;
    }

    public synchronized void offer(T value) {
        while (queue.size() == limit) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(value);
        this.notifyAll();
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        this.notifyAll();
            return queue.poll();
        }
}