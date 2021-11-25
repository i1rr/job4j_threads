package pool;

import org.junit.Test;

public class ThreadPoolTest {

    @Test
    public void justTest() {
        ThreadPool tp = new ThreadPool();
        int count = 0;
        for (int i = 0; i < 1_000; i++) {
            tp.work(() -> System.out.println(Thread.currentThread().getId()));
            System.out.println("Count: " + count++);
        }
        tp.shutdown();
    }

}