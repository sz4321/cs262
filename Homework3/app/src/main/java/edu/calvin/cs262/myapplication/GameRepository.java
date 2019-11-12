package edu.calvin.cs262.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GameRepository {

    //game
    private GameDao mGameDao;
    private LiveData<List<Game>> mAllGames;

    GameRepository(Application application) {
        GameRoomDatabase db = GameRoomDatabase.getDatabase(application);
        mGameDao = db.gameDao();
        mAllGames = mGameDao.getAllGames();
    }



    LiveData<List<Game>> getAllGames(){
        return mAllGames;
    }

    public void insert (Game game) {
        new GameRepository.insertAsyncTask(mGameDao).execute(game);
    }

    private static class insertAsyncTask extends AsyncTask<Game, Void, Void> {

        private GameDao mAsyncTaskDao;

        insertAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllGamesAsyncTask extends AsyncTask<Void, Void, Void> {
        private GameDao mAsyncTaskDao;

        deleteAllGamesAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteGameAsyncTask extends AsyncTask<Game, Void, Void> {
        private GameDao mAsyncTaskDao;

        deleteGameAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.deleteGame(params[0]);
            return null;
        }
    }

    public void deleteGame(Game word)  {
        new deleteGameAsyncTask(mGameDao).execute(word);
    }

    //also delete game
    public void deleteAll()  {
        new deleteAllGamesAsyncTask(mGameDao).execute();

    }

}
