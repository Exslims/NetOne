package netcracker.app.wf.client.admin.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.UserService;
import netcracker.app.wf.client.admin.mvp.view.widgets.EditUserView;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;


public class EditUserActivity extends AbstractActivity implements EditUserView.EditUserPresenter {
    private ClientFactory clientFactory;
    private EditUserView view;
    private UserService service;

    private int id;
    private User currentUser;

    public EditUserActivity(ClientFactory factory, String url){
        this.clientFactory = factory;
        String[] args = url.split("=");
        id = Integer.parseInt(args[1]);

        service = GWT.create(UserService.class);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view = clientFactory.getEditUserView();
        view.setPresenter(this);

        panel.setWidget(view);

        setCurrentUserOnView();

    }

    @Override
    public void updateUser(User user) {
        service.updateUser(user, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.setStatus("Can not update user: " + throwable.getMessage() );
            }

            @Override
            public void onSuccess(Method method, Void aVoid) {
                view.setStatus("User has been successfully updated");
            }
        });
    }

    @Override
    public void setCurrentUserOnView() {
        service.getUserById(id, new MethodCallback<User>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.setStatus("Can not get user: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, User user) {
                view.setCurrentUser(user);
            }
        });
    }
}
