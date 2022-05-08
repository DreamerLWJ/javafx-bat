package org.example.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.persistence.FXMLRepository;

/**
 * @author 罗文俊
 * 2022/5/8
 */
public class BatchStage extends Stage {

    private Parent parent;

    private String title = "批量计算";

    public BatchStage() {
        parent = FXMLRepository.getBatch();
        setTitle(title);
        setScene(new Scene(parent, 1000, 500));
        setResizable(false);
        show();
    }
}
