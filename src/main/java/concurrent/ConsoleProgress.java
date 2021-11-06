package concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] shar = {"-",
                "\\",
                "|",
                "/"
        };

        int sharIndex = 0;

        while (!Thread.currentThread().isInterrupted()) {
            if (sharIndex == 4) {
                sharIndex = 0;
            }
            System.out.print("\rLoading: " + shar[sharIndex++]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread test = new Thread(new ConsoleProgress());
        test.start();
        Thread.sleep(5000);
        test.interrupt();
    }
}