package org.example.service;

import org.example.model.History;
import org.example.model.State;
import org.example.persistence.HistoryRepository;
import org.example.persistence.StateRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class CalculateService {

    public History calculate(double[] a, String remark) {
        // 获取保存的角度
        double[] b = StateRepository.getAngles();
        double b1 = b[0];
        double b2 = b[1];
        double b3 = b[2];
        double b4 = b[3];
        double b5 = b[4];
        double b6 = b[5];
        double b7 = b[6];
        double b8 = b[7];
        double b9 = b[8];
        double b10 = b[9];
        double b11 = b[10];
        double b12 = b[11];
        double b13 = b[12];
        double b14 = b[13];
        double b15 = b[14];
        double b16 = b[15];

        // 获取输入的值
        double a1 = a[0];
        double a2 = a[1];
        double a3 = a[2];
        double a4 = a[3];
        double a5 = a[4];
        double a6 = a[5];
        double a7 = a[6];
        double a8 = a[7];
        double a9 = a[8];
        double a10 = a[9];
        double a11 = a[10];
        double a12 = a[11];
        double a13 = a[12];
        double a14 = a[13];
        double a15 = a[14];
        double a16 = a[15];
        double a17 = a[16];
        double a18 = a[17];

        double x1 = 0;
        double x2 = -a1 * Math.sin(b1 * Math.PI / 180) + x1;
        double x3 = -a2 * Math.sin((b2 - b1) * Math.PI / 180) + x2;
        double x4 = -a3 * Math.sin((b3 - (b2 - b1)) * Math.PI / 180) + x3;
        double x5 = a4 * Math.sin((b4 + b3 - (b2 - b1)) * Math.PI / 180) + x4;
        double x6 = -a5 * Math.sin((b5 + b4 + b3 - (b2 - b1)) * Math.PI / 180) + x5;
        double x7 = -a6 * Math.sin((b6 + b3 - (b2 - b1)) * Math.PI / 180) + x3;
        double x8 = a7 * Math.sin((b7 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + x7;
        double x9 = -a8 * Math.sin((b8 + b7 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + x8;
        double x10 = -a9 * Math.sin((b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + x3;
        double x11 = a10 * Math.sin((b10 + b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + x10;
        double x12 = -a11 * Math.sin((b11 + b10 + b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + x11;
        double x13 = -a12 * Math.sin((b12 + b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + x3;
        double x14 = a13 * Math.sin((b13 + b12 + b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + x13;
        double x15 = x1;
        double x16 = -a15 * Math.sin(b14 * Math.PI / 180) + x15;
        double x17 = a16 * Math.sin((b15 + b14) * Math.PI / 180) + x16;
        double x18 = -a17 * Math.sin((b16 + b15 + b14) * Math.PI / 180) + x17;
        double x19 = x1;

        double[] x = new double[]{x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x19};


        double y1 = 0;
        double y2 = -a1 * Math.cos(b1 * Math.PI / 180) + y1;
        double y3 = a2 * Math.cos((b2 - b1) * Math.PI / 180) + y2;
        double y4 = -a3 * Math.cos((b3 - (b2 - b1)) * Math.PI / 180) + y3;
        double y5 = a4 * Math.cos((b4 + b3 - (b2 - b1)) * Math.PI / 180) + y4;
        double y6 = -a5 * Math.cos((b5 + b4 + b3 - (b2 - b1)) * Math.PI / 180) + y5;
        double y7 = -a6 * Math.cos((b6 + b3 - (b2 - b1)) * Math.PI / 180) + y3;
        double y8 = a7 * Math.cos((b7 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + y7;
        double y9 = -a8 * Math.cos((b8 + b7 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + y8;
        double y10 = -a9 * Math.cos((b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + y3;
        double y11 = a10 * Math.cos((b10 + b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + y10;
        double y12 = -a11 * Math.cos((b11 + b10 + b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + y11;
        double y13 = -a12 * Math.cos((b12 + b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + y3;
        double y14 = a13 * Math.cos((b13 + b12 + b9 + b6 + b3 - (b2 - b1)) * Math.PI / 180) + y13;
        double y15 = -a14 + y1;
        double y16 = -a15 * Math.cos(b14 * Math.PI / 180) + y15;
        double y17 = a16 * Math.cos((b15 + b14) * Math.PI / 180) + y16;
        double y18 = -a17 * Math.cos((b16 + b15 + b14) * Math.PI / 180) + y17;
        double y19 = -a18 + y15;

        double[] y = new double[]{y1, y2, y3, y4, y5, y6, y7, y8, y9, y10, y11, y12, y13, y14, y15, y16, y17, y18, y19};


        double r1 = 0.5 * Math.abs(x3 * y13 - y3 * x13 + x13 * y14 - y13 * x14 + x14 * y10 - y14 * x10 + x10 * y3 - y10 * x3);
        double r2 = 0.5 * Math.abs(x3 * y10 - y3 * x10 + x10 * y11 - y10 * x11 + x11 * y12 - y11 * x12 + x12 * y9 - y12 * x9 + x9 * y8 - y9 * x8 + x8 * y7 - y8 * x7 + x7 * y3 - y7 * x3);
        double r3 = 0.5 * Math.abs(x3 * y7 - y3 * x7 + x7 * y8 - y7 * x8 + x8 * y9 - y8 * x9 + x9 * y6 - y9 * x6 + x6 * y5 - y6 * x5 + x5 * y4 - y5 * x4 + x4 * y3 - y4 * x3);
        double r4 = 0.5 * Math.abs(x3 * y2 - y3 * x2 + x2 * y1 - y2 * x1 + x1 * y3 - y1 * x3);
        double r5 = 0.5 * Math.abs(x3 * y4 - y3 * x4 + x4 * y5 - y4 * x5 + x5 * y6 - y5 * x6 + x6 * y17 - y6 * x17 + x17 * y16 - y17 * x16 + x16 * y15 - y16 * x15 + x15 * y1 - y15 * x1 + x1 * y2 - y1 * x2 + x2 * y3 - y2 * x3);
        double r6 = 0.5 * Math.abs(x15 * y16 - y15 * x16 + x16 * y17 - y16 * x17 + x17 * y18 - y17 * x18 + x18 * y19 - y18 * x19 + x19 * y15 - y19 * x15);
        double r7 = r1 + r2 + r3 + r4 + r5 + r6;

        double[] r = new double[]{r1, r2, r3, r4, r5, r6, r7};

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone timeZoneChina1 = TimeZone.getTimeZone("Asia/Shanghai");
        sdf.setTimeZone(timeZoneChina1);
        String date = sdf.format(new Date());
        History history = new History()
                .setA(a)
                .setB(b)
                .setDate(date)
                .setR(r)
                .setRemark(remark)
                .setId(HistoryRepository.getHistoryNonce() + 1)
                .setX(x)
                .setY(y);
        return history;
    }
}
