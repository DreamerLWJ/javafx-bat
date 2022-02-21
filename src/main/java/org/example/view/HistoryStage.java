package org.example.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.persistence.FXMLRepository;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class HistoryStage extends Stage {
    private Parent parent;

    private String title = "历史记录界面";

    public HistoryStage() {
        parent = FXMLRepository.getHistory();
        this.setTitle(title);
        this.setScene(new Scene(parent, 1000, 500));
        this.setResizable(false);
        this.show();
    }
}
