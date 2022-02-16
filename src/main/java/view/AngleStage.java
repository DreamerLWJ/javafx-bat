package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.FXMLRepository;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class AngleStage extends Stage {
    private Parent parent;

    private String title = "修改角度界面";

    public AngleStage() {
        parent = FXMLRepository.getAngle();
        this.setTitle(title);
        this.setScene(new Scene(parent, 1000, 500));
        this.setResizable(false);
        this.show();
    }
}
