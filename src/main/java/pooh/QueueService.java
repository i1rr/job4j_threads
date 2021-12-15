package pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String requestType = req.httpRequestType();
        String sourceName = req.getSourceName();
        String parameter = req.getParam();

        if ("POST".equals(requestType)) {
            queue.putIfAbsent(sourceName, new ConcurrentLinkedQueue<>());
            queue.get(sourceName).add(parameter);
            return new Resp(parameter, "200");
        }

        if ("GET".equals(requestType)) {
            ConcurrentLinkedQueue<String> qq = queue.get(sourceName);
            if (qq != null && !qq.isEmpty()) {
                return new Resp(qq.poll(), "200");
            }
        }
        return new Resp("", "204");
    }
}