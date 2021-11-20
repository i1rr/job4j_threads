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

    public SimpleBlockingQueue() {
        this.limit = 44;
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

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                this.wait();
        }
        this.notifyAll();
            return queue.poll();
        }

        public synchronized boolean isEmpty() {
        return queue.isEmpty();
        }
}