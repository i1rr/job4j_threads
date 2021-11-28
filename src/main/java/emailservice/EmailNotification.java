package emailservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool;

    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    private boolean emailTo(User user) {
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

    private void close(ExecutorService pool) {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EmailNotification en = new EmailNotification();
        en.pool.submit(() -> {
            en.emailTo(new User("Vasilij", "vasja@gmail.com"));
            en.close(en.pool);
        });
    }
}
