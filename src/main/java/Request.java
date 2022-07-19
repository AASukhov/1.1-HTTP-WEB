import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {

    private final RequestLine requestLine;
    private final Map<String,String> headers;
    private final List<String> body;


    public Request (List <String> strings) throws IOException {
        requestLine = new RequestLine(strings.remove(0));
        headers = strings.stream().takeWhile(string -> !string.equals("")).collect(Collectors.toMap(
                string -> string.split(":")[0].trim(),
                string -> string.split(":")[1].trim()
                ));
        body = strings.stream().dropWhile(string -> !string.equals("")).collect(Collectors.toList());
}

    public HashMap getQueryParams () throws URISyntaxException {
        HashMap <String, String> map = new HashMap<>();
        List<NameValuePair> params;
            params = URLEncodedUtils.parse(new URI(getRequestLine().getPath()), "UTF-8");
            for (NameValuePair param : params) {
                if (param.getName() != null && param.getValue() != null)
                    map.put(param.getName(), param.getValue());
        }
        return map;
    }


    public String getQueryParam (String name) throws URISyntaxException  {
        HashMap <String, String> map = getQueryParams();
        return map.get(name);
    }


    public RequestLine getRequestLine() {
        return requestLine;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public List<String> getBody() {
        return body;
    }
}
