package org.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.model.History;
import org.example.model.LengthInputSheet;
import org.example.model.ResultOutputSheet;
import org.example.persistence.BatchCache;
import org.example.service.CalculateService;
import org.example.utils.DataInputUtil;
import org.example.utils.ExcelOutputUtil;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 罗文俊
 * 2022/5/8
 */
public class BatchController implements Initializable {
    @FXML
    public Button loadBtn;

    @FXML
    public TextField loadedTf;

    @FXML
    public Button calculateBtn;

    @FXML
    public Button exportBtn;

    private CalculateService calculateService = new CalculateService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = new Stage();
        stage.setTitle("请选择要导入的文件");

        loadBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel File", "*.xls", "*.xlsx"));
            fileChooser.setTitle("请选择文件");
            File file = fileChooser.showOpenDialog(stage);
            if (file == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("导入取消！");
                alert.show();
                return;
            }

            // 文件选择器
            List<LengthInputSheet> lengthInputSheets = DataInputUtil.readExcel(file);
            BatchCache.saveInputCache(lengthInputSheets);

            loadedTf.setText(String.valueOf(lengthInputSheets.size()));
        });

        calculateBtn.setOnAction(event -> {
            if (!BatchCache.isInputReady()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("尚未导入成功！");
                alert.show();
                return;
            }
            if (batchCalculate(BatchCache.getInputCache())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("计算完成！");
                alert.show();
            }
        });

        exportBtn.setOnAction(event -> {
            if (!BatchCache.isOutputReady()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("尚未计算或计算完成！");
                alert.show();
                return;
            }
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

            DataInputUtil.writeExcel(file, BatchCache.getOutputData());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("导出成功！");
            alert.show();
            BatchCache.clearAll();
        });
    }

    private boolean batchCalculate(List<LengthInputSheet> lengthInputSheets) {
        List<ResultOutputSheet> resultOutputSheets = new ArrayList<>();
        for (LengthInputSheet lengthInputSheet : lengthInputSheets) {
            double[] a = new double[18];
            a[0] = lengthInputSheet.getA1();
            a[1] = lengthInputSheet.getA2();
            a[2] = lengthInputSheet.getA3();
            a[3] = lengthInputSheet.getA4();
            a[4] = lengthInputSheet.getA5();
            a[5] = lengthInputSheet.getA6();
            a[6] = lengthInputSheet.getA7();
            a[7] = lengthInputSheet.getA8();
            a[8] = lengthInputSheet.getA9();
            a[9] = lengthInputSheet.getA10();
            a[10] = lengthInputSheet.getA11();
            a[11] = lengthInputSheet.getA12();
            a[12] = lengthInputSheet.getA13();
            a[13] = lengthInputSheet.getA14();
            a[14] = lengthInputSheet.getA15();
            a[15] = lengthInputSheet.getA16();
            a[16] = lengthInputSheet.getA17();
            a[17] = lengthInputSheet.getA18();
            History s = calculateService.calculate(a, "s");
            ResultOutputSheet resultOutputSheet = new ResultOutputSheet();

            resultOutputSheet.setId(lengthInputSheet.getId());
            resultOutputSheet.setArea1(s.getR()[0]);
            resultOutputSheet.setArea2(s.getR()[1]);
            resultOutputSheet.setArea3(s.getR()[2]);
            resultOutputSheet.setArea4(s.getR()[3]);
            resultOutputSheet.setArea5(s.getR()[4]);
            resultOutputSheet.setArea6(s.getR()[5]);
            resultOutputSheet.setTotalArea(s.getR()[6]);
            resultOutputSheets.add(resultOutputSheet);
        }
        BatchCache.saveOutputCache(resultOutputSheets);
        BatchCache.clearInput();
        return true;
    }
}
