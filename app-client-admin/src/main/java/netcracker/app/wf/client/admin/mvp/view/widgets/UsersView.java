package netcracker.app.wf.client.admin.mvp.view.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import netcracker.app.wf.back.model.User;

import java.util.List;

/**
 * Created by ���������� on 02.12.2015.
 */
public interface UsersView extends IsWidget {
    void updateTable(List<User> users);

    void setPresenter(UsersPresenter presenter);

    public interface UsersPresenter{
        void updateTableByName(String token);
        void updateTableByNameLike(String token);
        void updateTableByLoginLike(String token);
        void updateTableByEmailLike(String token);
        void deleteUser(User user);
        void showAll();
    }
}
