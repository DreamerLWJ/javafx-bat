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

import java.io.File;
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


    public static List<LengthInputSheet> readExcel(File readFile) {
        ExcelReaderBuilder read = EasyExcel.read(readFile);
        return read.head(LengthInputSheet.class).sheet(0).doReadSync();
    }

    public static void writeExcel(File outputFile, List<ResultOutputSheet> data) {
        ExcelWriterBuilder write = EasyExcel.write(outputFile);
        write.head(ResultOutputSheet.class).sheet("结果信息").doWrite(data);
    }
}
