package netcracker.app.wf.client.admin.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.TaskService;
import netcracker.app.wf.client.admin.mvp.view.widgets.TasksView;
import netcracker.app.wf.back.model.Task;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���������� on 04.12.2015.
 */
public class TasksActivity extends AbstractActivity implements TasksView.TasksPresenter {
    private ClientFactory clientFactory;
    private TasksView view;
    private TaskService service;

    public TasksActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        GWT.log("Start Tasks Activity");
        view = clientFactory.getTasksView();
        view.setPresenter(this);
        panel.setWidget(view.asWidget());

        service = GWT.create(TaskService.class);

        service.getAllTasks(new MethodCallback<List<Task>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<Task> tasks) {
                view.updateTable(tasks);
            }
        });
    }

    @Override
    public void updateTableByTitle(String token) {
        service.getTaskByTitle(token, new MethodCallback<List<Task>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<Task> tasks) {
                view.updateTable(tasks);
            }
        });
    }

    @Override
    public void updateTableById(String token) {
        service.getTaskById(Integer.parseInt(token), new MethodCallback<Task>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Task task) {
                List<Task> tasks = new ArrayList<Task>();
                tasks.add(task);

                view.updateTable(tasks);
            }
        });
    }

    @Override
    public void showAll() {
        service.getAllTasks(new MethodCallback<List<Task>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<Task> tasks) {
                view.updateTable(tasks);
            }
        });
    }
}
