import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(int defaultValue) {
        count.set(defaultValue);
    }

    public void increment() {
        Integer ref;
        do {
            ref = count.get();
        } while (!count.compareAndSet(ref, ref + 1));
    }

    public int get() {
        return count.get();
    }
}