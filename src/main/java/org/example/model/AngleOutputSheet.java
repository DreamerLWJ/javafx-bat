package org.example.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 罗文俊
 * 2022/3/1
 */
@Accessors(chain = true)
@Data
public class AngleOutputSheet {
    @ExcelProperty("角度标识")
    private String bString;

    @ExcelProperty("角度值")
    private Double angle;
}
