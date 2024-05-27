package org.example.fitnessstudiomanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.LanguageKey;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Enums.TranslationKey;
import org.example.fitnessstudiomanagement.Helper.Encrypt;
import org.example.fitnessstudiomanagement.Helper.Validation;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Languages.LanguageDatabase;
import org.example.fitnessstudiomanagement.Model.Account;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    public MenuButton settings;
    @FXML
    public Label username;
    @FXML
    public Label password;
    @FXML
    public Button login;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView LogoImage;
    @FXML
    private MenuItem darkMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(Data.isDarkMode){
            File file = new File("src/main/resources/org/example/fitnessstudiomanagement/Controllers/Images/Logo2.png");
            Image image = new Image(file.toURI().toString());
            LogoImage.setImage(image);

            darkMode.setText("Light-Mode");
        }

        settings.setText(LanguageDatabase.getInstance().get(TranslationKey.SETTINGS));
        username.setText(LanguageDatabase.getInstance().get(TranslationKey.USERNAME));
        password.setText(LanguageDatabase.getInstance().get(TranslationKey.PASSWORD));
        login.setText(LanguageDatabase.getInstance().get(TranslationKey.LOGIN));
    }


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
    public void onItalian(){
        Data.language = LanguageKey.ITALIAN;
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
    }
    public void onGerman(){
        Data.language = LanguageKey.GERMAN;
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
    }
    public void onEnglish(){
        Data.language = LanguageKey.ENGLISH;
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
    }


}
