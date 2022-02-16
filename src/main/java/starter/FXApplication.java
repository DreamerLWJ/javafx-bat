package starter;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainStage;

public class FXApplication extends Application {

    @Override
    public void start(Stage stage) {
        // 渲染主界面
        new MainStage();
    }
}