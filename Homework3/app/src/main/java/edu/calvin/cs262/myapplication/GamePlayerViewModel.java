package edu.calvin.cs262.myapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Player view model for game player
 */
public class GamePlayerViewModel extends AndroidViewModel {
    private final GamePlayerRepository mRepository;

    private LiveData<List<GamePlayer>> mAllGamePlayers;

    /**
     * @param application
     */
    public GamePlayerViewModel(Application application) {
        super(application);
        mRepository = new GamePlayerRepository(application);
        mAllGamePlayers = mRepository.getAllGamePlayers();
    }

    /**
     * get all the game players
     * @return <List<GamePlayer>
     */
    LiveData<List<GamePlayer>> getAllGamePlayers() {
        return mAllGamePlayers;
    }

    /**
     * get player from game
     * @param id
     * @return
     */
    LiveData<List<Player>> getPlayerFromGame(Integer id) {
        return mRepository.getPlayerFromGame(id);
    }

    /**
     * get game from player
     * @param id
     * @return
     */
    LiveData<List<Game>> getGameFromPlayer(Integer id) {
        return mRepository.getGameFromPlayer(id);
    }

    /**
     * insert
     * @param gamePlayer
     */
    public void insert(GamePlayer gamePlayer) {
        mRepository.insert(gamePlayer);
    }

    /**
     * delete all game player
     */
    public void deleteAll() {
        mRepository.deleteAll();
    }

    /**
     * delete game player
     * @param gamePlayer
     */
    public void deleteGamePlayer(GamePlayer gamePlayer) {
        mRepository.deleteGamePlayer(gamePlayer);
    }

}
