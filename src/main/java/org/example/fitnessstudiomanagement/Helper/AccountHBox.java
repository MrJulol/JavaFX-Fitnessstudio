package org.example.fitnessstudiomanagement.Helper;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.example.fitnessstudiomanagement.Controllers.SceneManager;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.MembershipType;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Model.Account;

public class AccountHBox {
    public static HBox getAccountHbox(Account account){
        HBox box = new HBox();
        box.setSpacing(10);

        Button deleteUser = new Button("Delete User");
        deleteUser.setOnAction(e -> {
            Database.getDatabase().removeAccount(account);
            SceneManager.getInstance().switchScene(SceneType.MEMBERSHIP);
        });
        Label expDate = new Label(account.getMembership().getExpirationDate().toString());

        SplitMenuButton membership = new SplitMenuButton();
        membership.setText(account.getMembership().name());
        membership.setMinWidth(120);

        MenuItem yearly = new MenuItem("Yearly");
        yearly.setOnAction(e -> {
            account.changeMembership(MembershipType.YEARLY);
            membership.setText(account.getMembership().name());
            expDate.setText(account.getMembership().getExpirationDate().toString());
        });

        MenuItem monthly = new MenuItem("Monthly");
        monthly.setOnAction(e -> {
            account.changeMembership(MembershipType.MONTHLY);
            membership.setText(account.getMembership().name());
            expDate.setText(account.getMembership().getExpirationDate().toString());
        });
        MenuItem quarterly = new MenuItem("Quarterly");
        quarterly.setOnAction(e -> {
            account.changeMembership(MembershipType.QUARTERLY);
            membership.setText(account.getMembership().name());
            expDate.setText(account.getMembership().getExpirationDate().toString());
        });
        membership.getItems().addAll(monthly, yearly, quarterly);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button details = new Button("Details");
        details.setOnAction(e -> {
            //TODO
        });

        box.getChildren().addAll(new Label(account.getName()), spacer, expDate, deleteUser, membership);
        return box;
    }
}
