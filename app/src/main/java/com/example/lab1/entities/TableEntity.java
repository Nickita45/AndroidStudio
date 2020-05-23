package com.example.lab1.entities;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Date;

public class TableEntity {
    public int id;
    public String name;
    public String date;
    public TableEntity(int id,String name, String date){
        this.id=id;
        this.name=name;
        this.date=date;
    }
    public TableEntity(String name, String date){
        this.name=name;
        this.date=date;
    }
}
