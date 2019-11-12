package edu.calvin.cs262.myapplication;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    private GameRepository mRepository;

    private LiveData<List<Game>> mAllGames;


    public GameViewModel (Application application) {
        super(application);
        mRepository = new GameRepository(application);
        mAllGames = mRepository.getAllGames();
    }

    LiveData<List<Game>> getAllGames() { return mAllGames; }
    public void insert(Game game) {
        mRepository.insert(game); }
    public void deleteAll() {mRepository.deleteAll();}
    public void deleteGame(Game word) {mRepository.deleteGame(word);}

}
