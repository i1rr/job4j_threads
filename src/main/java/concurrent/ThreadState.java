package concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        System.out.println("First thread: " + first.getState());
        System.out.println("Second thread: " + second.getState());

        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println("First thread: " + first.getState());
            System.out.println("Second thread: " + second.getState());
        }
        System.out.println("First thread: " + first.getState());
        System.out.println("Second thread: " + second.getState());
        System.out.println("\nBoth threads are terminated.");
    }
}
