package netcracker.app.wf.client.admin.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.UserService;
import netcracker.app.wf.client.admin.style.GwtResource;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

/**
 * Created by ���������� on 02.12.2015.
 */
public class AppLayout extends Composite {
    private ClientFactory clientFactory;
    interface MainViewUiBinder extends UiBinder<Widget, AppLayout> {
    }

    private static MainViewUiBinder ourUiBinder = GWT.create(MainViewUiBinder.class);

    private UserService userService;

    @UiField
    Label loggedUser;
    @UiField
    SimplePanel appContent;
    public AppLayout(ClientFactory clientFactory) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.clientFactory = clientFactory;

        userService = GWT.create(UserService.class);
        userService.getLoggedUser(new MethodCallback<User>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                loggedUser.setText("Error");
            }

            @Override
            public void onSuccess(Method method, User user) {
                loggedUser.setText("Hello: " + user.getLogin());
            }
        });
    }

    public SimplePanel getAppContentHolder(){
        return this.appContent;
    }
}