package netcracker.app.wf.client.admin.mvp.view.widgets.tasks;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.client.admin.mvp.view.widgets.AddTaskView;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.back.model.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ���������� on 07.12.2015.
 */
public class AddTaskViewImpl extends Composite implements AddTaskView {
    private AddTaskPresenter presenter;
    private Map<String,User> usersMap;

    @Override
    public void setPresenter(AddTaskPresenter presenter) {
        this.presenter = presenter;
    }

    interface AddTaskViewImplUiBinder extends UiBinder<Widget, AddTaskViewImpl> {
    }

    private static AddTaskViewImplUiBinder ourUiBinder = GWT.create(AddTaskViewImplUiBinder.class);


    @UiField
    TextBox titleField;
    @UiField
    TextArea descField;
    @UiField
    ListBox targetField;
    @UiField
    Label statusField;


    public AddTaskViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    @UiHandler("submitButton")
    void onClick(ClickEvent event){
        if(descField.getSelectionLength() < 255) {
            if (!titleField.getText().equals("") && !descField.getText().equals("")) {
                Task task = new Task();
                task.setTitle(titleField.getText());
                task.setDescription(descField.getText());
                task.setNotificationDate(new Date(System.currentTimeMillis()));

                User user = usersMap.get(targetField.getSelectedValue());
                user.addTask(task);
                presenter.updateUser(user);

                titleField.setText("");
                descField.setText("");
            }
        }else {
            statusField.setText("Max length of description = 255 ");
        }

    }

    @Override
    public void setUsers(List<User> users) {
        targetField.clear();
        usersMap = new HashMap<>();
        for (User user: users) {
            targetField.addItem(user.getLogin());
            usersMap.put(user.getLogin(),user);
        }
    }

    @Override
    public void setStatus(String token) {
        statusField.setText(token);
    }
}