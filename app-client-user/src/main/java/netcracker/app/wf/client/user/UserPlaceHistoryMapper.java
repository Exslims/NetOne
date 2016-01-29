package netcracker.app.wf.client.user;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import netcracker.app.wf.client.user.mvp.place.HomePlace;

/**
 * Created by Константин on 29.01.2016.
 */
@WithTokenizers({HomePlace.Tokenizer.class})
public interface UserPlaceHistoryMapper extends PlaceHistoryMapper {
}
