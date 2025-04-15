package com.example;

import java.io.IOException;

import com.google.gson.Gson;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;

public class intruc {

    @FXML
    private Button refresh;

    @FXML
    private Button delete;

    @FXML
    private Button up;

    @FXML
    private Button star;

    @FXML
    private Button down;
    

    private String currentValue;

    @FXML
    private ListView<String> list;

    private ObservableList<String> students;

    private static Names names;
    @FXML
    void initialize(){
        students= FXCollections.observableArrayList();
        list.setItems(students);
        recieve();
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // code in here will trigger anytime the selected item changes
            currentValue=newValue;
            System.out.println("Selected user: " + newValue);
        });
    }
    

    @FXML
    void RemoveStudent(ActionEvent event) {
        int x =list.getSelectionModel().getSelectedIndex();
        if(x!=-1){
            String y=students.remove(x);
            names.remove(y);
            System.out.println(names);
        }
    }

    @FXML
    void moveu(ActionEvent event) {
        int x =list.getSelectionModel().getSelectedIndex();
        if(x!=-1){
            String y=students.remove(x);
            names.remove(y);
            System.out.println(names);
        }
    }

    @FXML
    void moved(ActionEvent event) {
        int x =list.getSelectionModel().getSelectedIndex();
        if(x!=-1){
            String y=students.remove(x);
            
            names.remove(y);
            System.out.println(names);
        }
    }

    public static void setnames(Names x){
            names = x;
        }

        public void Refresh(){
            recieve();
            students.clear();
            students.addAll(names.getList());
            
        }

        public void send() {
            OkHttpClient client = new OkHttpClient();
            Request request = (new Request.Builder()).url("http://localhost:4567/RemoveFromAdmin").build();
      
            try {
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println(e+"\t Request failed! 1");
                        }
        
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                Gson gson = new Gson();
                               
                               Names n = gson.fromJson(response.body().string(), Names.class);
                                intruc.setnames(n);
                                System.out.println(n);
                    } else {
                        System.out.println("Request failed! 2");
                    }
                }

            });
        } catch (Exception e) {
            System.out.println(e +"\t here");
        }
  
     }





        public void recieve() {
            OkHttpClient client = new OkHttpClient();
            Request request = (new Request.Builder()).url("http://localhost:4567/messages").build();
      
            try {
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println(e+"\t Request failed! 1");
                        }
        
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                Gson gson = new Gson();
                               
                               Names n = gson.fromJson(response.body().string(), Names.class);
                                intruc.setnames(n);
                                System.out.println(n);
                    } else {
                        System.out.println("Request failed! 2");
                    }
                }

            });
        } catch (Exception e) {
            System.out.println(e +"\t here");
        }
  
     }
}
 