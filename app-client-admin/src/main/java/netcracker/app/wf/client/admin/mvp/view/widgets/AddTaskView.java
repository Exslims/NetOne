package netcracker.app.wf.client.admin.mvp.view.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import netcracker.app.wf.back.model.User;

import java.util.List;

/**
 * Created by ���������� on 07.12.2015.
 */
public interface AddTaskView extends IsWidget {
    void setPresenter(AddTaskPresenter presenter);
    void setUsers(List<User> users);
    void setStatus(String token);
    interface AddTaskPresenter{
        void updateUser(User user);
        void fillListBox();
    }
}
