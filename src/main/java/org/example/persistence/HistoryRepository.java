package org.example.persistence;

import com.alibaba.fastjson.JSONObject;
import org.example.model.History;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class HistoryRepository {
    /**
     * 保存的位置
     */
    private final static String filepath = "history.txt";

    /**
     * 数据队列
     */
    private final static ArrayBlockingQueue<History> dataQueue = new ArrayBlockingQueue<>(6);


    private final static int saveSize = 6;

    static {
        init();
    }

    public static void init() {
        load();
    }

    public static List<History> getData() {
        return new ArrayList<>(dataQueue);
    }

    public static History getNewest() {
        Object[] histories = dataQueue.toArray();
        History history = (History) histories[histories.length - 1];
        return history;
    }

    public static synchronized void save(History history) {
        if (dataQueue.size() >= saveSize) {
            dataQueue.remove();
            dataQueue.offer(history);
        } else {
            dataQueue.offer(history);
        }
    }

    public static void saveFile() {
        File file = new File(filepath);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (History history : dataQueue) {
                printWriter.println(JSONObject.toJSON(history));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        // open file
        File file = new File(filepath);
        if (!file.exists()) {
            // 如果文件不存在
            try {
                if (!file.createNewFile()) {
                    throw new RuntimeException("文件创建失败！");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // read file
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                History history = JSONObject.parseObject(line, History.class);
                if (dataQueue.size() > saveSize) {
                    dataQueue.remove();
                    dataQueue.offer(history);
                } else {
                    dataQueue.offer(history);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Integer getHistoryNonce() {
        Object[] histories = dataQueue.toArray();
        if (histories.length == 0) {
            return 0;
        }
        History history = (History) histories[histories.length - 1];
        if (history == null) {
            return 0;
        }
        return history.getId();
    }
}
