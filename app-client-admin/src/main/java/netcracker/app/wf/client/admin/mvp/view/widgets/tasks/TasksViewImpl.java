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

import java.util.HashMap;
import java.util.List;

/**
 * Created by ���������� on 02.12.2015.
 */
public class TasksViewImpl extends Composite implements TasksView {

    private TasksView.TasksPresenter presenter;

    interface TasksViewUiBinder extends UiBinder<Widget, TasksViewImpl> {
    }

    private static TasksViewUiBinder ourUiBinder = GWT.create(TasksViewUiBinder.class);

    private HashMap<Task,User> pairs;

    @UiField
    FlexTable flexTable;
    @UiField
    Label statusField;

    @SuppressWarnings("all")
    public TasksViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));

        flexTable.setStyleName("flexTable");
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

    @Override
    public void updateTable(final HashMap<Task,User> pairs) {
        this.pairs = pairs;

        flexTable.removeAllRows();
        flexTable.setText(0, 0, "Id");
        flexTable.setText(0, 1, "Title");
        flexTable.setText(0, 2, "Description");
        flexTable.setText(0, 3, "Notification Date");
        flexTable.setText(0, 4, "Target");
        flexTable.setText(0, 5, "");

        for (final Task task : pairs.keySet()) {
            final int rowCount = flexTable.getRowCount();
            flexTable.setText(rowCount,0,String.valueOf(task.getId()));
            flexTable.setText(rowCount,1,task.getTitle());
            flexTable.setText(rowCount,2,task.getDescription());
            flexTable.setText(rowCount,3,String.valueOf(task.getNotificationDate()));
            flexTable.setText(rowCount,4,pairs.get(task).getLogin());

            HorizontalPanel utilsPanel = new HorizontalPanel();

            Button removeButton = new Button("Delete");
            removeButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    User user = pairs.get(task);
                    user.getTasks().remove(task);
                    pairs.remove(task);
                    flexTable.removeRow(rowCount);

                    presenter.updateUser(user);
                }
            });
            utilsPanel.add(removeButton);
            flexTable.setWidget(rowCount,5,utilsPanel);
        }
    }

    @Override
    public void setPresenter(TasksPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setStatus(String var) {
        statusField.setText(var);
    }
}