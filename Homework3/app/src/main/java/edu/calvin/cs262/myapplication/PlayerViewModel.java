package edu.calvin.cs262.myapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerRepository mRepository;

    private LiveData<List<Player>> mAllPlayers;


    /**
     *
     * @param application
     */
    public PlayerViewModel(Application application) {
        super(application);
        mRepository = new PlayerRepository(application);
        mAllPlayers = mRepository.getAllPlayers();
    }

    LiveData<List<Player>> getAllPlayers() {
        return mAllPlayers;
    }

    /**
     *
     * @param player
     */
    public void insert(Player player) {
        mRepository.insert(player);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    /**
     *
     * @param word
     */
    public void deletePlayer(Player word) {
        mRepository.deletePlayer(word);
    }

}
