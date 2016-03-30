package netcracker.app.wf.client.user.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.user.ClientFactory;
import netcracker.app.wf.client.user.mvp.view.widgets.TasksView;
import netcracker.app.wf.client.user.service.user.UserService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AbstractActivity implements TasksView.TasksPresenter {
    private ClientFactory clientFactory;
    private TasksView view;
    private UserService service;

    public TasksActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;

        service = GWT.create(UserService.class);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view = clientFactory.getTasksView();
        view.setPresenter(this);
        panel.setWidget(view.asWidget());

        getAllTasks();
    }


    @Override
    public void getAllTasks() {
        service.getLoggedUser(new MethodCallback<User>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.setStatus("Can not get user: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, User user) {
                List<Task> tasks = new ArrayList<Task>();
                tasks.addAll(user.getTasks());

                view.updateTable(tasks);
                view.setStatus("Loading tasks successfully completed");
            }
        });
    }
}
