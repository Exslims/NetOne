package netcracker.app.wf.client.admin.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.UserService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

/**
 * Created by ���������� on 02.12.2015.
 */
public class AppLayout extends Composite {
    interface MainViewUiBinder extends UiBinder<Widget, AppLayout> {
    }

    private static MainViewUiBinder ourUiBinder = GWT.create(MainViewUiBinder.class);

    private UserService userService;

    @UiField
    Label loggedUser;
    @UiField
    SimplePanel appContent;
    public AppLayout() {
        initWidget(ourUiBinder.createAndBindUi(this));

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