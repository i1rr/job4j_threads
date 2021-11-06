package concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed, String filename) {
        this.url = url;
        this.speed = speed;
        this.fileName = filename;
    }

    private static void argsValidator(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long bytesWritten = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesWritten += bytesRead;
                if (bytesWritten > speed) {
                    long timeSpent = System.currentTimeMillis() - startTime;
                    if (timeSpent < 1000) {
                        try {
                            Thread.sleep(1000 - timeSpent);
                        } catch (InterruptedException iE) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    bytesWritten = 0;
                    startTime = System.currentTimeMillis();
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        argsValidator(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }
}