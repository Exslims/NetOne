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
    ListBox listField;

    /**
     * Callback panels
     */

    @UiField
    HorizontalPanel succesPanel;
    @UiField
    HorizontalPanel errorPanel;

    public AddTaskViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    @UiHandler("submitButton")
    void onClick(ClickEvent event){
        Task task = new Task();
        task.setTitle(titleField.getText());
        task.setDescription(descField.getText());
        task.setNotificationDate(new Timestamp(System.currentTimeMillis()));

        User user = usersMap.get(listField.getSelectedValue());
        user.addTask(task);
        presenter.updateUser(user);
    }

    @Override
    public void addItems(List<User> users) {
        usersMap = new HashMap<>();
        for (User user: users) {
            listField.addItem(user.getLogin());
            usersMap.put(user.getLogin(),user);
        }
    }

    @Override
    public void setMessageType(boolean var) {
        if(var){
            succesPanel.setVisible(true);
        }else {
            errorPanel.setVisible(true);
        }
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        listField.clear();
        presenter.fillListBox();
    }
}