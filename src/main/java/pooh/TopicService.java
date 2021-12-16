package pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentHashMap<
            String, ConcurrentLinkedQueue<String>>> topic =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String requestType = req.httpRequestType();
        String sourceName = req.getSourceName();
        String parameter = req.getParam();

        if ("POST".equals(requestType)) {
            topic.putIfAbsent("", new ConcurrentHashMap<>());
            AtomicReference<String> response = new AtomicReference<>("");
            topic.forEach((k, v) -> {
                if (v.get(sourceName) != null) {
                    v.get(sourceName).add(parameter);
                    response.set(response + k + ": " + parameter);
                }
            });
            return new Resp(parameter, "200");
        } else if ("GET".equals(requestType)) {
            topic.putIfAbsent(parameter, new ConcurrentHashMap<>());
            topic.get(parameter).putIfAbsent(sourceName, new ConcurrentLinkedQueue<>());
            ConcurrentLinkedQueue<String> tt = topic.get(parameter).get(sourceName);
            if (tt != null && !tt.isEmpty()) {
                return new Resp(tt.poll(), "200");
            }
        }
        return new Resp("", "204");
    }
}