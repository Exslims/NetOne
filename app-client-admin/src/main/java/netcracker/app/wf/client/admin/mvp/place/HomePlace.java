package netcracker.app.wf.client.admin.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Created by ���������� on 04.12.2015.
 */
public class HomePlace extends Place {
    private static final String HOME_URL = "home";
    public HomePlace(){}

    @Prefix(value = HOME_URL)
    public static class Tokenizer implements PlaceTokenizer<HomePlace> {
        @Override
        public HomePlace getPlace(String token) {
            return new HomePlace();
        }

        @Override
        public String getToken(HomePlace place) {
            return "";
        }
    }
}
