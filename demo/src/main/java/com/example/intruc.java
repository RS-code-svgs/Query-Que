package com.example;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class intruc {

    @FXML
    private Button refresh;

    @FXML
    private Button delete;

    @FXML
    private ListView<String> list;

    private ObservableList<String> students;

    @FXML
    private Button up;

    @FXML
    private Button star;

    @FXML
    private Button down;
    

    private String currentValue;
    @FXML
    void initialize(){
        students= FXCollections.observableArrayList();
        list.setItems(students);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // code in here will trigger anytime the selected item changes
            currentValue=newValue;
            System.out.println("Selected user: " + newValue);
            /*if(newValue==null){
            achList.setVisible(false);
            addAch.setVisible(false);
            removeAch.setVisible(false);
            }*/
        });
    }
    
    private void recieve(){
        
    }
}
 