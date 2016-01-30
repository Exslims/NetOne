package netcracker.app.wf.client.admin.mvp.view.widgets.tasks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.client.admin.mvp.view.widgets.TasksView;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.client.admin.style.GwtResource;

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
    FlexTable flexTable;
    @UiField
    TextBox titleField;
    @UiField
    TextBox idField;
    @UiField
    Button searchByTitleButton;
    @UiField
    Button searchByIdButton;
    @UiField
    Label statusField;

    @SuppressWarnings("all")
    public TasksViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));

        flexTable.setStyleName("flexTable");
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
        flexTable.removeAllRows();
        flexTable.setText(0, 0, "Id");
        flexTable.setText(0, 1, "Title");
        flexTable.setText(0, 2, "Description");
        flexTable.setText(0, 3, "Notification Date");

        for (Task task : tasks) {
            int rowCount = flexTable.getRowCount();
            flexTable.setText(rowCount + 1,0,String.valueOf(task.getId()));
            flexTable.setText(rowCount + 1,1,task.getTitle());
            flexTable.setText(rowCount + 1,2,task.getDescription());
            flexTable.setText(rowCount + 1,3,String.valueOf(task.getNotificationDate()));
        }
    }

    @Override
    public void setPresenter(TasksPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setMessage(String var) {
//        this.callbackField.setText(var);
    }
}