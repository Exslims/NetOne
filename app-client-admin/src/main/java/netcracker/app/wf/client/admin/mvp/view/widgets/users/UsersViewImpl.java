package netcracker.app.wf.client.admin.mvp.view.widgets.users;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.back.model.Role;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.client.admin.mvp.view.widgets.UsersView;
import netcracker.app.wf.back.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by ���������� on 02.12.2015.
 */
public class UsersViewImpl extends Composite implements UsersView {
    private UsersView.UsersPresenter presenter;

    interface UsersViewUiBinder extends UiBinder<Widget, UsersViewImpl> {
    }

    private static UsersViewUiBinder ourUiBinder = GWT.create(UsersViewUiBinder.class);


    List<User> currentUserList;

    @UiField
    FlexTable flexTable;
    @UiField
    VerticalPanel accordion;
    @UiField
    Button searchByNameButton;
    @UiField
    Button searchByLoginButton;
    @UiField
    Button showAllButton;
    @UiField
    TextBox nameField;
    @UiField
    TextBox loginField;
    @UiField
    Label statusField;

    @SuppressWarnings("all")
    public UsersViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));

        flexTable.setStyleName("flexTable");
//        searchByNameButton.setStyleName("button");
//        searchByLoginButton.setStyleName("button");
//        showAllButton.setStyleName("button");
        accordion.setStyleName("accordion");
        statusField.setStyleName("status");
    }


    @UiHandler("searchByNameButton")
    void onClickName(ClickEvent event){
        presenter.updateTableByNameLike(nameField.getText());
    }
    @UiHandler("searchByLoginButton")
    void onClickId(ClickEvent event){
        presenter.updateTableByLoginLike(loginField.getText());
    }
    @UiHandler("showAllButton")
    void onClickAll(ClickEvent event){
        presenter.showAll();
    }
    @UiHandler("flexTable")
    void onClickTable(ClickEvent event){
        int rowIndex = flexTable.getCellForEvent(event).getRowIndex();
        if(rowIndex != 0){
            fillAccordion(rowIndex);
        }
    }

    public void updateTable(List<User> users) {
        flexTable.removeAllRows();
        flexTable.setText(0, 0, "Login");
        flexTable.setText(0, 1, "Name");
        flexTable.setText(0, 2, "Email");
        flexTable.setText(0,3,"Address");
        flexTable.setText(0,4,"Country");
        flexTable.setText(0,5,"Java skills");
        flexTable.setText(0,6,"Tasks count");
        flexTable.setText(0,7,"Role");
        flexTable.setText(0,8,"");

        for (final User user : users) {
            final int rowCount = flexTable.getRowCount();
            flexTable.setText(rowCount,0,user.getLogin());
            flexTable.setText(rowCount,1,user.getName());
            flexTable.setText(rowCount,2,user.getEmail());
            flexTable.setText(rowCount,3,user.getAddress());
            flexTable.setText(rowCount,4,user.getCountry());
            flexTable.setText(rowCount,5,user.getJavaSkills());
            flexTable.setText(rowCount,6,String.valueOf(user.getTasks().size()));

            Set<Role> userRoles = user.getRoles();
            String roleStr = "";
            for (Role role :userRoles) {
                roleStr += role.getName() + " ";
            }

            flexTable.setText(rowCount,7,roleStr);

            HorizontalPanel utilsPanel = new HorizontalPanel();


            Hyperlink hyperlink = new Hyperlink();
            hyperlink.setText("Edit");
            hyperlink.setTargetHistoryToken("edit-user:userid="+user.getId());

            Button removeButton = new Button("Delete");
            removeButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    presenter.deleteUser(user);
                    flexTable.removeRow(rowCount);
                }
            });
            utilsPanel.add(hyperlink);
            utilsPanel.add(removeButton);
            flexTable.setWidget(rowCount,8,utilsPanel);
        }

        currentUserList = users;
    }

    @Override
    public void setPresenter(UsersPresenter presenter) {
        this.presenter = presenter;
    }

    @SuppressWarnings("all")
    private void fillAccordion(int userIndex){
        accordion.clear();
        Label head = new Label(currentUserList.get(userIndex-1).getLogin() + "'s tasks");
        head.setStyleName("accordion_header");
        accordion.add(head);

        Set<Task> tasks = currentUserList.get(userIndex - 1).getTasks();
        for (Task task :tasks) {
            VerticalPanel vPanel = new VerticalPanel();
            vPanel.addStyleName("accordion");

            Label title = new Label(task.getTitle());
            title.addStyleName("accordion__title");

            Label content = new Label(task.getDescription());
            content.addStyleName("accordion__copy");

            vPanel.add(title);
            vPanel.add(content);

            accordion.add(vPanel);
        }
    }
    public void setStatusLabel(String token){
        statusField.setText(token);
    }

//    public static native void test()/*-{
//        $wnd.alert("inside");
//        var $title = $wnd('.js-title');
//        var copy   = '.js-copy';
//
//        $title.click(function () {
//            $wnd.next(copy).slideToggle();
//            $wnd.parent().siblings().children().next().slideUp();
//            return false;
//        });
//    }-*/;

}