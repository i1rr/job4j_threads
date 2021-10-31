package concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.print("\r" + count++);
                    }
                }
        );

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
