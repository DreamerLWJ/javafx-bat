package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.History;
import persistence.FXMLRepository;
import utils.HistoryOutputUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author 罗文俊
 * 2021/12/18
 */
public class HistoryCell extends ListCell<History> {

    private final TextField[] bFields;
    private final TextField[] inputFields;
    private final TextField[] areaFields;
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
    @FXML
    public TextField nonceTf;
    @FXML
    public TextField remarkTf;
    @FXML
    public Button exportBtn;


    /**
     * 构造表上某一列的视图
     * 自定义控件的FXML资源定位符，如果为空，则简单展示文本
     */
    public HistoryCell() throws IOException {
        loadFXML();

        bFields = new TextField[]{b1Tf, b2Tf, b3Tf, b4Tf, b5Tf, b6Tf, b7Tf, b8Tf, b9Tf, b10Tf,
                b11Tf, b12Tf, b13Tf, b14Tf, b15Tf, b16Tf};

        inputFields = new TextField[]{
                input1Tx, input2Tx, input3Tx,
                input4Tx, input5Tx, input6Tx,
                input7Tx, input8Tx, input9Tx,
                input10Tx, input11Tx, input12Tx,
                input13Tx, input14Tx, input15Tx,
                input16Tx, input17Tx, input18Tx
        };

        areaFields = new TextField[]{area1Tf, area2Tf, area3Tf, area4Tf, area5Tf, area6Tf, area7Tf};
    }

    private void loadFXML() {

        FXMLLoader loader = new FXMLLoader(FXMLRepository.class.getClassLoader().getResource("history_cell.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(History history, boolean empty) {
        super.updateItem(history, empty);

        if (empty || history == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            nonceTf.setText(String.valueOf(history.getId()));
            remarkTf.setText(history.getRemark());

            for (int i = 0; i < bFields.length; i++) {
                bFields[i].setText(String.valueOf(history.getB()[i]));
            }

            for (int i = 0; i < inputFields.length; i++) {
                inputFields[i].setText(String.valueOf(history.getA()[i]));
            }

            for (int i = 0; i < areaFields.length; i++) {
                areaFields[i].setText(String.valueOf(history.getR()[i]));
            }
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        Stage stage = new Stage();
        stage.setTitle("请选择你想要导出的位置");
        exportBtn.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("导出结果");
            fileChooser.setInitialFileName("export.txt");
            File file = fileChooser.showSaveDialog(stage);
            if (file == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("导出取消！");
                alert.show();
                return;
            }
            System.out.println(file.getAbsolutePath());
            if (HistoryOutputUtil.prettyOutput(history, file)) {
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
