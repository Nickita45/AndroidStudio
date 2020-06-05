package com.example.lab1.entities;

import java.io.Serializable;

public class Classmates implements Serializable {
    public int id;
    public String fio;
    public String date;

    public Classmates(int id,String fio, String date){
        this.id=id;
        this.fio=fio;
        this.date=date;

    }
    public Classmates(String fio, String date){
        this.fio=fio;
        this.date=date;

    }
}
