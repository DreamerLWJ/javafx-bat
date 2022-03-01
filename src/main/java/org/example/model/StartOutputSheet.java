package org.example.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 罗文俊
 * 2022/3/1
 */
@Data
@Accessors(chain = true)
public class StartOutputSheet {
    @ExcelProperty("记录序号")
    private Integer num;

    @ExcelProperty("记录日期")
    private String recordDate;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("引用文献")
    private String citation;

    @ExcelProperty("软件开发")
    private String developer;
}
