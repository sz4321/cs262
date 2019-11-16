package edu.calvin.cs262.myapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_table")
public class Player {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    private Integer ID;

    @NonNull
    @ColumnInfo(name = "player_name")
    private String playerName;
    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    public Player(@NonNull Integer ID, @NonNull String playerName, @NonNull String email) {
        this.ID = ID;
        this.playerName = playerName;
        this.email = email;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getID() {
        return this.ID;
    }


}
