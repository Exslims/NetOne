package netcracker.app.wf.client.admin.mvp.view.widgets.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.client.admin.mvp.view.widgets.AddUserView;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.style.GwtResource;

/**
 * Created by ���������� on 03.12.2015.
 */
public class AddUserImpl extends Composite implements AddUserView {
    private AddUserPresenter presenter;
    @Override
    public void setPresenter(AddUserPresenter presenter) {
        this.presenter = presenter;
    }

    interface AddUserImplUiBinder extends UiBinder<Widget, AddUserImpl> {
    }

    private static AddUserImplUiBinder ourUiBinder = GWT.create(AddUserImplUiBinder.class);

    @UiField
    GwtResource res;
    @UiField
    TextBox loginField;
    @UiField
    TextBox passwordField;
    @UiField
    TextBox nameField;
    @UiField
    TextBox emailField;
    @UiField
    TextBox addressField;
    @UiField
    TextBox countryField;
    @UiField
    TextBox javaslField;


    /**
     * Callback panels
     */

    @UiField
    HorizontalPanel succesPanel;
    @UiField
    HorizontalPanel errorPanel;


    @UiField
    Button submitButton;

    public AddUserImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        res.style().ensureInjected();
    }

    @UiHandler("submitButton")
    void onClickSubmit(ClickEvent event){
        User user = new User();
        user.setLogin(loginField.getText());
        user.setPassword(passwordField.getText());
        user.setName(nameField.getText());
        user.setEmail(emailField.getText());
        user.setAddress(addressField.getText());
        user.setCountry(countryField.getText());
        user.setJavaSkills(javaslField.getText());
        presenter.addUser(user);

    }


    @Override
    public void setMessageType(boolean var) {
        if(var){
            this.succesPanel.setVisible(true);
        }else {
            this.errorPanel.setVisible(true);
        }
    }
}