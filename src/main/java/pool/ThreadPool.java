package pool;

import blockingqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int procCount = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(procCount);

    public ThreadPool() {
        for (int i = 0; i < procCount; i++) {
            int finalI = i;
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                                System.out.println("Hey! Thread " + finalI + " is running!");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                System.out.println("Whoa! Thread "
                                        + finalI + " has been terminated.");
                            }
                        }
                    }
            );
            threads.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public static void main(String[] args) {
    }
}