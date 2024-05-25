package org.example.fitnessstudiomanagement.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Helper.AccountHBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MembershipViewController implements Initializable {

    @FXML
    private ListView<HBox> MembershipListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Database.getDatabase().getAccountsAsList().forEach(account -> {
            MembershipListView.getItems().add(AccountHBox.getAccountHbox(account));
        });
    }
    public void onRetrunButtonPressed(){
        SceneManager.getInstance().switchScene(SceneType.ADMIN);
    }
    public void onDarkMode(){

    }


}
