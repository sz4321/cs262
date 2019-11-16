package edu.calvin.cs262.myapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    private final GameRepository mRepository;

    private LiveData<List<Game>> mAllGames;

    /**
     *
     * @param application
     */
    public GameViewModel(Application application) {
        super(application);
        mRepository = new GameRepository(application);
        mAllGames = mRepository.getAllGames();
    }

    /**
     *
     * @return
     */
    LiveData<List<Game>> getAllGames() {
        return mAllGames;
    }

    /**
     * insert game
     * @param game
     */
    public void insert(Game game) {
        mRepository.insert(game);
    }

    /**
     * delete all
     */
    public void deleteAll() {
        mRepository.deleteAll();
    }

    /**
     * @param word
     */
    public void deleteGame(Game word) {
        mRepository.deleteGame(word);
    }

}
