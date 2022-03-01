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
public class CoordinateOutputSheet {
    @ExcelProperty("坐标序号")
    private Integer num;

    @ExcelProperty("x 坐标")
    private Double x;

    @ExcelProperty("y 坐标")
    private Double y;
}
