public class RequestLine {
    private final String method;
    private final String protocol;
    private final String path;

    public RequestLine (String requestLine) {
        final String [] splitLine = requestLine.split(" ");

        if (splitLine.length != 3) {
            throw new IllegalArgumentException("Incorrect request line " + requestLine);
        }

        method = splitLine[0];
        path = splitLine[1];
        protocol = splitLine[2];
    }

    public String getMethod() {
        return method;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getPath() {
        return path;
    }
}
