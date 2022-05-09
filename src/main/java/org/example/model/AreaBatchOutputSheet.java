package org.example.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 罗文俊
 * 2022/5/8
 */
@Data
public class AreaBatchOutputSheet {
    @ExcelProperty("编号")
    private String id;

    @ExcelProperty("1区面积")
    private double area1;

    @ExcelProperty("2区面积")
    private double area2;

    @ExcelProperty("3区面积")
    private double area3;

    @ExcelProperty("4区面积")
    private double area4;

    @ExcelProperty("5区面积")
    private double area5;

    @ExcelProperty("6区面积")
    private double area6;

    @ExcelProperty("总面积")
    private double totalArea;
}
