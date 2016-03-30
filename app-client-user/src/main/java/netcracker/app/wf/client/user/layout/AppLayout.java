package netcracker.app.wf.client.user.layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.user.service.user.UserService;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * Created by Константин on 29.01.2016.
 */
public class AppLayout extends Composite {
    interface AppLayoutUiBinder extends UiBinder<Widget, AppLayout> {
    }

    private static AppLayoutUiBinder ourUiBinder = GWT.create(AppLayoutUiBinder.class);

    @UiField
    Label loggedUser;
    @UiField
    SimplePanel appContent;

    public AppLayout() {
        initWidget(ourUiBinder.createAndBindUi(this));

        UserService service = GWT.create(UserService.class);
        service.getLoggedUser(new MethodCallback<User>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, User user) {
                loggedUser.setText(user.getLogin());
            }
        });
    }

    public SimplePanel getAppContent() {
        return appContent;
    }
}