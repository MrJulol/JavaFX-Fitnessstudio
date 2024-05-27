package org.example.fitnessstudiomanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.LanguageKey;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Enums.TranslationKey;
import org.example.fitnessstudiomanagement.Languages.LanguageDatabase;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientViewController implements Initializable {

    @FXML
    public MenuButton settings;
    @FXML
    public Button logout;
    @FXML
    public Button checkin;
    @FXML
    private Label counter;
    @FXML
    private Label name;
    @FXML
    private Label expDate;
    @FXML
    private MenuItem darkMode;
    @FXML
    private ImageView LogoImage;
    @FXML
    private Label motiv;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settings.setText(LanguageDatabase.getInstance().get(TranslationKey.SETTINGS));
        logout.setText(LanguageDatabase.getInstance().get(TranslationKey.LOGOUT));
        checkin.setText(LanguageDatabase.getInstance().get(TranslationKey.CHECKIN));

        name.setText(LanguageDatabase.getInstance().get(TranslationKey.WELCOME) + Data.loggedOnAccount.getName());
        counter.setText(LanguageDatabase.getInstance().get(TranslationKey.LOGIN_NR) + Data.loggedOnAccount.getCheckinNr());
        expDate.setText(LanguageDatabase.getInstance().get(TranslationKey.EXP_DATE) + Data.loggedOnAccount.getMembership().getExpirationDate());

        motiv.setText("Nothing is impossible!");

        if(Data.isDarkMode){
            darkMode.setText("Light-Mode");

            File file = new File("src/main/resources/org/example/fitnessstudiomanagement/Controllers/Images/Logo2.png");
            Image image = new Image(file.toURI().toString());
            LogoImage.setImage(image);
        }

    }

    public void onCheckinButtonPressed(ActionEvent actionEvent) {
        Data.loggedOnAccount.setCheckinNr(Data.loggedOnAccount.getCheckinNr() + 1);
        try {
            Database.getDatabase().updateCheckinStat(Data.loggedOnAccount.getName(), Data.loggedOnAccount.getCheckinNr());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        counter.setText(LanguageDatabase.getInstance().get(TranslationKey.LOGIN_NR) + Data.loggedOnAccount.getCheckinNr());
    }

    public void onReturnButtonPressed(ActionEvent actionEvent) {
        Data.loggedOnAccount = null;
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
    }

    public void onDarkMode(){
        Data.isDarkMode = !Data.isDarkMode;
        SceneManager.getInstance().switchScene(SceneType.CLIENT);
    }
    public void onItalian(){
        Data.language = LanguageKey.ITALIAN;
        SceneManager.getInstance().switchScene(SceneType.CLIENT);
    }
    public void onGerman(){
        Data.language = LanguageKey.GERMAN;
        SceneManager.getInstance().switchScene(SceneType.CLIENT);
    }
    public void onEnglish(){
        Data.language = LanguageKey.ENGLISH;
        SceneManager.getInstance().switchScene(SceneType.CLIENT);
    }
}
