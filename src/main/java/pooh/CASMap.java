package pooh;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;

public class CASMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();
        String name = "weather";

        /* add if empty */
        queue.putIfAbsent(name, new ConcurrentLinkedQueue<>());

        /* put */
        queue.get(name).add("value");

        /* extract */
    }
}