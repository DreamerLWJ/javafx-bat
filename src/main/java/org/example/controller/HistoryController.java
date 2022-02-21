package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.example.model.History;
import org.example.persistence.HistoryRepository;
import org.example.view.HistoryCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author 罗文俊
 * 2021/12/8
 */
public class HistoryController implements Initializable {

    @FXML
    public ListView<History> listview;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<History> observableList = FXCollections.observableList(HistoryRepository.getData());
        listview.setItems(observableList);
        listview.setCellFactory(historyListView -> {
            try {
                return new HistoryCell();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
