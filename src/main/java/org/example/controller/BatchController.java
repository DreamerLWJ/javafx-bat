package org.example.controller;

import com.alibaba.excel.exception.ExcelDataConvertException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.model.CoordinateBatchOutputSheet;
import org.example.model.History;
import org.example.model.LengthInputSheet;
import org.example.model.AreaBatchOutputSheet;
import org.example.persistence.BatchCache;
import org.example.service.CalculateService;
import org.example.utils.DataInputUtil;

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
            List<LengthInputSheet> lengthInputSheets = null;
            try {
                lengthInputSheets = DataInputUtil.readExcel(file);
            } catch (ExcelDataConvertException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("导入出错！出错位置，(行号, 列号) 为 (" + e.getRowIndex() + 1 + ", " + e.getColumnIndex() +")，出错值为 " + e.getCellData().getStringValue());
                alert.show();
                return;
            }
            BatchCache.saveInputCache(lengthInputSheets);

            loadedTf.setText("有效数据为: " + lengthInputSheets.size() + "组");
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

            DataInputUtil.writeExcel(file, BatchCache.getOutputAreaData(), BatchCache.getOutputCoorData());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("导出成功！");
            alert.show();
            BatchCache.clearAll();
        });
    }

    private boolean batchCalculate(List<LengthInputSheet> lengthInputSheets) {
        List<AreaBatchOutputSheet> areaBatchOutputSheets = new ArrayList<>(lengthInputSheets.size());
        List<CoordinateBatchOutputSheet> coordinateBatchOutputSheets = new ArrayList<>(lengthInputSheets.size());
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
            AreaBatchOutputSheet areaBatchOutputSheet = new AreaBatchOutputSheet();

            areaBatchOutputSheet.setId(lengthInputSheet.getId());
            areaBatchOutputSheet.setArea1(s.getR()[0]);
            areaBatchOutputSheet.setArea2(s.getR()[1]);
            areaBatchOutputSheet.setArea3(s.getR()[2]);
            areaBatchOutputSheet.setArea4(s.getR()[3]);
            areaBatchOutputSheet.setArea5(s.getR()[4]);
            areaBatchOutputSheet.setArea6(s.getR()[5]);
            areaBatchOutputSheet.setTotalArea(s.getR()[6]);
            areaBatchOutputSheets.add(areaBatchOutputSheet);

            CoordinateBatchOutputSheet coordinateBatchOutputSheet = new CoordinateBatchOutputSheet();
            coordinateBatchOutputSheet.setId(lengthInputSheet.getId());
            coordinateBatchOutputSheet.setX1(s.getX()[0]);
            coordinateBatchOutputSheet.setX2(s.getX()[1]);
            coordinateBatchOutputSheet.setX3(s.getX()[2]);
            coordinateBatchOutputSheet.setX4(s.getX()[3]);
            coordinateBatchOutputSheet.setX5(s.getX()[4]);
            coordinateBatchOutputSheet.setX6(s.getX()[5]);
            coordinateBatchOutputSheet.setX7(s.getX()[6]);
            coordinateBatchOutputSheet.setX8(s.getX()[7]);
            coordinateBatchOutputSheet.setX9(s.getX()[8]);
            coordinateBatchOutputSheet.setX10(s.getX()[9]);
            coordinateBatchOutputSheet.setX11(s.getX()[10]);
            coordinateBatchOutputSheet.setX12(s.getX()[11]);
            coordinateBatchOutputSheet.setX13(s.getX()[12]);
            coordinateBatchOutputSheet.setX14(s.getX()[13]);
            coordinateBatchOutputSheet.setX15(s.getX()[14]);
            coordinateBatchOutputSheet.setX16(s.getX()[15]);
            coordinateBatchOutputSheet.setX17(s.getX()[16]);
            coordinateBatchOutputSheet.setX18(s.getX()[17]);
            coordinateBatchOutputSheet.setX19(s.getX()[18]);

            coordinateBatchOutputSheet.setY1(s.getY()[0]);
            coordinateBatchOutputSheet.setY2(s.getY()[1]);
            coordinateBatchOutputSheet.setY3(s.getY()[2]);
            coordinateBatchOutputSheet.setY4(s.getY()[3]);
            coordinateBatchOutputSheet.setY5(s.getY()[4]);
            coordinateBatchOutputSheet.setY6(s.getY()[5]);
            coordinateBatchOutputSheet.setY7(s.getY()[6]);
            coordinateBatchOutputSheet.setY8(s.getY()[7]);
            coordinateBatchOutputSheet.setY9(s.getY()[8]);
            coordinateBatchOutputSheet.setY10(s.getY()[9]);
            coordinateBatchOutputSheet.setY11(s.getY()[10]);
            coordinateBatchOutputSheet.setY12(s.getY()[11]);
            coordinateBatchOutputSheet.setY13(s.getY()[12]);
            coordinateBatchOutputSheet.setY14(s.getY()[13]);
            coordinateBatchOutputSheet.setY15(s.getY()[14]);
            coordinateBatchOutputSheet.setY16(s.getY()[15]);
            coordinateBatchOutputSheet.setY17(s.getY()[16]);
            coordinateBatchOutputSheet.setY18(s.getY()[17]);
            coordinateBatchOutputSheet.setY19(s.getY()[18]);
            coordinateBatchOutputSheets.add(coordinateBatchOutputSheet);
        }
        BatchCache.saveOutputAreaCache(areaBatchOutputSheets);
        BatchCache.saveOutputCoorCache(coordinateBatchOutputSheets);
        BatchCache.clearInput();
        return true;
    }
}
