package netcracker.app.wf.client.user;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import netcracker.app.wf.client.user.mvp.activity.HomeActivity;
import netcracker.app.wf.client.user.mvp.place.HomePlace;

/**
 * Created by Константин on 29.01.2016.
 */
public class ActivityMapperImpl implements ActivityMapper {
    private ClientFactory clientFactory;

    public ActivityMapperImpl(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if(place instanceof HomePlace){
            return new HomeActivity(clientFactory);
        }
        return null;
    }
}
