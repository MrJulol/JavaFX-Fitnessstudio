package org.example.fitnessstudiomanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.SceneType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientViewController implements Initializable {

    @FXML
    private Label counter;
    @FXML
    private Label name;
    @FXML
    private Label expDate;
    @FXML
    private ListView<String> CheckinListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText("Welcome Back "+Data.loggedOnAccount.getName());
        expDate.setText("Expiration Date: "+Data.loggedOnAccount.getMembership().getExpirationDate().toString());
        counter.setText("Number of Logins: "+Data.loggedOnAccount.getCheckinNr());
    }
    public void onCheckinButtonPressed(ActionEvent actionEvent) {
        Data.loggedOnAccount.setCheckinNr(Data.loggedOnAccount.getCheckinNr()+1);
        try {
            Database.getDatabase().updateCheckinStat(Data.loggedOnAccount.getName(), Data.loggedOnAccount.getCheckinNr());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        counter.setText("Number of Logins: "+Data.loggedOnAccount.getCheckinNr());
    }
    public void onReturnButtonPressed(ActionEvent actionEvent) {
        Data.loggedOnAccount = null;
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
    }
    public void onDarkMode(){
        Data.isDarkMode = !Data.isDarkMode;
        SceneManager.getInstance().switchScene(SceneType.CLIENT);
    }
}
