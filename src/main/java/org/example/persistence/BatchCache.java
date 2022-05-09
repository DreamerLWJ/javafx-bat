package org.example.persistence;

import org.example.model.LengthInputSheet;
import org.example.model.ResultOutputSheet;

import javax.xml.transform.Result;
import java.util.List;

/**
 * @author 罗文俊
 * 2022/5/9
 */
public class BatchCache {
    private static List<LengthInputSheet> inputData;

    private static List<ResultOutputSheet> outputData;

    public static boolean isInputReady() {
        return inputData != null && inputData.size() != 0;
    }

    public static boolean isOutputReady() {
        return outputData != null && outputData.size() != 0;
    }

    public static void saveInputCache(List<LengthInputSheet> lengthInputSheets) {
        inputData = lengthInputSheets;
    }

    public static List<LengthInputSheet> getInputCache() {
        return inputData;
    }

    public static List<ResultOutputSheet> getOutputData() {
        return outputData;
    }

    public static void saveOutputCache(List<ResultOutputSheet> resultOutputSheets) {
        outputData = resultOutputSheets;
    }

    public static void clearInput() {
        inputData = null;
    }

    public static void clearAll() {
        // gc to clear
        inputData = null;
        outputData = null;
    }
}
