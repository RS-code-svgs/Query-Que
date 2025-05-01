package com.example;

import java.io.IOException;

import com.google.gson.Gson;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.FormBody;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

    @FXML
    private TextField currentCode;
    

    private static String currentValue;
    
        @FXML
        private ListView<Text> list;
    
        private ObservableList<Text> students;
    
        private static Names names;
        @FXML
        void initialize(){
            students= FXCollections.observableArrayList();
            list.setItems(students);
            recieve();
            change();
            currentCode.setPromptText(currentValue);
            list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                // code in here will trigger anytime the selected item changes
                //currentValue=newValue;
                //System.out.println("Selected user: " + newValue);
            });
        }
        
        @FXML
        void Star(ActionEvent event){
            int x =list.getSelectionModel().getSelectedIndex();
            Text thing = list.getItems().get(x);
            String y="true";
            if(thing.getFill().equals(Color.GOLD)){
                y="false";
            }
            String t = thing.getText();
            /*Text s = new Text("☆");
            s.setFill(Color.GOLD);
            thing.setText(thing.getText()+"\t \t"+s);
            System.out.println(thing.toString());*/
            thing.setFill(Color.GOLD);
           OkHttpClient client = new OkHttpClient();
           RequestBody formBody = new FormBody.Builder()
           .add("name",t+"*")
           .add("color",y)
           .build();
       Request request = new Request.Builder()
           .url("http://localhost:4567/star")
           .post(formBody)
           .build();
    
       try (Response response = client.newCall(request).execute()) {
     
             System.out.println(response.body().string());
       } catch (Exception e) {
           System.out.println(e);
       }
        }
    
    
        @FXML
        void RemoveStudent(ActionEvent event) {
            int x =list.getSelectionModel().getSelectedIndex();
            Text y = new Text();
            if(x!=-1){
                y=students.remove(x);
                names.remove(y.getText());
                System.out.println(names);
            }
            send(y.getText());
        }
    
        @FXML
        void moveu(ActionEvent event) {
            int x =list.getSelectionModel().getSelectedIndex();
            if(x>0){
                Text y = students.get(x);
                students.set(x, students.get(x-1));
                students.set(x-1, y);
                move("up",y.getText());
            }
        }
    
        @FXML
        void moved(ActionEvent event) {
            int x =list.getSelectionModel().getSelectedIndex();
            if(x>-1 && x<students.size()-1){
                Text y = students.get(x);
                students.set(x, students.get(x+1));
                students.set(x+1, y);
                move("down",y.getText());
            }
        }
    
        public static void setnames(Names x){
                names = x;
            }
    
            @FXML
            public void changec(){
                change();
                currentCode.setPromptText(currentValue);
            OkHttpClient client = new OkHttpClient();
           RequestBody formBody = new FormBody.Builder()
           .add("code",currentValue)
           .build();
       Request request = new Request.Builder()
           .url("http://localhost:4567/changec")
           .post(formBody)
           .build();
    
       try (Response response = client.newCall(request).execute()) {
     
             System.out.println(response.body().string());
       } catch (Exception e) {
           System.out.println(e);
       }
            }
            
         public static void setCode(String x){
                currentValue = x;
        }

        public void Refresh(){
            recieve();
            students.clear();
            for(String name : names.getList()) {
                Text x = new Text(name);
                if(name.indexOf("*")>-1) {
                    String t =name.substring(0, name.indexOf("*"));
                    x.setFill(Color.GOLD);
                    x.setText(t);
                }
                students.add(x);
                
            }


           // students.addAll(names.getList());
            
        }


        public void move(String x,String y) {
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
            .add("direction", x)
            .add("name",y)
            .build();
        Request request = new Request.Builder()
            .url("http://localhost:4567/move")
            .post(formBody)
            .build();
    
        try (Response response = client.newCall(request).execute()) {
      
              System.out.println(response.body().string());
        } catch (Exception e) {
            System.out.println(e);
        }
        }

        
        public void send(String x) {
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
            .add("search", x)
            .build();
        Request request = new Request.Builder()
            .url("http://localhost:4567/RemoveFromAdmin")
            .post(formBody)
            .build();
    
        try (Response response = client.newCall(request).execute()) {
      
              System.out.println(response.body().string());
        } catch (Exception e) {
            System.out.println(e);
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

     public void change() {
        OkHttpClient client = new OkHttpClient();
        Request request = (new Request.Builder()).url("http://localhost:4567/change").build();
  
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
                           
                           String n = gson.fromJson(response.body().string(), String.class);
                            intruc.setCode(n);
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
 