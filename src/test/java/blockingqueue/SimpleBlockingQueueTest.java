package blockingqueue;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProduce10taken5() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(7);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 11; i++) {
                        sbq.offer(i);
                    }
                }
        );

        Thread consumer = new Thread(
                () -> {
                    for (int i = 0; i < 6; i++) {
                        sbq.poll();
                    }
                }
        );

        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertThat(sbq.getQueueLength(), is(5));
    }
}