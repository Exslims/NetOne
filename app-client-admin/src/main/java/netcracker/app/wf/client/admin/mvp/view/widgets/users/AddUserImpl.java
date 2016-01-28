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
    Label checkLoginLabel;
    @UiField
    Label checkPasswordLabel;
    @UiField
    Label checkPasswordLabel2;
    @UiField
    Label checkNameLabel;
    @UiField
    Label checkEmailLabel;
    @UiField
    Label checkAddressLabel;
    @UiField
    Label checkCountryLabel;
    @UiField
    Label checkSkillsLabel;
    @UiField
    Label checkRoleLabel;

    public AddUserImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));

        roleField.addItem("ADMIN");
        roleField.addItem("USER");
    }

    @UiHandler("passwordField2")
    void onChangePassword2(ValueChangeEvent<String> event){
        if(passwordField.getText().equals(passwordField2.getText())){
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
        if(nameField.getText().equals("")){
            checkNameLabel.setText("Name field can't be empty");
            flag = false;
        }else {
            checkNameLabel.setText("");
        }
        if(emailField.getText().equals("")){
            checkEmailLabel.setText("Email field can't be empty");
            flag = false;
        } else {
            checkEmailLabel.setText("");
        }
        if(addressField.getText().equals("")){
            checkAddressLabel.setText("Address field can't be empty");
            flag = false;
        }else {
            checkAddressLabel.setText("");
        }
        if(countryField.getText().equals("")){
            checkCountryLabel.setText("Country field can't be empty");
            flag = false;
        }else {
            checkCountryLabel.setText("");
        }
        if(skillsField.getText().equals("")){
            checkSkillsLabel.setText("Java-skills field can't be empty");
            flag = false;
        }else {
            checkSkillsLabel.setText("");
        }
        if(roleField.getSelectedItemText().equals("")){
            checkRoleLabel.setText("Role field can't be empty");
            flag = false;
        }
        return flag;
    }

    @Override
    public void setCheckLoginLabel(String token){
        checkLoginLabel.setText(token);
    }
}