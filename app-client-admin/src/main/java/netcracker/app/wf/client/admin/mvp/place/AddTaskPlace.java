package netcracker.app.wf.client.admin.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Created by ���������� on 07.12.2015.
 */
public class AddTaskPlace extends Place {
    private static final String ADD_TASK_URL = "addtask";

    @Prefix(value = ADD_TASK_URL)
    public static class Tokenizer implements PlaceTokenizer<AddTaskPlace>{

        @Override
        public AddTaskPlace getPlace(String token) {
            return new AddTaskPlace();
        }

        @Override
        public String getToken(AddTaskPlace place) {
            return "";
        }
    }
}
