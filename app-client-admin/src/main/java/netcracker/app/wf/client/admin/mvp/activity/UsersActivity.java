package netcracker.app.wf.client.admin.mvp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import netcracker.app.wf.client.admin.ClientFactory;
import netcracker.app.wf.client.admin.UserService;
import netcracker.app.wf.client.admin.mvp.view.widgets.UsersView;
import netcracker.app.wf.back.model.User;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���������� on 04.12.2015.
 */
public class UsersActivity extends AbstractActivity implements UsersView.UsersPresenter {
    private ClientFactory clientFactory;
    private UsersView view;
    private UserService service;

    public UsersActivity(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        service = GWT.create(UserService.class);
    }


    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view = clientFactory.getUsersView();
        view.setPresenter(this);
        panel.setWidget(view.asWidget());

        showAll();
    }

    @Override
    public void updateTableByName(final String token) {
        if(!token.equals("")) {
            service.getUserByName(token, new MethodCallback<List<User>>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                    view.setStatusLabel("Cannot load users with name[" + token + "*] " + throwable.getMessage());
                }

                @Override
                public void onSuccess(Method method, List<User> users) {
                    view.updateTable(users);
                    view.setStatusLabel("Loading users with name[" + token + "*] successfully completed");
                }
            });
        }
    }

    @Override
    public void updateTableByNameLike(final String token) {
        if(!token.equals("")) {
            service.getUserByNameLike(token, new MethodCallback<List<User>>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                    view.setStatusLabel("Cannot load users with name[" + token + "*] " + throwable.getMessage());
                }

                @Override
                public void onSuccess(Method method, List<User> users) {
                    view.updateTable(users);
                    view.setStatusLabel("Loading users with name[" + token + "*] successfully completed");
                }
            });
        }
    }

    @Override
    public void updateTableByLoginLike(final String token) {
        if(!token.equals("")) {
            service.getUserByLoginLike(token, new MethodCallback<List<User>>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                    view.setStatusLabel("Cannot load users with login[" + token + "*] " + throwable.getMessage());
                }

                @Override
                public void onSuccess(Method method, List<User> users) {
                    view.updateTable(users);
                    view.setStatusLabel("Loading users with login[" + token + "*] successfully completed");
                }
            });
        }
    }

    @Override
    public void updateTableByEmailLike(final String token) {
        if(!token.equals("")) {
            service.getUserByEmailLike(token, new MethodCallback<List<User>>() {
                @Override
                public void onFailure(Method method, Throwable throwable) {
                    view.setStatusLabel("Cannot load users with email[" + token + "*] " + throwable.getMessage());
                }

                @Override
                public void onSuccess(Method method, List<User> users) {
                    view.updateTable(users);
                    view.setStatusLabel("Loading users with email[" + token + "*] successfully completed");
                }
            });
        }
    }

    @Override
    public void deleteUser(final User user) {
        service.deleteUser(user, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.setStatusLabel("Cannot deleting user with login[" + user.getLogin() + "] " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, Void aVoid) {
                view.setStatusLabel("Deleting user with login[" + user.getLogin() + "] successfully completed");
            }
        });
    }


    @Override
    public void showAll() {
        service.getAllUsers(new MethodCallback<List<User>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.setStatusLabel("Cannot load all users: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<User> users) {
                view.updateTable(users);
                view.setStatusLabel("Loading all users successfully completed");
            }
        });
    }
}
