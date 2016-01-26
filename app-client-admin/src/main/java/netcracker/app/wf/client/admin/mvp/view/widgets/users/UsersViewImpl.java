package netcracker.app.wf.client.admin.mvp.view.widgets.users;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.back.model.Task;
import netcracker.app.wf.client.admin.mvp.view.widgets.UsersView;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.style.GwtResource;

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
    Button searchByIdButton;
    @UiField
    Button showAllButton;
    @UiField
    TextBox nameField;
    @UiField
    TextBox idField;

    @SuppressWarnings("all")
    public UsersViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));

        flexTable.setStyleName("flexTable");
        searchByNameButton.setStyleName("button");
        searchByIdButton.setStyleName("button");
        showAllButton.setStyleName("button");
        accordion.setStyleName("accordion");
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
    @UiHandler("flexTable")
    void onClickTable(ClickEvent event){
        int rowIndex = flexTable.getCellForEvent(event).getRowIndex();
        if(rowIndex != 0){
            fillAccordion(rowIndex);
        }
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
            flexTable.setText(rowCount,0,String.valueOf(user.getId()));
            flexTable.setText(rowCount,1,user.getLogin());
            flexTable.setText(rowCount,2,user.getName());
            flexTable.setText(rowCount,3,user.getEmail());
            flexTable.setText(rowCount,4,user.getAddress());
            flexTable.setText(rowCount,5,user.getCountry());
            flexTable.setText(rowCount,6,user.getJavaSkills());
            flexTable.setText(rowCount,7,String.valueOf(user.getTasks().size()));
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
        Label head = new Label(currentUserList.get(userIndex-1).getLogin() + " tasks");
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