package edu.calvin.cs262.myapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "game_player",
        primaryKeys = {"playerID", "gameID"},
        foreignKeys = {
                @ForeignKey(entity = Game.class,
                        parentColumns = "ID",
                        childColumns = "gameID"),
                @ForeignKey(entity = Player.class,
                        parentColumns = "ID",
                        childColumns = "playerID")
        })
public class GamePlayer {
    @NonNull
    private Integer playerID;

    public Integer getPlayerID() {
        return this.playerID;
    }

    @NonNull
    private Integer gameID;
    public Integer getGameID() {
        return this.gameID;
    }

    public GamePlayer(@NonNull Integer playerID, @NonNull Integer gameID){
        this.playerID = playerID;
        this.gameID = gameID;
    }
}

