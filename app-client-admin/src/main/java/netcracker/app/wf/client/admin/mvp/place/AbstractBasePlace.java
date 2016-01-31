package netcracker.app.wf.client.admin.mvp.place;

import com.google.gwt.place.shared.Place;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Константин on 31.01.2016.
 */
public abstract class AbstractBasePlace extends Place {

    private static final String VALIDATE_TOKENS_MESSAGE_PREFIX = "Missing token(s): ";

    private String url;
    private Map<String, String> params = new HashMap<String, String>();

    protected AbstractBasePlace(String url) {

        if (url == null || url.isEmpty()) {
            return;
        }

        this.url = url;

        List<String> list = Arrays.asList(url.split("&"));

        if (list.size() < 1) {
            return;
        }

        for (String listItem : list) {

            List<String> nvPair = Arrays.asList(listItem.split("="));

            if (nvPair.size() != 2) {
                continue;
            }

            params.put(nvPair.get(0), nvPair.get(1));
        }
    }

    public String getUrl() {
        return url;
    }

    public String getParameter(String name) {
        return params.get(name);
    }

    public boolean hasParameter(String name) {
        return params.containsKey(name);
    }
}
