package other;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public final class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> userList = new HashMap<>();

    public synchronized boolean add(User user) {
        User current = userList.get(user.getId());
        if (current == null) {
            current = userList.put(user.getId(), user);
        }
        return current != null;
    }

    public synchronized boolean update(User user) {
        return userList.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return userList.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = userList.get(fromId);
        User to = userList.get(toId);
        if (from != null && to != null && from.getBalance() >= amount) {
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            return true;
        }
        return false;
    }
}
