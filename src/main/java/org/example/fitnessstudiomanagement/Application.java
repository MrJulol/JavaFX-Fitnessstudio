package org.example.fitnessstudiomanagement;

import javafx.stage.Stage;
import org.example.fitnessstudiomanagement.Data.Data;
import org.example.fitnessstudiomanagement.Enums.SceneType;
import org.example.fitnessstudiomanagement.Controllers.SceneManager;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Data.stage = primaryStage;
        SceneManager.getInstance().switchScene(SceneType.LOGIN);
        primaryStage.show();
    }
}
