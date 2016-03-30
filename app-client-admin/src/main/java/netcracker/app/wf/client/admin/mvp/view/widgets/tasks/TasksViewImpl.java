package netcracker.app.wf.client.admin.mvp.view.widgets.tasks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
        accordion.setStyleName("accordion");

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
    @UiHandler("flexTable")
    void onClickTable(ClickEvent event){
        int rowIndex = flexTable.getCellForEvent(event).getRowIndex();
        if(rowIndex != 0){
            fillAccordion(rowIndex);
        }
    }


    @Override
    public void updateTable(HashMap<Task,User> pairs) {
        this.currentPairs = pairs;

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


        User user = currentPairs.get(task);

        VerticalPanel userPanel = new VerticalPanel();
            userPanel.addStyleName("accordion");
            Label userInfo = new Label("User info");
            userInfo.addStyleName("accordion__title");
            userPanel.add(userInfo);

        userPanel.add( new Label("Login:"));
        Label userLogin = new Label(user.getLogin());
        userLogin.addStyleName("accordion__copy");
        userPanel.add(userLogin);

        userPanel.add( new Label("Name:"));
        Label userName = new Label(user.getName());
        userName.addStyleName("accordion__copy");
        userPanel.add(userName);

        userPanel.add( new Label("Email:"));
        Label userEmail = new Label(user.getEmail());
        userEmail.addStyleName("accordion__copy");
        userPanel.add(userEmail);

        userPanel.add( new Label("Java Skills:"));
        Label userSkills = new Label(user.getJavaSkills());
        userSkills.addStyleName("accordion__copy");
        userPanel.add(userSkills);


        accordion.add(userPanel);

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