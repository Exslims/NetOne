package netcracker.app.wf.client.admin.mvp.view.widgets.users;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.client.admin.mvp.view.widgets.UsersView;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.style.GwtResource;

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
    FlexTable flexTable;
    @UiField
    Button searchByNameButton;
    @UiField
    Button searchByIdButton;
    @UiField
    Button showAllButton;
    @UiField
    TextBox nameField;
    @UiField
    TextBox idField;

    public UsersViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));

        flexTable.addStyleName("flexTable");
        searchByNameButton.addStyleName("button");
        searchByIdButton.addStyleName("button");
        showAllButton.addStyleName("button");
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
        flexTable.removeAllRows();
        flexTable.setText(0, 0, "Id");
        flexTable.setText(0, 1, "Login");
        flexTable.setText(0, 2, "Name");
        flexTable.setText(0, 3, "Email");
        flexTable.setText(0,4,"Address");
        flexTable.setText(0,5,"Country");
        flexTable.setText(0,6,"Java skills");
        flexTable.setText(0,7,"Tasks count");

        for (User user : users) {
            int rowCount = flexTable.getRowCount();
            flexTable.setText(rowCount + 1,0,String.valueOf(user.getId()));
            flexTable.setText(rowCount + 1,1,user.getLogin());
            flexTable.setText(rowCount + 1,2,user.getName());
            flexTable.setText(rowCount + 1,3,user.getEmail());
            flexTable.setText(rowCount + 1,4,user.getAddress());
            flexTable.setText(rowCount + 1,5,user.getCountry());
            flexTable.setText(rowCount + 1,6,user.getJavaSkills());
            flexTable.setText(rowCount + 1,7,String.valueOf(user.getTasks().size()));
        }

    }

    @Override
    public void setPresenter(UsersPresenter presenter) {
        this.presenter = presenter;
    }

}