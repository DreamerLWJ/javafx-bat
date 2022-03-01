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
public class LengthOutputSheet {
    @ExcelProperty("长度标识")
    private String aString;

    @ExcelProperty("长度值")
    private Double length;
}
