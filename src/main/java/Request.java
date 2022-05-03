import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.util.List;

public class Request {
    public static MultiMap getQueryParams (String url) {
        MultiMap map = new MultiValueMap();
        List<NameValuePair> params;
        try {
            params = URLEncodedUtils.parse(new URI(url), "UTF-8");
            for (NameValuePair param : params) {
                if (param.getName() != null && param.getValue() != null)
                    map.put(param.getName(), param.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getQueryParamsPath(String url) {
        String result;
        int i = url.indexOf("?");
        if (i == -1) {
            return url;
        }
        result = url.substring(0, i);
        return result;
    }
}
