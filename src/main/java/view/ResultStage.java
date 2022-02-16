package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.FXMLRepository;

/**
 * @author 罗文俊
 * 2021/12/10
 */
public class ResultStage extends Stage {
    private Parent parent;

    private String title = "计算结果界面";

    public ResultStage() {
        parent = FXMLRepository.getResult();
        this.setTitle(title);
        this.setScene(new Scene(parent, 1000, 500));
        this.setResizable(false);
        this.show();
    }
}
