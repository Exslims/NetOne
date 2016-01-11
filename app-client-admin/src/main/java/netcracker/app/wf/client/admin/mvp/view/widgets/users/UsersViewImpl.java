package netcracker.app.wf.client.admin.mvp.view.widgets.users;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.client.admin.mvp.view.widgets.UsersView;
import netcracker.app.wf.back.model.User;

import java.util.List;

/**
 * Created by ���������� on 02.12.2015.
 */
public class UsersViewImpl extends Composite implements UsersView {
    private UsersView.UsersPresenter presenter;

    interface UsersViewUiBinder extends UiBinder<Widget, UsersViewImpl> {
    }

    private static UsersViewUiBinder ourUiBinder = GWT.create(UsersViewUiBinder.class);


    @UiField
    FlexTable usersTable;
    @UiField
    TextBox nameField;
    @UiField
    TextBox idField;
    @UiField
    Label callbackField;

    public UsersViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }


    @UiHandler("searchByNameButton")
    void onClickName(ClickEvent event){
        presenter.updateTableByName(nameField.getText());
    }
    @UiHandler("searchByIdButton")
    void onClickId(ClickEvent event){
        presenter.updateTableById(idField.getText());
    }
    @UiHandler("showAllButton")
    void onClickAll(ClickEvent event){
        presenter.showAll();
    }

    public void updateTable(List<User> users) {
        usersTable.removeAllRows();
        usersTable.setText(0, 0, "Id");
        usersTable.setText(0, 1, "Login");
        usersTable.setText(0, 2, "Name");
        usersTable.setText(0, 3, "Email");
        usersTable.setText(0,4,"Address");
        usersTable.setText(0,5,"Country");
        usersTable.setText(0,6,"Java skills");
        usersTable.setText(0,7,"Tasks count");

        usersTable.getRowFormatter().addStyleName(0,"th");

        for (User user : users) {
            int rowCount = usersTable.getRowCount();
            usersTable.setText(rowCount + 1,0,String.valueOf(user.getId()));
            usersTable.setText(rowCount + 1,1,user.getLogin());
            usersTable.setText(rowCount + 1,2,user.getName());
            usersTable.setText(rowCount + 1,3,user.getEmail());
            usersTable.setText(rowCount + 1,4,user.getAddress());
            usersTable.setText(rowCount + 1,5,user.getCountry());
            usersTable.setText(rowCount + 1,6,user.getJavaSkills());
            usersTable.setText(rowCount + 1,7,String.valueOf(user.getTasks().size()));
        }

    }

    @Override
    public void setMessage(String var) {
        callbackField.setText(var);
    }

    @Override
    public void setPresenter(UsersPresenter presenter) {
        this.presenter = presenter;
    }

}