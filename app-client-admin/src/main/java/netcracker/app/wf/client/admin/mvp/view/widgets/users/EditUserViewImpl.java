package netcracker.app.wf.client.admin.mvp.view.widgets.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import netcracker.app.wf.back.model.Role;
import netcracker.app.wf.back.model.User;
import netcracker.app.wf.client.admin.mvp.view.widgets.EditUserView;

/**
 * Created by Константин on 31.01.2016.
 */
public class EditUserViewImpl extends Composite implements EditUserView {
    private EditUserPresenter presenter;

    interface EditUserViewImplUiBinder extends UiBinder<Widget, EditUserViewImpl> {
    }

    private static EditUserViewImplUiBinder ourUiBinder = GWT.create(EditUserViewImplUiBinder.class);

    private User user;

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
    Label checkPasswordLabel;
    @UiField
    Label checkPasswordLabel2;

    public EditUserViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        roleField.addItem("ADMIN");
        roleField.addItem("USER");

        loginField.setReadOnly(true);
    }
    @UiHandler("passwordField2")
    void onChangePassword2(ValueChangeEvent<String> event){
        if(passwordField.getText().equals(passwordField2.getText()) && passwordField2.getText().equals(passwordField.getText()) ){
            checkPasswordLabel2.setText("");
        }else {
            checkPasswordLabel2.setText("Passwords do not match");
        }
    }

    @UiHandler("submitButton")
    void onClickSubmit(ClickEvent event){

        if(checkFieldsByNotNull()){
            if(passwordField.getText().equals(passwordField2.getText())){
                user.setLogin(loginField.getText());
                user.setPassword(passwordField.getText());
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setAddress(addressField.getText());
                user.setCountry(countryField.getText());
                user.setJavaSkills(skillsField.getText());


                if(!user.getRoles().isEmpty()){
                    user.getRoles().iterator().next().setName("ROLE_" + roleField.getSelectedItemText());
                }else {
                    Role role = new Role();
                    role.setName("ROLE_" + roleField.getSelectedItemText());
                    user.addRole(role);
                }
                presenter.updateUser(user);
            }
        }

    }

    private boolean checkFieldsByNotNull(){
        boolean flag = true;
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
    public void setPresenter(EditUserPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setCurrentUser(User user) {
        this.user = user;
        fillAllField();
    }

    @Override
    public void setStatus(String token) {
        statusLabel.setText(token);
    }

    @Override
    public void fillAllField() {
        loginField.setText(user.getLogin());
        passwordField.setText("");
        passwordField2.setText("");
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        addressField.setText(user.getAddress());
        countryField.setText(user.getCountry());
        skillsField.setText(user.getJavaSkills());

        if(user.getRoles().iterator().next().getName().contains("ADMIN")) {
            roleField.setSelectedIndex(0);
        }else {
            roleField.setSelectedIndex(1);
        }
    }
}