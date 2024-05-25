package org.example.fitnessstudiomanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.MembershipType;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Helper.Encrypt;
import org.example.fitnessstudiomanagement.Helper.Validation;
import org.example.fitnessstudiomanagement.Model.Account;

public class RegisterController {

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private DatePicker birthDate;
    @FXML
    private PasswordField passwordInit;
    @FXML
    private PasswordField passwordConfirm;

    public void onReturnButtonPressed(){
        SceneManager.getInstance().switchScene(SceneType.ADMIN);
    }
    public void onRegisterButtonPressed(ActionEvent actionEvent) {

        if (!Validation.validateName(name.getText())) {
            showAlert("Invalid name! Exists already or is Empty");
            clearAllFields();
            return;
        }
        if (!Validation.validateAddress(address.getText())) {
            showAlert("Invalid address!");
            clearAllFields();
            return;
        }
        if (!Validation.validatePhone(phone.getText())) {
            showAlert("Invalid phone number!");
            clearAllFields();
            return;
        }
        if (!Validation.validateBirthDate(birthDate.getValue())) {
            showAlert("Invalid birth date! Must be only numbers");
            clearAllFields();
            return;
        }
        if (!Validation.validatePassword(passwordInit.getText(), passwordConfirm.getText())) {
            showAlert("Invalid password!");
            clearAllFields();
            return;
        }

        System.out.println("Validation successful");
        addUser();
        clearAllFields();
        SceneManager.getInstance().switchScene(SceneType.ADMIN);

    }

    private void addUser() {
        System.out.println("Adding user");
        Account account = new Account(
                name.getText(),
                address.getText(),
                phone.getText(),
                birthDate.getValue(),
                Encrypt.sha256(passwordConfirm.getText()),
                MembershipType.YEARLY,
                null
        );
        Database.getDatabase().addAccount(account);

    }

    private void clearAllFields() {
        name.clear();
        address.clear();
        phone.clear();
        birthDate.setValue(null);
        passwordInit.clear();
        passwordConfirm.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void onDarkMode(){

    }
}
