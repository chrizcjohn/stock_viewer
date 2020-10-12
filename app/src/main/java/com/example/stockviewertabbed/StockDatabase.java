package com.example.stockviewertabbed;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Dummydata.class,exportSchema = false,version = 1)
public abstract class StockDatabase extends RoomDatabase {
    private static final String DB_NAME = "stockdata.db";

    private static StockDatabase instance;

    public static synchronized StockDatabase getInstance(Context context){
        if(instance ==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),StockDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
    public abstract DataAccesser dataAccesser();
}
