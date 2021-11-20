package blockingqueue;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

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