package org.example.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.example.model.CoordinateBatchOutputSheet;
import org.example.model.LengthInputSheet;
import org.example.model.AreaBatchOutputSheet;
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


    public static List<LengthInputSheet> readExcel(File readFile) throws ExcelDataConvertException {
        ExcelReaderBuilder read = EasyExcel.read(readFile);
        return read.head(LengthInputSheet.class).sheet(0).doReadSync();
    }

    public static void writeExcel(File outputFile, List<AreaBatchOutputSheet> data, List<CoordinateBatchOutputSheet> coordinateBatchOutputSheets) {
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(outputFile).build();
            WriteSheet areaSheet = EasyExcel.writerSheet(0, "面积信息").head(AreaBatchOutputSheet.class).build();
            WriteSheet coorSheet = EasyExcel.writerSheet(1, "坐标信息").head(CoordinateBatchOutputSheet.class).build();
            excelWriter.write(data, areaSheet).write(coordinateBatchOutputSheets, coorSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
