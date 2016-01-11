package netcracker.app.wf.client.admin.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.UserService;
import netcracker.app.wf.client.admin.mvp.view.widgets.AddTaskView;
import netcracker.app.wf.back.model.User;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

/**
 * Created by ���������� on 07.12.2015.
 */
public class AddTaskActivity extends AbstractActivity implements AddTaskView.AddTaskPresenter {
    private ClientFactory clientFactory;
    private UserService service;
    private AddTaskView view;

    public AddTaskActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;

        service = GWT.create(UserService.class);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view = clientFactory.getAddTaskView();
        view.setPresenter(this);

        panel.setWidget(view);
    }


    @Override
    public void updateUser(User user) {
        service.updateUser(user, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.setMessageType(false);
            }

            @Override
            public void onSuccess(Method method, Void aVoid) {
                view.setMessageType(true);
            }
        });
    }

    @Override
    public void fillListBox() {
        service.getAllUsers(new MethodCallback<List<User>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<User> users) {
                view.addItems(users);
            }
        });
    }

}
