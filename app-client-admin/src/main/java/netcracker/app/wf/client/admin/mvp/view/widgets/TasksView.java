package netcracker.app.wf.client.admin.mvp.view.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.back.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ���������� on 03.12.2015.
 */
public interface TasksView extends IsWidget {
    void updateTable(HashMap<Task,User> pairs);
    void setPresenter(TasksPresenter presenter);
    void setStatus(String var);
    public interface TasksPresenter{
        void updateUser(User user);
        void updateTableView();
    }
}
