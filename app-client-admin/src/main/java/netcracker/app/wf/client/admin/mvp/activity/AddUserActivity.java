package netcracker.app.wf.client.admin.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.UserService;
import netcracker.app.wf.client.admin.mvp.view.widgets.AddUserView;
import netcracker.app.wf.back.model.User;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * Created by ���������� on 06.12.2015.
 */
public class AddUserActivity extends AbstractActivity implements AddUserView.AddUserPresenter {
    private ClientFactory clientFactory;
    private UserService service;
    private AddUserView view;
    public AddUserActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;

        service = GWT.create(UserService.class);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view = clientFactory.getAddUserView();
        view.setPresenter(this);

        panel.setWidget(view);

    }

    @Override
    public void addUser(User user) {
        service.addUser(user, new MethodCallback<Void>() {
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
}
