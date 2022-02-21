package org.example.view;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.persistence.FXMLRepository;
import org.example.persistence.HistoryRepository;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class MainStage extends Stage {
    private Parent parent;

    private String title = "主界面";

    public MainStage() {
        parent = FXMLRepository.getMain();
        this.setTitle(title);
        this.setScene(new Scene(parent, 1000, 500));
        this.setResizable(false);
        this.setOnCloseRequest(windowEvent -> {
            HistoryRepository.saveFile();
            // 监听主窗口关闭
            Platform.exit();
        });
        this.show();
    }
}
