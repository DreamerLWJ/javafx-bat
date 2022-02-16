package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import persistence.StateRepository;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class AngleController implements Initializable {
    @FXML
    public TextField b1Tf;
    @FXML
    public TextField b2Tf;
    @FXML
    public TextField b3Tf;
    @FXML
    public TextField b4Tf;
    @FXML
    public TextField b5Tf;
    @FXML
    public TextField b6Tf;
    @FXML
    public TextField b7Tf;
    @FXML
    public TextField b8Tf;
    @FXML
    public TextField b9Tf;
    @FXML
    public TextField b10Tf;
    @FXML
    public TextField b11Tf;
    @FXML
    public TextField b12Tf;
    @FXML
    public TextField b13Tf;
    @FXML
    public TextField b14Tf;
    @FXML
    public TextField b15Tf;
    @FXML
    public TextField b16Tf;
    @FXML
    public Button modifyAngleBtn;

    public TextField[] textFields;

    private double[] angles;

    public AngleController() {
        angles = StateRepository.getAngles();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 初始化数组
        textFields = new TextField[]{b1Tf, b2Tf, b3Tf, b4Tf, b5Tf, b6Tf, b7Tf, b8Tf, b9Tf, b10Tf,
                b11Tf, b12Tf, b13Tf, b14Tf, b15Tf, b16Tf};

        for (int i = 0; i < 16; i++) {
            textFields[i].setText(String.valueOf(angles[i]));
        }

        // 初始化按钮
        modifyAngleBtn.setOnAction(actionEvent -> {
            for (int i = 0; i < angles.length; i++) {
                angles[i] = Double.parseDouble(textFields[i].getText());
            }
            // 保存修改
            StateRepository.changeAngle(angles);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("保存角度成功！");
            alert.show();
        });
    }
}
