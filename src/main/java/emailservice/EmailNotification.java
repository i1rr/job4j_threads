package emailservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification implements Runnable {
    private static ExecutorService pool =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final User user;

    public EmailNotification(User user) {
        this.user = user;
    }

    public boolean emailTo(User user) {
        if (user == null) {
            return false;
        }
        String subject = "Notification " + user.getName() + " to email " + user.getEmail();
        String body = "Add a new event to " + user.getName();
        send(subject, body, user.getEmail());
        return true;
    }

    private void send(String subject, String body, String email) {

    }

    private void close(User user, ExecutorService pool) {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        emailTo(user);
        close(user, pool);
    }

    public static void main(String[] args) {
        pool.submit(new EmailNotification(new User("Vasilij", "vasja@gmail.com")));
    }
}
