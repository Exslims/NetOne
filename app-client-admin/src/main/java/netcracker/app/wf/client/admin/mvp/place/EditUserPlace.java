package netcracker.app.wf.client.admin.mvp.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Created by Константин on 31.01.2016.
 */
public class EditUserPlace extends AbstractBasePlace {
    public EditUserPlace(String token){
        super(token);
    }

    @Prefix("edit-user")
    public static class Tokenizer implements PlaceTokenizer<EditUserPlace>{

        @Override
        public EditUserPlace getPlace(String s) {
            return new EditUserPlace(s);
        }

        @Override
        public String getToken(EditUserPlace editUserPlace) {
            return editUserPlace.getUrl();
        }
    }
}
