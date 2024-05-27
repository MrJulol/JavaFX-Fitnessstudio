package org.example.fitnessstudiomanagement.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.LanguageKey;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Enums.TranslationKey;
import org.example.fitnessstudiomanagement.Helper.AccountHBox;
import org.example.fitnessstudiomanagement.Languages.LanguageDatabase;

import java.net.URL;
import java.util.ResourceBundle;

public class MembershipViewController implements Initializable {

    @FXML
    public MenuButton settings;
    @FXML
    public Button logout;
    @FXML
    private ListView<HBox> MembershipListView;
    @FXML
    private MenuItem darkMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(Data.isDarkMode){
            darkMode.setText("Light-Mode");
        }

        settings.setText(LanguageDatabase.getInstance().get(TranslationKey.SETTINGS));
        logout.setText(LanguageDatabase.getInstance().get(TranslationKey.BACK));

        Database.getDatabase().getAccountsAsList().forEach(account -> {
            MembershipListView.getItems().add(AccountHBox.getAccountHbox(account));
        });
    }
    public void onRetrunButtonPressed(){
        SceneManager.getInstance().switchScene(SceneType.ADMIN);
    }
    public void onDarkMode(){
        Data.isDarkMode = !Data.isDarkMode;
        SceneManager.getInstance().switchScene(SceneType.MEMBERSHIP);
    }
    public void onItalian(){
        Data.language = LanguageKey.ITALIAN;
        SceneManager.getInstance().switchScene(SceneType.MEMBERSHIP);
    }
    public void onGerman(){
        Data.language = LanguageKey.GERMAN;
        SceneManager.getInstance().switchScene(SceneType.MEMBERSHIP);
    }
    public void onEnglish(){
        Data.language = LanguageKey.ENGLISH;
        SceneManager.getInstance().switchScene(SceneType.MEMBERSHIP);
    }


}
