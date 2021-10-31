package concurrent;

public class ConsoleProgress {
    private int count = 0;
    private final String[] shar = {"-",
            "\\",
            "|",
            "/"
    };

    private String process() {
        if (count > 3) {
            count = 0;
        }
        return shar[count++];
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
                            break;
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}