package edu.calvin.cs262.myapplication;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(tableName = "game_table")
public class Game {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    private Integer ID;

    @NonNull
    public Integer getID() {
        return this.ID;
    }

    @NonNull
    @ColumnInfo(name = "time_stamp")
    private String timeStamp;

    @NonNull
    public String getTimeStamp(){
        return this.timeStamp;
    }


    public Game(@NonNull Integer ID, @NonNull String timeStamp){
        this.ID = ID;
        this.timeStamp = timeStamp;
    }

}
