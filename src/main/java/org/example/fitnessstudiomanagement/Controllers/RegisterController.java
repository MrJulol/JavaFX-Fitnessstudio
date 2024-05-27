package org.example.fitnessstudiomanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.LanguageKey;
import org.example.fitnessstudiomanagement.Enums.MembershipType;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Enums.TranslationKey;
import org.example.fitnessstudiomanagement.Helper.Encrypt;
import org.example.fitnessstudiomanagement.Helper.Validation;
import org.example.fitnessstudiomanagement.Languages.LanguageDatabase;
import org.example.fitnessstudiomanagement.Model.Account;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    public Button contB;
    @FXML
    public Label namelb;
    @FXML
    public Label passlb;
    @FXML
    public Label phonelb;
    @FXML
    public Label bdaylb;
    @FXML
    public Label addlb;
    @FXML
    public Label passlb2;
    @FXML
    public Button cancel;
    @FXML
    public MenuButton settings;

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
    @FXML
    private MenuItem darkMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(Data.isDarkMode){
            darkMode.setText("Light-Mode");
        }

        contB.setText(LanguageDatabase.getInstance().get(TranslationKey.OK));
        namelb.setText(LanguageDatabase.getInstance().get(TranslationKey.REG_NAME));
        phonelb.setText(LanguageDatabase.getInstance().get(TranslationKey.REG_PHONE));
        bdaylb.setText(LanguageDatabase.getInstance().get(TranslationKey.REG_DATE));
        addlb.setText(LanguageDatabase.getInstance().get(TranslationKey.REG_ADDRESS));
        passlb.setText(LanguageDatabase.getInstance().get(TranslationKey.PASSWORD));
        passlb2.setText(LanguageDatabase.getInstance().get(TranslationKey.PASSWORD));
        cancel.setText(LanguageDatabase.getInstance().get(TranslationKey.CANCEL));
        settings.setText(LanguageDatabase.getInstance().get(TranslationKey.SETTINGS));
    }

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
        Data.isDarkMode = !Data.isDarkMode;
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
    }
    public void onItalian(){
        Data.language = LanguageKey.ITALIAN;
        SceneManager.getInstance().switchScene(SceneType.REGISTER);
    }
    public void onGerman(){
        Data.language = LanguageKey.GERMAN;
        SceneManager.getInstance().switchScene(SceneType.REGISTER);
    }
    public void onEnglish(){
        Data.language = LanguageKey.ENGLISH;
        SceneManager.getInstance().switchScene(SceneType.REGISTER);
    }
}
