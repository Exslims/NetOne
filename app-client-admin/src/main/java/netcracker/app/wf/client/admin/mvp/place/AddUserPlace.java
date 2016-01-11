package netcracker.app.wf.client.admin.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Created by ���������� on 06.12.2015.
 */
public class AddUserPlace extends Place {
    private static final String ADD_USER_URL = "adduser";

    @Prefix(value = ADD_USER_URL)
    public static class Tokenizer implements PlaceTokenizer<AddUserPlace> {
        @Override
        public AddUserPlace getPlace(String token) {
            return new AddUserPlace();
        }

        @Override
        public String getToken(AddUserPlace place) {
            return "";
        }
    }
}
