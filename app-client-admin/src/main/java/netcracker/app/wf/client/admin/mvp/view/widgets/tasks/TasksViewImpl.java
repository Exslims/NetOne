package netcracker.app.wf.client.admin.mvp.view.widgets.tasks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.mvp.view.widgets.TasksView;
import netcracker.app.wf.back.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TasksViewImpl extends Composite implements TasksView {

    private TasksView.TasksPresenter presenter;

    interface TasksViewUiBinder extends UiBinder<Widget, TasksViewImpl> {
    }

    private static TasksViewUiBinder ourUiBinder = GWT.create(TasksViewUiBinder.class);

    private HashMap<Task,User> currentPairs;
    private List<Task> currentTasks;

    @UiField
    FlexTable flexTable;
    @UiField
    Label statusField;
    @UiField
    VerticalPanel accordion;

    @SuppressWarnings("all")
    public TasksViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));

        flexTable.setStyleName("flexTable");
        statusField.setStyleName("status");

        currentTasks = new ArrayList<>();
    }

//    @UiHandler("searchByTitleButton")
//    void onClickName(ClickEvent event){
//        presenter.updateTableByTitle(titleField.getText());
//    }
//    @UiHandler("searchByIdButton")
//    void onClickId(ClickEvent event){
//        presenter.updateTableById(idField.getText());
//    }
//    @UiHandler("showAllButton")
//    void onClickAll(ClickEvent event){
//        presenter.showAll();
//    }
//
//    @UiHandler("flexTable")
//    void onClickTable(ClickEvent event){
//        int rowIndex = flexTable.getCellForEvent(event).getRowIndex();
//        if(rowIndex != 0){
//            fillAccordion(rowIndex);
//        }
//    }


    @Override
    public void updateTable(HashMap<Task,User> pairs) {
        this.currentPairs = pairs;

        statusField.setText(String.valueOf(currentPairs.size()));
        flexTable.removeAllRows();
        flexTable.setText(0, 0, "Id");
        flexTable.setText(0, 1, "Title");
        flexTable.setText(0, 2, "Description");
        flexTable.setText(0, 3, "Notification Date");
        flexTable.setText(0, 4, "Target");
        flexTable.setText(0, 5, "");

        currentTasks.clear();
        for (Task task : currentPairs.keySet()) {
            currentTasks.add(task);

            final int rowCount = flexTable.getRowCount();
            flexTable.setText(rowCount,0,String.valueOf(task.getId()));
            flexTable.setText(rowCount,1,task.getTitle());
            flexTable.setText(rowCount,2,task.getDescription());
            flexTable.setText(rowCount,3,String.valueOf(task.getNotificationDate()));
            flexTable.setText(rowCount,4,currentPairs.get(task).getLogin());

            HorizontalPanel utilsPanel = new HorizontalPanel();

            Button removeButton = new Button("Delete");

            removeButton.addClickHandler(new CustomClickHandler(currentPairs,task));
            removeButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    flexTable.removeRow(rowCount);
                }
            });
            utilsPanel.add(removeButton);
            flexTable.setWidget(rowCount,5,utilsPanel);
        }
    }

    @SuppressWarnings("all")
    private void fillAccordion(int userIndex){

        accordion.clear();
        Label head = new Label("Info");
        head.setStyleName("accordion_header");
        accordion.add(head);

        Task task = currentTasks.get(userIndex - 1);

        VerticalPanel taskPanel = new VerticalPanel();
        taskPanel.addStyleName("accordion");
        Label title = new Label("Task info");
        title.addStyleName("accordion__title");

        taskPanel.add(new Label("Title:"));
        Label taskTitle = new Label(task.getTitle());
        taskTitle.addStyleName("accordion__copy");
        taskPanel.add(taskTitle);


        taskPanel.add(new Label("Description:"));
        Label content = new Label(task.getDescription());
        content.addStyleName("accordion__copy");
        taskPanel.add(content);

        accordion.add(taskPanel);

        User user = currentPairs.get(task);

//        VerticalPanel userPanel = new VerticalPanel();
//        taskPanel.addStyleName("accordion");
//        Label title = new Label(task.getTitle());
//        title.addStyleName("accordion__title");
//        Label content = new Label(task.getDescription());
//        content.addStyleName("accordion__copy");
//        taskPanel.add(title);
//        taskPanel.add(content);
    }

    @Override
    public void setPresenter(TasksPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setStatus(String var) {
        statusField.setText(var);
    }

    class CustomClickHandler implements ClickHandler{
        private HashMap<Task,User> pairs;
        private Task task;

        public CustomClickHandler(HashMap<Task, User> pairs, Task task) {
            this.pairs = pairs;
            this.task = task;
        }

        @Override
        public void onClick(ClickEvent clickEvent) {
            User user = pairs.get(task);
            user.getTasks().remove(task);
            pairs.remove(task);

            presenter.updateUser(user);

        }
    }
}