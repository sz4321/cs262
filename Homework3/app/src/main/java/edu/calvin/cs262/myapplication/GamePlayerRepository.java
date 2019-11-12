package edu.calvin.cs262.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GamePlayerRepository {
    private GamePlayerDao mGamePlayerDao;
    private LiveData<List<GamePlayer>> mAllGamePlayers;

    GamePlayerRepository(Application application) {
        GamePlayerRoomDatabase db = GamePlayerRoomDatabase.getDatabase(application);
        mGamePlayerDao = db.gamePlayerDao();
    }

    LiveData<List<GamePlayer>> getAllGamePlayers() {
        return mAllGamePlayers;
    }

    public void insert (GamePlayer gamePlayer) {
        new insertAsyncTask(mGamePlayerDao).execute(gamePlayer);
    }

    private static class insertAsyncTask extends AsyncTask<GamePlayer, Void, Void> {

        private GamePlayerDao mAsyncTaskDao;

        insertAsyncTask(GamePlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GamePlayer... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAllGamePlayersAsyncTask extends AsyncTask<Void, Void, Void> {
        private GamePlayerDao mAsyncTaskDao;

        deleteAllGamePlayersAsyncTask(GamePlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    public void deleteAll()  {
        new deleteAllGamePlayersAsyncTask(mGamePlayerDao).execute();
    }

    private static class deleteGamePlayerAsyncTask extends AsyncTask<GamePlayer, Void, Void> {
        private GamePlayerDao mAsyncTaskDao;

        deleteGamePlayerAsyncTask(GamePlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GamePlayer... params) {
            mAsyncTaskDao.deleteGamePlayer(params[0]);
            return null;
        }
    }
    public void deleteGamePlayer(GamePlayer gamePlayer)  {
        new deleteGamePlayerAsyncTask(mGamePlayerDao).execute(gamePlayer);
    }


    LiveData<List<Player>> getPlayerFromGame(Integer Id){
        return mGamePlayerDao.getPlayerFromGame(Id);
    }

    LiveData<List<Game>> getGameFromPlayer(Integer Id){
        return mGamePlayerDao.getGameFromPlayer(Id);
    }
}
