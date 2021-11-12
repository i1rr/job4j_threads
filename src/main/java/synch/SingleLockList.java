package synch;

import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = (List) list.clone();
    }

    public void add(T value) {
    }

    public T get(int index) {
        return null;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.array).iterator();
    }
}
