package concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) throws InterruptedException {

            Thread second = new Thread(
                    () -> System.out.println(Thread.currentThread().getName()));

            second.start();

            System.out.println(Thread.currentThread().getName());
    }
}
