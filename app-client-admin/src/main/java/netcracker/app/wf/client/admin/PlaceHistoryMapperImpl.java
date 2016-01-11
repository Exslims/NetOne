package netcracker.app.wf.client.admin;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import netcracker.app.wf.client.admin.mvp.place.*;

/**
 * Created by ���������� on 04.12.2015.
 */
@WithTokenizers({UsersPlace.Tokenizer.class, TasksPlace.Tokenizer.class, HomePlace.Tokenizer.class, AddUserPlace.Tokenizer.class, AddTaskPlace.Tokenizer.class})
public interface PlaceHistoryMapperImpl extends PlaceHistoryMapper {
}
