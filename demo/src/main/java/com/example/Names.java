package com.example;

import java.util.ArrayList;

public class Names {
    ArrayList<String> names;
    public Names(){
        names = new ArrayList<String>();
    }

    public void add(String n){
        names.add(n);
    }

    public void remove(String x){
        int t=names.indexOf(x);
        names.remove(t);
    }

    public String toString() {
        return names.toString();
    }

    public ArrayList<String> getList(){
        return names;
    }
}
