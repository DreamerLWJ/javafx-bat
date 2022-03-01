package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.model.History;
import org.example.persistence.HistoryRepository;
import org.example.utils.ExcelOutputUtil;
import org.example.utils.HistoryOutputUtil;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author 罗文俊
 * 2021/12/9
 */
public class ResultController implements Initializable {
    @FXML
    public TextField area7Tf;
    @FXML
    public TextField area6Tf;
    @FXML
    public TextField area5Tf;
    @FXML
    public TextField area4Tf;
    @FXML
    public TextField area3Tf;
    @FXML
    public TextField area2Tf;
    @FXML
    public TextField area1Tf;

    public TextField[] textFields;

    @FXML
    public TextField remarkTf;

    @FXML
    public Button exportBtn;

    private History history;

    public ResultController() {
        history = HistoryRepository.getNewest();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFields = new TextField[]{area1Tf, area2Tf, area3Tf, area4Tf, area5Tf, area6Tf, area7Tf};

        Stage stage = new Stage();
        stage.setTitle("请选择你想要导出的位置");
        for (int i = 0; i < history.getR().length; i++) {
            textFields[i].setEditable(false);
            textFields[i].setText(String.valueOf(history.getR()[i]));
        }
        remarkTf.setText(history.getRemark());
        remarkTf.setEditable(false);

        exportBtn.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("导出结果");
            fileChooser.setInitialFileName("export.xlsx");
            File file = fileChooser.showSaveDialog(stage);
            if (file == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("导出取消！");
                alert.show();
                return;
            }
            System.out.println(file.getAbsolutePath());
            if (ExcelOutputUtil.historyExcelOutput(history, file)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("导出成功！");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("导出失败！");
                alert.show();
            }
        });
    }
}
