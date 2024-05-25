package org.example.fitnessstudiomanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Helper.Encrypt;
import org.example.fitnessstudiomanagement.Helper.Validation;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Model.Account;

import java.time.LocalDate;


public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void onLoginButtonClick(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        usernameField.clear();
        passwordField.clear();

        if(Validation.isInvalidInput(username)){
            showAlert("Please enter a valid username");
            return;
        }
        else if(Validation.isInvalidInput(password)){
            showAlert("Please enter a password");
            return;
        }
        if(checkUserForAdmin(username, password)){
            SceneManager.getInstance().switchScene(SceneType.ADMIN);
        }else if(checkUserForClient(username, password)){
            SceneManager.getInstance().switchScene(SceneType.CLIENT);
        }
    }

    private boolean checkUserForClient(String username, String pass) {
        Account tryLogin = Database.getDatabase().findAccountByUsername(username);
        System.out.println("here");
        if (tryLogin == null) {
            showAlert("Invalid username or password");
            return false;
        }
        if(tryLogin.getMembership().getExpirationDate().isBefore(LocalDate.now())){
            showAlert("Expired Membership");
            return false;
        }
        if (!tryLogin.getPass().equals(Encrypt.sha256(pass))) {
            showAlert("Invalid username or password");
            return false;
        }
        if(tryLogin.getMembership().getExpirationDate().isBefore(LocalDate.now())){
            showAlert("Expired Membership");
            return false;
        }
        Data.loggedOnAccount = tryLogin;
        return true;
    }

    private boolean checkUserForAdmin(String username, String password) {
        return (Encrypt.sha256(username).equals(Data.Admin.getUsername())
                && Encrypt.sha256(password).equals(Data.Admin.getPassword()));
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void onDarkMode(){
        Data.isDarkMode = !Data.isDarkMode;
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
    }
}
