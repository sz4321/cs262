package edu.calvin.cs262.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GamePlayerDao {

    @Insert
    void insert(GamePlayer gamePlayer);

    @Query("DELETE FROM game_player")
    void deleteAll();

    @Delete
    void deleteGamePlayer(GamePlayer gamePlayer);


    @Query("SELECT * FROM game_player")
    LiveData<List<GamePlayer>> getAllGamePlayer();

    @Query("SELECT * FROM game_player LIMIT 1")
    GamePlayer[] getAnyGamePlayer();

    //get player using ID
    @Query("SELECT * FROM player_table " +
            "INNER JOIN game_player " +
            "ON player_table.ID = game_player.playerID " +
            "WHERE game_player.gameID=:gameID")
    LiveData<List<Player>> getPlayerFromGame(Integer gameID);

    @Query("SELECT * FROM game_table " +
            "INNER JOIN game_player " +
            "ON game_table.ID = game_player.gameID " +
            "WHERE game_player.playerID=:playerID")
    LiveData<List<Game>> getGameFromPlayer(Integer playerID);
}
