package other;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public final class User {
    private final int id;

    @GuardedBy("this")
    private int balance;

    public User(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized void setBalance(int balance) {
        this.balance = balance;
    }
}
