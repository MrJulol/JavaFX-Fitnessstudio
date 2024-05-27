package org.example.fitnessstudiomanagement.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Enums.SceneType;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {
    private static SceneManager instance;

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
    public void switchScene(SceneType sceneType) {
        Scene scene = null;
        try {
            switch (sceneType) {
                case LOGIN:
                    scene = new Scene((new FXMLLoader(getClass().getResource("login-view.fxml")).load()));
                    break;
                case CLIENT:
                    scene = new Scene((new FXMLLoader(getClass().getResource("client-view.fxml")).load()));
                    break;
                case ADMIN:
                    scene = new Scene((new FXMLLoader(getClass().getResource("admin-view.fxml")).load()));
                    break;
                case MEMBERSHIP:
                    scene = new Scene((new FXMLLoader(getClass().getResource("membership-view.fxml")).load()));
                    break;
                case REGISTER:
                    scene = new Scene((new FXMLLoader(getClass().getResource("register-view.fxml")).load()));
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
        if(Data.isDarkMode)scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("dark.css")).toExternalForm());
        this.switchScene(scene);
    }

    public void switchScene(Scene scene) {
        Data.stage.setScene(scene);
    }

}
