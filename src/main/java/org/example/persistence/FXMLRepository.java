package org.example.persistence;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

/**
 * @author 罗文俊
 * 2021/12/9
 */
public class FXMLRepository {

    public static Parent getMain() {
        Parent mainScene = null;
        try {
            mainScene = FXMLLoader.load(Objects.requireNonNull(FXMLRepository.class.getClassLoader().getResource("main.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mainScene;
    }

    public static Parent getHistory() {
        Parent historyScene = null;
        try {
            historyScene = FXMLLoader.load(Objects.requireNonNull(FXMLRepository.class.getClassLoader().getResource("history.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyScene;
    }

    public static Parent getAngle() {
        Parent angleScene = null;
        try {
            angleScene = FXMLLoader.load(Objects.requireNonNull(FXMLRepository.class.getClassLoader().getResource("angle.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return angleScene;
    }

    public static Parent getResult() {
        Parent resultScene = null;
        try {
            resultScene = FXMLLoader.load(Objects.requireNonNull(FXMLRepository.class.getClassLoader().getResource("result.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultScene;
    }

    public static Parent getBatch() {
        Parent batchScene = null;
        try {
            batchScene = FXMLLoader.load(Objects.requireNonNull(FXMLRepository.class.getClassLoader().getResource("batch.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return batchScene;
    }
}
