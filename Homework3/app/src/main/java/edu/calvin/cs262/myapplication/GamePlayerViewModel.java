package edu.calvin.cs262.myapplication;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class GamePlayerViewModel extends AndroidViewModel {
    private GamePlayerRepository mRepository;

    private LiveData<List<GamePlayer>> mAllGamePlayers;


    public GamePlayerViewModel (Application application) {
        super(application);
        mRepository = new GamePlayerRepository(application);
        mAllGamePlayers = mRepository.getAllGamePlayers();
    }

    LiveData<List<GamePlayer>> getAllGamePlayers() { return mAllGamePlayers; }
    LiveData<List<Player>> getPlayerFromGame(Integer id) { return mRepository.getPlayerFromGame(id);}
    LiveData<List<Game>> getGameFromPlayer(Integer id) {return mRepository.getGameFromPlayer(id);}
    public void insert(GamePlayer gamePlayer) { mRepository.insert(gamePlayer); }
    public void deleteAll() {mRepository.deleteAll();}
    public void deleteGamePlayer(GamePlayer gamePlayer) {mRepository.deleteGamePlayer(gamePlayer);}

}
