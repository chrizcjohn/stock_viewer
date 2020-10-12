package com.example.stockviewertabbed;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface DataAccesser {

    @Query("Select * from dummydata")
    List<Dummydata> getDummydata();

    @Insert
    void insertDummydata(Dummydata dummydata);


}
