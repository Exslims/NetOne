package netcracker.app.wf.client.admin.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 * Created by ���������� on 04.12.2015.
 */
public class TasksPlace extends Place {
    private static final String TASKS_URL = "tasks";
    public TasksPlace(){}

    @Prefix(value = TASKS_URL)
    public static class Tokenizer implements PlaceTokenizer<TasksPlace> {

        @Override
        public TasksPlace getPlace(String token) {
            return new TasksPlace();
        }

        @Override
        public String getToken(TasksPlace place) {
            return "";
        }
    }
}
