package edu.calvin.cs262.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Player player);

    @Query("DELETE FROM player_table")
    void deleteAll();

    @Query("SELECT * FROM player_table ORDER BY player_name ASC")
    LiveData<List<Player> >getAllPlayers();

    @Query("SELECT * from player_table LIMIT 1")
    Player[] getAnyPlayer();

    @Delete
    void deletePlayer(Player player);
}
