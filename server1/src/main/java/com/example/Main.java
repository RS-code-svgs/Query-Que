package com.example;

import java.util.*;

import static spark.Spark.*;

import com.google.gson.Gson;

public class Main {
    private static ArrayList<String> names = new ArrayList<>();

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

        /*get("/",(req,res)->{
            res.redirect("/final.html");
            return null;
        });*/

        post("/submit",(req,res)->{
            String code =(req.queryParams("classcode"));
            String name =(req.queryParams("name"));
           
            System.out.println(code);
            System.out.println(name);
            System.out.println("Clicked submit");
           if(code.equals(classCode)){
                names.add(name);
                res.redirect("/final.html");
            } else{
                res.redirect("/deny.html");
            }
            res.status(201);
            return "";
        });

        get("/messages",(req,res)->{
            res.type("application/json");
            return gson.toJson(names);
        });

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