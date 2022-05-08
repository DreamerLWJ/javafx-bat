package org.example.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.model.History;
import org.example.model.LengthInputSheet;
import org.example.model.ResultOutputSheet;
import org.example.service.CalculateService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 罗文俊
 * 2022/5/8
 */
public class DataInputUtil {


    private DataInputUtil() {

    }

    public static class InputDataListener implements ReadListener<LengthInputSheet> {
        private List<LengthInputSheet> list = new ArrayList<>();

        @Override
        public void invoke(LengthInputSheet data, AnalysisContext context) {
            list.add(data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            System.out.println(list);
        }

        private List<LengthInputSheet> getData() {
            return list;
        }
    }

    private static CalculateService calculateService = new CalculateService();


    public static List<LengthInputSheet> readExcel(String readPath) {
        ExcelReaderBuilder read = EasyExcel.read(readPath);
        return read.head(LengthInputSheet.class).sheet(0).doReadSync();
    }

    public static void writeExcel(String writePath, List<ResultOutputSheet> data) {
        ExcelWriterBuilder write = EasyExcel.write(writePath);
        write.head(ResultOutputSheet.class).sheet("结果信息").doWrite(data);
    }

    public static void main(String[] args) {
        List<LengthInputSheet> lengthInputSheets = readExcel("D:\\work\\UpFile\\javafx-bat\\src\\main\\java\\org\\example\\model\\test.xlsx");
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

        writeExcel("D:\\work\\UpFile\\javafx-bat\\src\\main\\java\\org\\example\\model\\result.xlsx", resultOutputSheets);
    }
}
