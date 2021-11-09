package other;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
public final class UserStorage {

    @GuardedBy("this")
    private final List<User> userList = new ArrayList<>();

    public synchronized boolean add(User user) {
        for (User u : userList) {
            if (user.getId() == u.getId()) {
                return false;
            }
        }
        userList.add(user);
        return true;
    }

    public synchronized boolean update(User user) {
        return false;
    }

    public synchronized boolean delete(User user) {
        return userList.remove(user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<User> from = userList.stream().filter(usr -> usr.getId() == fromId).findFirst();
        Optional<User> to = userList.stream().filter(usr -> usr.getId() == toId).findFirst();
        if (from.isPresent() && to.isPresent()) {
            User f = from.get();
            if (f.getBalance() < amount) {
                System.out.println("Balance of userId " + fromId + " is not enough.");
                return false;
            }
            User t = to.get();
            f.setBalance(f.getBalance() - amount);
            t.setBalance(t.getBalance() + amount);
            return true;
        }
        System.out.println("Transaction unsuccessful.\n Please check user ID's.");
        return false;
    }
}
