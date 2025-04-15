package com.example;

import java.io.IOException;
import java.util.*;

import static spark.Spark.*;

import com.google.gson.Gson;

import spark.Session;



public class Main {
    private static Names names = new Names();

    private static String classCode="Coding #1";
    public static void main(String[] args) {
        System.out.println("Hello world!");
        port(4567);
        staticFiles.location("/public");

        Gson gson = new Gson();
        disableCORS();

        get("/home",(req,res)->{
            res.redirect("/home.html");
            return("This is a test");
        });
        
        get("/final",(req,res)->{
            res.redirect("/home.html");
            return("This is a test");
        });


        post("/submit",(req,res)->{
            String code =(req.queryParams("classcode"));
            String name =(req.queryParams("name"));
           
            /*System.out.println(code);
            System.out.println(name);
            System.out.println("Clicked submit");*/


           if(code.equals(classCode)){
            Session session = req.session(true);  // true creates a session if none exists
            session.attribute("username", name); 

                
                                names.add(name);
                                res.redirect("/final.html");
                            } else{
                                res.redirect("/deny.html");
                            }
                            res.status(201);
                            return "";
                        });

                        post("/removeFromAdmin",(req,res)->{

                        });
                
                        post("/remove",(req,res)->{
                            //System.out.println("Clicked remove");
                           
                            Session session = req.session(false); // false won't create new session
                            String username = session.attribute("username");
                        if (username == null) {
                            res.redirect("/home.html");  // maybe force them to log in again
                
                            return null;
                            }
                
                            names.remove(username);
                            res.redirect("/home.html");
                            res.status(201);
                
                            return "";
                        });
                
                        
                
                        get("/messages",(req,res)->{
                            res.type("application/json");
                            return gson.toJson(names);
                        });
                
                        
                
                    }
                        public static void setnames(Names x){
                        names = x;
                    }

    public static void disableCORS() {
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        });

        options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
    }
}