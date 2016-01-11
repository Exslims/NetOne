package netcracker.app.wf.client.admin.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Created by ���������� on 04.12.2015.
 */
public class UsersPlace extends Place {
    private static final String USERS_URL = "users";
    public UsersPlace(){}

    @Prefix(value = USERS_URL)
    public static class Tokenizer implements PlaceTokenizer<UsersPlace>{

        @Override
        public UsersPlace getPlace(String token) {
            return new UsersPlace();
        }

        @Override
        public String getToken(UsersPlace place) {
            return "";
        }
    }
}
