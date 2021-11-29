package pools;

import emailservice.User;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindingIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T obj;

    public FindingIndex(T[] array, int from, int to, T obj) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    @Override
    protected Integer compute() {
        if (from - to < 10) {
            return simpleFind();
        }
        int mid = (from + to) / 2;

        FindingIndex<T> leftSort = new FindingIndex<>(array, from, mid, obj);
        FindingIndex<T> rightSort = new FindingIndex<>(array, mid + 1, to, obj);

        leftSort.fork();
        rightSort.fork();

        int left = leftSort.join();
        int right = rightSort.join();

        return returnRightIndexOrMinusOne(left, right);
    }

    public static <T> Integer find(T[] array, T obj) {
        ForkJoinPool fjp = new ForkJoinPool();
        return fjp.invoke(new FindingIndex<>(array, 0, array.length - 1, obj));
    }

    private int simpleFind() {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    private int returnRightIndexOrMinusOne(int value1, int value2) {
        return value1 >= 0 ? value1 : value2;
    }
}
