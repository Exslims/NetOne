package netcracker.app.wf.client.user.mvp.view.widgets.tasks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.user.mvp.view.widgets.TasksView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Константин on 01.02.2016.
 */
public class TasksViewImpl extends Composite implements TasksView{
    private TasksView.TasksPresenter presenter;
    private List<Task> currentTasks;

    interface TasksViewImplUiBinder extends UiBinder<HTMLPanel, TasksViewImpl> {
    }

    private static TasksViewImplUiBinder ourUiBinder = GWT.create(TasksViewImplUiBinder.class);

    @SuppressWarnings("all")
    public TasksViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));

        flexTable.setStyleName("flexTable");
        statusField.setStyleName("status");
        accordion.setStyleName("accordion");
    }

    @UiField
    FlexTable flexTable;
    @UiField
    Label statusField;
    @UiField
    VerticalPanel accordion;

    @UiHandler("flexTable")
    void onClickTable(ClickEvent event){
        int rowIndex = flexTable.getCellForEvent(event).getRowIndex();
        if(rowIndex != 0){
            fillAccordion(rowIndex);
        }
    }
    @Override
    public void setPresenter(TasksPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateTable(List<Task> tasks) {
        currentTasks = tasks;

        flexTable.removeAllRows();
        flexTable.setText(0, 0, "Id");
        flexTable.setText(0, 1, "Title");
        flexTable.setText(0, 2, "Description");
        flexTable.setText(0, 3, "Notification Date");
        for (Task task : currentTasks) {

            final int rowCount = flexTable.getRowCount();
            flexTable.setText(rowCount,0,String.valueOf(task.getId()));
            flexTable.setText(rowCount,1,task.getTitle());
            flexTable.setText(rowCount,2,task.getDescription());
            flexTable.setText(rowCount,3,String.valueOf(task.getNotificationDate()));
        }
    }

    @SuppressWarnings("all")
    private void fillAccordion(int taskIndex){
        accordion.clear();
        Label head = new Label("Info");
        head.setStyleName("accordion_header");
        accordion.add(head);

        Task task = currentTasks.get(taskIndex - 1);

        VerticalPanel taskPanel = new VerticalPanel();
        taskPanel.addStyleName("accordion");
        Label taskInfo = new Label("Task info");
        taskInfo.addStyleName("accordion__title");
        taskPanel.add(taskInfo);

        taskPanel.add( new Label("Title:"));
        Label taskTitle = new Label(task.getTitle());
        taskTitle.addStyleName("accordion__copy");
        taskPanel.add(taskTitle);


        taskPanel.add(new Label("Description:"));

        TextArea descArea = new TextArea();
        descArea.setText(task.getDescription());
        Label content = new Label(task.getDescription());
        content.addStyleName("accordion__copy");
        taskPanel.add(content);

        accordion.add(taskPanel);
    }
    @Override
    public void setStatus(String var) {
        statusField.setText(var);
    }
}