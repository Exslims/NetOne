package netcracker.app.wf.client.admin.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.UserService;
import netcracker.app.wf.client.admin.mvp.view.widgets.TasksView;
import netcracker.app.wf.back.model.Task;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by ���������� on 04.12.2015.
 */
public class TasksActivity extends AbstractActivity implements TasksView.TasksPresenter {
    private ClientFactory clientFactory;
    private TasksView view;
    private UserService service;
    List<User> currentUsers;

    public TasksActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;

        service = GWT.create(UserService.class);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view = clientFactory.getTasksView();
        view.setPresenter(this);
        panel.setWidget(view.asWidget());

        updateTableView();
    }

    @Override
    public void updateUser(final User user) {

        service.updateUser(user, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.setStatus("Can not update user");
            }

            @Override
            public void onSuccess(Method method, Void aVoid) {
                view.setStatus("User " + user.getTasks().size() + " was successfully updated");
            }
        });
    }

    @Override
    public void updateTableView() {
        HashMap<Task,User> pairs = new HashMap<>();


        service.getAllUsers(new MethodCallback<List<User>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.setStatus("Can not get users: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<User> users) {
                view.setStatus("Loading tasks successfully completed");
                HashMap<Task,User> pairs = new HashMap<>();

                for (User user : users) {
                    Set<Task> tasks = user.getTasks();
                    for (Task task :tasks) {
                        pairs.put(task,user);
                    }
                }
                view.updateTable(pairs);
            }
        });
    }


}
