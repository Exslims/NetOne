package netcracker.app.wf.client.admin;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import netcracker.app.wf.client.admin.mvp.view.widgets.*;

/**
 * Created by ���������� on 04.12.2015.
 */
public interface ClientFactory {
    public EventBus getEventBus();
    public PlaceController getPlaceController();

    public HomeView getHomeView();
    public UsersView getUsersView();
    public TasksView getTasksView();
    public AddUserView getAddUserView();
    public AddTaskView getAddTaskView();
    public EditUserVIew getEditUserView();

}
