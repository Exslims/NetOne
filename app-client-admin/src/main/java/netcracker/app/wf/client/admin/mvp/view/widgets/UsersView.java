package netcracker.app.wf.client.admin.mvp.view.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import netcracker.app.wf.back.model.User;

import java.util.List;

/**
 * Created by ���������� on 02.12.2015.
 */
public interface UsersView extends IsWidget {
    void updateTable(List<User> users);

    /**
     * set message Load completed or Error loading
     * @param var message
     */
    void setMessage(String var);
    void setPresenter(UsersPresenter presenter);

    public interface UsersPresenter{
        void updateTableByName(String token);
        void updateTableById(String token);
        void showAll();
    }
}
