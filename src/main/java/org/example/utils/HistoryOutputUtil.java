package org.example.utils;

import org.example.model.History;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author 罗文俊
 * 2021/12/25
 * 历史信息输入工具
 */
public class HistoryOutputUtil {

    public static boolean prettyOutput(History history, File file) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        printWriter.println("记录序号：" + history.getId());
        printWriter.println("记录日期：" + history.getDate());
        printWriter.println("备注：" + history.getRemark());
        printWriter.println("#################################");
        printWriter.println("############## 角度 ##############");
        double[] b = history.getB();
        for (int i = 0; i < b.length; i++) {
            printWriter.println("b" + (i + 1) + "：" + b[i]);
        }
        printWriter.println("############## 长度 ##############");
        double[] a = history.getA();
        for (int i = 0; i < a.length; i++) {
            printWriter.println("a" + (i + 1) + "：" + a[i]);
        }
        printWriter.println("############## 结果 ##############");
        double[] r = history.getR();
        for (int i = 0; i < r.length - 1; i++) {
            printWriter.println((i + 1) + "区面积为：" + r[i]);
        }
        printWriter.println("总面积为：" + r[r.length - 1]);
        printWriter.flush();
        return true;
    }
}
