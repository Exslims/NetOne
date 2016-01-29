package netcracker.app.wf.client.user.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Created by Константин on 29.01.2016.
 */
public class HomePlace extends Place {
    private static final String HOME_URL = "home";
    public HomePlace(){}

    @Prefix(value = HOME_URL)
    public static class Tokenizer implements PlaceTokenizer<HomePlace>{

        @Override
        public HomePlace getPlace(String s) {
            return new HomePlace();
        }

        @Override
        public String getToken(HomePlace homePlace) {
            return "";
        }
    }
}
