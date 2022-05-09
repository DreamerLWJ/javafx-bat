package org.example.persistence;

import org.example.model.CoordinateBatchOutputSheet;
import org.example.model.LengthInputSheet;
import org.example.model.AreaBatchOutputSheet;

import java.util.List;

/**
 * @author 罗文俊
 * 2022/5/9
 */
public class BatchCache {
    private static List<LengthInputSheet> inputData;

    private static List<AreaBatchOutputSheet> outputAreaData;

    private static List<CoordinateBatchOutputSheet> outputCoorData;

    public static boolean isInputReady() {
        return inputData != null && inputData.size() != 0;
    }

    public static boolean isOutputReady() {
        return outputAreaData != null && outputAreaData.size() != 0;
    }

    public static void saveInputCache(List<LengthInputSheet> lengthInputSheets) {
        inputData = lengthInputSheets;
    }

    public static List<LengthInputSheet> getInputCache() {
        return inputData;
    }

    public static List<AreaBatchOutputSheet> getOutputAreaData() {
        return outputAreaData;
    }

    public static List<CoordinateBatchOutputSheet> getOutputCoorData() {
        return outputCoorData;
    }

    public static void saveOutputAreaCache(List<AreaBatchOutputSheet> areaBatchOutputSheets) {
        outputAreaData = areaBatchOutputSheets;
    }

    public static void saveOutputCoorCache(List<CoordinateBatchOutputSheet> coordinateBatchOutputSheets) {
        outputCoorData = coordinateBatchOutputSheets;
    }

    public static void clearInput() {
        inputData = null;
    }

    public static void clearAll() {
        // gc to clear
        inputData = null;
        outputAreaData = null;
        outputCoorData = null;
    }
}
