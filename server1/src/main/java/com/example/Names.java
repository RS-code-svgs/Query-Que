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
    public void star(String x,String t){
        if(t.equals("true")){
            System.out.println(x);
            int y= names.indexOf(x);
            names.set(y, x + "*");
        } else{
            int y = names.indexOf(x);
            String r=names.get(y);
            names.set(y, r.substring(0, r.indexOf("*")));
        }
    }

    public ArrayList<String> getList(){
        return names;
    }

    public void up(String n){
        int x = names.indexOf(n);
        String y=names.get(x-1);
        names.set(x-1, names.get(x));
        names.set(x, y);
    }
    public void down(String n){
        int x = names.indexOf(n);
        String y=names.get(x+1);
        names.set(x+1, names.get(x));
        names.set(x, y);
    }
}
