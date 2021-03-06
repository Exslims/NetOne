package netcracker.app.wf.client.admin.mvp.view.widgets.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.back.model.Role;
import netcracker.app.wf.client.admin.mvp.view.widgets.AddUserView;
import netcracker.app.wf.back.model.User;

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
    TextBox loginField;
    @UiField
    TextBox passwordField;
    @UiField
    TextBox passwordField2;
    @UiField
    TextBox nameField;
    @UiField
    TextBox emailField;
    @UiField
    TextBox addressField;
    @UiField
    TextBox countryField;
    @UiField
    TextBox skillsField;
    @UiField
    ListBox roleField;
    @UiField
    Label statusLabel;

    @UiField
    Label checkLoginLabel;
    @UiField
    Label checkPasswordLabel;
    @UiField
    Label checkPasswordLabel2;

    public AddUserImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        roleField.addItem("ADMIN");
        roleField.addItem("USER");
    }

    @UiHandler("passwordField2")
    void onChangePassword2(ValueChangeEvent<String> event){
        if(passwordField.getText().equals(passwordField2.getText()) && passwordField2.getText().equals(passwordField.getText()) ){
            checkPasswordLabel2.setText("");
        }else {
            checkPasswordLabel2.setText("Passwords do not match");
        }
    }
    @UiHandler("loginField")
    void onChangeLogin(ValueChangeEvent<String> event){
        presenter.checkAvailableLogin(loginField.getText());
    }

    @UiHandler("submitButton")
    void onClickSubmit(ClickEvent event){

        if(checkFieldsByNotNull()){
            presenter.checkAvailableLogin(loginField.getText());
            if(passwordField.getText().equals(passwordField2.getText()) && checkLoginLabel.getText().equals("")){
                User user = new User();
                user.setLogin(loginField.getText());
                user.setPassword(passwordField.getText());
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setAddress(addressField.getText());
                user.setCountry(countryField.getText());
                user.setJavaSkills(skillsField.getText());

                Role role = new Role();
                role.setName("ROLE_" + roleField.getSelectedItemText());
                user.addRole(role);
                presenter.addUser(user);

                loginField.setText("");
                passwordField.setText("");
                passwordField2.setText("");
                nameField.setText("");
                emailField.setText("");
                addressField.setText("");
                countryField.setText("");
                skillsField.setText("");
            }
        }

    }

    private boolean checkFieldsByNotNull(){
        boolean flag = true;
        if(loginField.getText().equals("")){
            checkLoginLabel.setText("Login field can't be empty");
            flag = false;
        }
        if(passwordField.getText().equals("")){
            checkPasswordLabel.setText("Password field can't be empty");
            flag = false;
        }else {
            checkPasswordLabel.setText("");
        }
        if(passwordField2.getText().equals("")){
            checkPasswordLabel2.setText("Password field can't be empty");
            flag = false;
        }else {
            checkPasswordLabel2.setText("");
        }
        return flag;
    }

    @Override
    public void setCheckLoginLabel(String token){
        checkLoginLabel.setText(token);
    }

    @Override
    public void setStatus(String token) {
        statusLabel.setText(token);
    }
}