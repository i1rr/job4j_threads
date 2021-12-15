package pooh;

public class Req {

    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] lines = content.split(System.lineSeparator());
        String requestType = parseHttpReqType(lines[0]);
        String source = parseSourceName(lines[0]);
        return new Req(
                requestType,
                parsePoohMode(lines[0]),
                source,
                parseParam(lines, requestType, source));
    }

    private static String parseHttpReqType(String content) {
        return content.substring(0, 4).trim().equals("POST")
                ? "POST" : content.substring(0, 3).trim().equals("GET")
                ? "GET" : "";
    }

    private static String parsePoohMode(String content) {
        String temp = content.substring(0, 15).trim();
        return temp.contains("queue")
                ? "queue" : temp.contains("topic")
                ? "topic" : "";
    }

    private static String parseSourceName(String content) {
        return content.split("[/ ]")[3].trim();
    }

    private static String parseParam(String[] content, String reqType, String source) {
        if (reqType.equals("POST")) {
            return content[7];
        } else {
            String temp = content[0].split(source)[1];
            if (temp.charAt(0) == ' ') {
                return "";
            } else {
                System.out.println(temp);
                return temp.substring(1, temp.indexOf(" "));
            }
        }
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}