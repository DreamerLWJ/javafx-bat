package org.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.example.model.History;
import org.example.persistence.HistoryRepository;
import org.example.service.CalculateService;
import org.example.view.AngleStage;
import org.example.view.HistoryStage;
import org.example.view.ResultStage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author 罗文俊
 * 2021/12/7
 */
public class MainController implements Initializable {

    @FXML
    public Button modifyAngleBtn;

    @FXML
    public Button viewRecordBtn;

    @FXML
    public Button clearBtn;

    @FXML
    public Button calculateBtn;

    /**
     * 18 个输入
     */
    @FXML
    public TextField input1Tx;
    @FXML
    public TextField input2Tx;
    @FXML
    public TextField input3Tx;
    @FXML
    public TextField input4Tx;
    @FXML
    public TextField input5Tx;
    @FXML
    public TextField input6Tx;
    @FXML
    public TextField input7Tx;
    @FXML
    public TextField input8Tx;
    @FXML
    public TextField input9Tx;
    @FXML
    public TextField input10Tx;
    @FXML
    public TextField input11Tx;
    @FXML
    public TextField input12Tx;
    @FXML
    public TextField input13Tx;
    @FXML
    public TextField input14Tx;
    @FXML
    public TextField input15Tx;
    @FXML
    public TextField input16Tx;
    @FXML
    public TextField input17Tx;
    @FXML
    public TextField input18Tx;

    @FXML
    public TextField remarkTx;
    /**
     * 图片显示器
     */
    public ImageView imageView;
    private TextField[] textFields;
    private CalculateService calculateService = new CalculateService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 对话框
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误提示");
        textFields = new TextField[]{
                input1Tx, input2Tx, input3Tx,
                input4Tx, input5Tx, input6Tx,
                input7Tx, input8Tx, input9Tx,
                input10Tx, input11Tx, input12Tx,
                input13Tx, input14Tx, input15Tx,
                input16Tx, input17Tx, input18Tx
        };

        modifyAngleBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new AngleStage();
            }
        });

        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (TextField textField : textFields) {
                    textField.setText("");
                }
                remarkTx.setText("");
            }
        });

        calculateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double[] a = new double[18];
                String remark = remarkTx.getText();
                for (int i = 0; i < 18; i++) {
                    try {
                        String input = textFields[i].getText();
                        a[i] = Double.parseDouble(input);

                    } catch (NumberFormatException e) {
                        alert.setContentText("长度输入非法，请重新调整输入");
                        alert.show();
                        return;
                    }
                }
                // 进行计算
                History history = calculateService.calculate(a, remark);
                // 保存到历史记录中
                HistoryRepository.save(history);
                // 显示新的界面
                ResultStage resultStage = new ResultStage();
            }
        });

        viewRecordBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // 查看历史记录的处理
                new HistoryStage();
            }
        });
    }
}
