package model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 罗文俊
 * 2021/12/8
 */
@Data
@Accessors(chain = true)
public class History {

    private Integer id;

    private double[] a = new double[18];

    private double[] b = new double[16];

    private double[] r = new double[7];

    private String remark;

    private String date;
}
