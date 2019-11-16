package edu.calvin.cs262.myapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

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
    @NonNull
    private Integer gameID;

    public GamePlayer(@NonNull Integer playerID, @NonNull Integer gameID) {
        this.playerID = playerID;
        this.gameID = gameID;
    }

    public Integer getPlayerID() {
        return this.playerID;
    }

    public Integer getGameID() {
        return this.gameID;
    }
}

