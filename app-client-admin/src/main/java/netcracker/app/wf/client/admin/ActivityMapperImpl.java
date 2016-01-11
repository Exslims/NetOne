package netcracker.app.wf.client.admin;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import netcracker.app.wf.client.admin.mvp.activity.*;
import netcracker.app.wf.client.admin.mvp.place.*;

public class ActivityMapperImpl implements ActivityMapper {
    private ClientFactory clientFactory;

    public ActivityMapperImpl(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {
        if(place instanceof UsersPlace){
            return new UsersActivity(clientFactory);
        }else if(place instanceof TasksPlace){
            return new TasksActivity(clientFactory);
        }else if(place instanceof HomePlace){
            return new HomeActivity(clientFactory);
        }else if(place instanceof AddUserPlace){
            return new AddUserActivity(clientFactory);
        }
        else
        if(place instanceof AddTaskPlace){
            return new AddTaskActivity(clientFactory);
        }
        return null;
    }
}
