package org.example.fitnessstudiomanagement.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Data.Database;
import org.example.fitnessstudiomanagement.Enums.LanguageKey;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Enums.TranslationKey;
import org.example.fitnessstudiomanagement.Helper.StatisticGenerator;
import org.example.fitnessstudiomanagement.Languages.LanguageDatabase;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminViewController implements Initializable {


    @FXML
    public Button register;
    @FXML
    public Button membership;
    @FXML
    public Button logout;
    @FXML
    public Label your_clients;
    @FXML
    public MenuButton settings;
    @FXML
    private ListView<HBox> clientList;
    @FXML
    private MenuItem darkMode;
    @FXML
    private ImageView LogoImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(Data.isDarkMode){
            File file = new File("src/main/resources/org/example/fitnessstudiomanagement/Controllers/Images/Logo2.png");
            Image image = new Image(file.toURI().toString());
            LogoImage.setImage(image);

            darkMode.setText("Light-Mode");

        }

        register.setText(LanguageDatabase.getInstance().get(TranslationKey.REGISTER));
        membership.setText(LanguageDatabase.getInstance().get(TranslationKey.MEMBERSHIP));
        logout.setText(LanguageDatabase.getInstance().get(TranslationKey.LOGOUT));
        your_clients.setText(LanguageDatabase.getInstance().get(TranslationKey.YOUR_CLIENTS));
        settings.setText(LanguageDatabase.getInstance().get(TranslationKey.SETTINGS));

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
        Data.isDarkMode = !Data.isDarkMode;
        SceneManager.getInstance().switchScene(SceneType.ADMIN);
    }
    public void onItalian(){
        Data.language = LanguageKey.ITALIAN;
        SceneManager.getInstance().switchScene(SceneType.ADMIN);
    }
    public void onGerman(){
        Data.language = LanguageKey.GERMAN;
        SceneManager.getInstance().switchScene(SceneType.ADMIN);
    }
    public void onEnglish(){
        Data.language = LanguageKey.ENGLISH;
        SceneManager.getInstance().switchScene(SceneType.ADMIN);
    }
    public void logStatisticsToFile(){
        new Thread(()->{
            File file = new File("./Statistics.txt");

            try {
                FileWriter fileWriter = new FileWriter(file, false);

                fileWriter.write("Expired Memberships: \n");
                StatisticGenerator.getExpiredMemberships().forEach(acc -> {
                    try {
                        fileWriter.write(acc.getName()+"\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                fileWriter.write("\n\nNumber of Clients per Month: \n");

                StatisticGenerator.getCustomerCountPerMonth().forEach((month, count)-> {
                    try {
                        fileWriter.write(month + " : " + count);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                fileWriter.write("\n\nTotal Number of Checkins per Month: \n");
                StatisticGenerator.getCheckinCountPerMonth().forEach((month, count)-> {
                    try {
                        fileWriter.write(month + " : " + count);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                fileWriter.write("\n\nStatistic on Membership-Types: \n");
                StatisticGenerator.getMembershipsByType().forEach((type, count)-> {
                    try {
                        fileWriter.write(type + " : " + count);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                fileWriter.close();
                System.out.println("Logged to file!");
            } catch (IOException e) {
                System.out.println("Error logging to file: " + e.getMessage());
            }
        }).start();
    }


}
