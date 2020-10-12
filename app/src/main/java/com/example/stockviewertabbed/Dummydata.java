package com.example.stockviewertabbed;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dummydata")
public class Dummydata {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="stockname")
    private String stockname;

    @ColumnInfo(name = "price")
    private  String price;

    public Dummydata(String stockname,  String price) {
        this.stockname = stockname;
        this.price = price;
    }

    public String getStockname(){
        return this.stockname;}
}
