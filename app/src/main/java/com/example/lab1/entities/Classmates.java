package com.example.lab1.entities;

import java.io.Serializable;

public class Classmates implements Serializable {
    public int id;
    public String name;
    public String secondname;
    public String otchestvo;
    public String date;

    public Classmates(int id,String name,String secondname,String otchestvo, String date){
        this.id=id;
        this.name=name;
        this.secondname=secondname;
        this.otchestvo=otchestvo;
        this.date=date;

    }

}
