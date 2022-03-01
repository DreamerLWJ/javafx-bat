package org.example.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 罗文俊
 * 2022/3/1
 */
@Data
@Accessors(chain = true)
public class AreaOutputSheet {
    @ExcelProperty("面积标识")
    private String areaName;

    @ExcelProperty("面积值")
    private Double value;
}
