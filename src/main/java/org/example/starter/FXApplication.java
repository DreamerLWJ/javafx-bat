package org.example.starter;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.view.MainStage;

public class FXApplication extends Application {

    @Override
    public void start(Stage stage) {
        // 渲染主界面
        new MainStage();
    }
}