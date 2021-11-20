package blockingqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProduce10andAllTaken() throws InterruptedException {
        final ArrayList<Integer> buffer = new ArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 10).forEach(queue::offer);
                }
        );
        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void whenProduce10taken5() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 205; i++) {
                        sbq.offer(i);
                        System.out.println(">>> Producer: i=" + i);
                    }
                    System.out.println("Producer thread has finished its process.");
                }
        );

        Thread consumer = new Thread(
                () -> {
                    for (int i = 0; i < 200; i++) {
                        try {
                            sbq.poll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Consumer: i=" + i + " <<<");
                    }
                    System.out.println("Consumer thread has finished its process.");
                }
        );

        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertFalse(consumer.isAlive() && producer.isAlive());
    }
}