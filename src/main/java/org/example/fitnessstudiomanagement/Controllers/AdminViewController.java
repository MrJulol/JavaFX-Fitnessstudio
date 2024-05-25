package org.example.fitnessstudiomanagement.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.SceneType;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminViewController implements Initializable {

    @FXML
    private ListView<HBox> clientList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientList.getItems().setAll(Database.getDatabase().getAccountsAsList().stream().map(account -> new HBox(new Label(account.getName()))).collect(Collectors.toSet()));
    }

    public void onRegisterButtonPressed() {
        SceneManager.getInstance().switchScene(SceneType.REGISTER);
    }

    public void onMembershipButtonPressed() {
        SceneManager.getInstance().switchScene(SceneType.MEMBERSHIP);
    }

    public void onReturnButtonPressed() {
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
    }
    public void onDarkMode(){

    }


}
