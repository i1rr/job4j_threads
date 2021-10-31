package concurrent;

public class ConsoleProgress {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void setCount(int count) {
        this.count = count;
    }

    private String process() {
         String[] shar = {"-",
                "\\",
                "|",
                "/"
        };

         if (getCount() > 3) {
             setCount(0);
         }

         String rsl = shar[getCount()];
         increment();

        return rsl;
    }

    public static void main(String[] args) throws InterruptedException {
        ConsoleProgress cp = new ConsoleProgress();
        Thread progress = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.print("\rLoading: " + cp.process());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}