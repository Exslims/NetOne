package netcracker.app.wf.client.user.mvp.view.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.back.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ���������� on 03.12.2015.
 */
public interface TasksView extends IsWidget {
    void updateTable(List<Task> tasks);
    void setPresenter(TasksPresenter presenter);
    void setStatus(String token);
    public interface TasksPresenter{
        void getAllTasks();
    }
}
