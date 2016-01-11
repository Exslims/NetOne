package netcracker.app.wf.client.admin.mvp.view.widgets.tasks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.client.admin.mvp.view.widgets.TasksView;
import netcracker.app.wf.back.model.Task;

import java.util.List;

/**
 * Created by ���������� on 02.12.2015.
 */
public class TasksViewImpl extends Composite implements TasksView {

    private TasksView.TasksPresenter presenter;

    interface TasksViewUiBinder extends UiBinder<Widget, TasksViewImpl> {
    }

    private static TasksViewUiBinder ourUiBinder = GWT.create(TasksViewUiBinder.class);

    @UiField
    FlexTable tasksTable;
    @UiField
    TextBox titleField;
    @UiField
    TextBox idField;
    @UiField
    Button searchByTitleButton;
    @UiField
    Button searchByIdButton;

    public TasksViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("searchByTitleButton")
    void onClickName(ClickEvent event){
        presenter.updateTableByTitle(titleField.getText());
    }
    @UiHandler("searchByIdButton")
    void onClickId(ClickEvent event){
        presenter.updateTableById(idField.getText());
    }
    @UiHandler("showAllButton")
    void onClickAll(ClickEvent event){
        presenter.showAll();
    }

    @Override
    public void updateTable(List<Task> tasks) {
        tasksTable.removeAllRows();
        tasksTable.setText(0, 0, "Id");
        tasksTable.setText(0, 1, "Title");
        tasksTable.setText(0, 2, "Description");
        tasksTable.setText(0, 3, "Notification Date");

        tasksTable.getRowFormatter().addStyleName(0,"th");

        for (Task task : tasks) {
            int rowCount = tasksTable.getRowCount();
            tasksTable.setText(rowCount + 1,0,String.valueOf(task.getId()));
            tasksTable.setText(rowCount + 1,1,task.getTitle());
            tasksTable.setText(rowCount + 1,2,task.getDescription());
            tasksTable.setText(rowCount + 1,3,String.valueOf(task.getNotificationDate()));
        }
    }

    @Override
    public void setPresenter(TasksPresenter presenter) {
        this.presenter = presenter;
    }
}