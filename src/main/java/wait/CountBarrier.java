package wait;

public class CountBarrier {
    private final Object monitor = this;

    private int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        total++;
        monitor.notifyAll();
    }

    public synchronized void await() {
        while (count < total) {
            try {
                monitor.wait();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
}