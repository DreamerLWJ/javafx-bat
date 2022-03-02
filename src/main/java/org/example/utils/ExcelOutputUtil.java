package org.example.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.example.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 罗文俊
 * 2022/3/1
 */
public final class ExcelOutputUtil {
    private ExcelOutputUtil() {

    }

    public static boolean historyExcelOutput(History history, File file) {

        ExcelWriter excelWriter = EasyExcel.write(file).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "文件信息").head(StartOutputSheet.class).build();
        excelWriter.write(() -> {
            StartOutputSheet startOutputSheet = new StartOutputSheet();
            startOutputSheet.setNum(history.getId())
                    .setRemark(history.getRemark())
                    .setRecordDate(history.getDate())
                    .setDeveloper("软件开发：余文华、范宏猷、罗文俊")
                    .setCitation("岳阳,胡宜峰,雷博宇,吴毅,吴华,刘宝权,余文华.毛翼管鼻蝠性二型特征及其在湖北和浙江的分布新纪录[J].兽类学报,2019,39(02):142-154.DOI:10.16829/j.slxb.150251.");
            return Collections.singleton(startOutputSheet);
        }, writeSheet);

        writeSheet = EasyExcel.writerSheet(1, "角度信息").head(AngleOutputSheet.class).build();
        excelWriter.write(() -> {
            List<AngleOutputSheet> list = new ArrayList<>();
            for (int i = 1; i <= history.getB().length; i++) {
                AngleOutputSheet angleOutputSheet = new AngleOutputSheet();
                angleOutputSheet.setBString("b" + i)
                        .setAngle(history.getB()[i - 1]);
                list.add(angleOutputSheet);
            }
            return list;
        }, writeSheet);

        writeSheet = EasyExcel.writerSheet(2, "长度信息").head(LengthOutputSheet.class).build();
        excelWriter.write(() -> {
            List<LengthOutputSheet> list = new ArrayList<>();
            for (int i = 1; i <= history.getA().length; i++) {
                LengthOutputSheet lengthOutputSheet = new LengthOutputSheet();
                lengthOutputSheet.setAString("a" + i)
                        .setLength(history.getA()[i - 1]);
                list.add(lengthOutputSheet);
            }
            return list;
        }, writeSheet);

        writeSheet = EasyExcel.writerSheet(3, "面积结果").head(AreaOutputSheet.class).build();
        excelWriter.write(() -> {
            List<AreaOutputSheet> list = new ArrayList<>();
            for (int i = 1; i <= history.getR().length - 1; i++) {
                AreaOutputSheet areaOutputSheet = new AreaOutputSheet();
                areaOutputSheet.setAreaName(i + "区面积")
                        .setValue(history.getR()[i - 1]);
                list.add(areaOutputSheet);
            }
            AreaOutputSheet areaOutputSheet = new AreaOutputSheet();
            areaOutputSheet.setAreaName("总面积")
                    .setValue(history.getR()[history.getR().length - 1]);
            list.add(areaOutputSheet);
            return list;
        }, writeSheet);

        writeSheet = EasyExcel.writerSheet(4, "坐标信息").head(CoordinateOutputSheet.class).build();
        excelWriter.write(() -> {
            List<CoordinateOutputSheet> list = new ArrayList<>();
            for (int i = 0; i < history.getX().length; i++) {
                CoordinateOutputSheet coordinateOutputSheet = new CoordinateOutputSheet();
                coordinateOutputSheet.setX(history.getX()[i])
                        .setY(history.getY()[i])
                        .setNum(i + 1);
                list.add(coordinateOutputSheet);
            }
            return list;
        }, writeSheet);

        excelWriter.finish();
        return true;
    }
}
