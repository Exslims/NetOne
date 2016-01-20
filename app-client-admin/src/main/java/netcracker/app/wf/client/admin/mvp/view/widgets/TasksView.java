package netcracker.app.wf.client.admin.mvp.view.widgets;

import com.google.gwt.user.client.ui.IsWidget;
import netcracker.app.wf.back.model.Task;

import java.util.List;

/**
 * Created by ���������� on 03.12.2015.
 */
public interface TasksView extends IsWidget {
    void updateTable(List<Task> tasks);
    void setPresenter(TasksPresenter presenter);
    void setMessage(String var);
    public interface TasksPresenter{
        void updateTableByTitle(String token);
        void updateTableById(String token);
        void showAll();
    }
}
