package netcracker.app.wf.client.admin;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import netcracker.app.wf.client.admin.mvp.view.widgets.*;
import netcracker.app.wf.client.admin.mvp.view.widgets.home.HomeViewImpl;
import netcracker.app.wf.client.admin.mvp.view.widgets.tasks.AddTaskViewImpl;
import netcracker.app.wf.client.admin.mvp.view.widgets.tasks.TasksViewImpl;
import netcracker.app.wf.client.admin.mvp.view.widgets.users.AddUserImpl;
import netcracker.app.wf.client.admin.mvp.view.widgets.users.EditUserViewImpl;
import netcracker.app.wf.client.admin.mvp.view.widgets.users.UsersViewImpl;

/**
 * Created by ���������� on 04.12.2015.
 */
public class ClientFactoryImpl implements ClientFactory {
    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);

    private final HomeView homeView = new HomeViewImpl();
    private final UsersView usersView = new UsersViewImpl();
    private final TasksView tasksView = new TasksViewImpl();
    private final AddUserView addUserView = new AddUserImpl();
    private final AddTaskView addTaskView = new AddTaskViewImpl();
    private final EditUserView editUserView = new EditUserViewImpl();


    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public HomeView getHomeView() {
        return homeView;
    }

    @Override
    public UsersView getUsersView() {
        return usersView;
    }

    @Override
    public TasksView getTasksView() {
        return tasksView;
    }

    @Override
    public AddUserView getAddUserView() {
        return addUserView;
    }

    @Override
    public AddTaskView getAddTaskView() {
        return addTaskView;
    }

    @Override
    public EditUserView getEditUserView() {
        return editUserView;
    }
}
