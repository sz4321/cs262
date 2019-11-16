package edu.calvin.cs262.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository for game player
 */
public class GamePlayerRepository {
    private final GamePlayerDao mGamePlayerDao;
    private LiveData<List<GamePlayer>> mAllGamePlayers;

    GamePlayerRepository(Application application) {
        GamePlayerRoomDatabase db = GamePlayerRoomDatabase.getDatabase(application);
        mGamePlayerDao = db.gamePlayerDao();
    }

    LiveData<List<GamePlayer>> getAllGamePlayers() {
        return mAllGamePlayers;
    }

    /**
     * inserts a gamePlayer
     * @param gamePlayer
     */
    public void insert(GamePlayer gamePlayer) {
        new insertAsyncTask(mGamePlayerDao).execute(gamePlayer);
    }

    /**
     * deletes all of the players
     */
    public void deleteAll() {
        new deleteAllGamePlayersAsyncTask(mGamePlayerDao).execute();
    }

    /**
     * deletes a gamePlayer
     * @param gamePlayer
     */
    public void deleteGamePlayer(GamePlayer gamePlayer) {
        new deleteGamePlayerAsyncTask(mGamePlayerDao).execute(gamePlayer);
    }

    /**
     * table join - get a player from game
     * @param Id
     * @return
     */
    LiveData<List<Player>> getPlayerFromGame(Integer Id) {
        return mGamePlayerDao.getPlayerFromGame(Id);
    }

    /**
     * join table - get game from player
     * @param Id
     * @return
     */
    LiveData<List<Game>> getGameFromPlayer(Integer Id) {
        return mGamePlayerDao.getGameFromPlayer(Id);
    }

    /**
     * insert an Async task
     */
    private static class insertAsyncTask extends AsyncTask<GamePlayer, Void, Void> {

        private final GamePlayerDao mAsyncTaskDao;

        insertAsyncTask(GamePlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GamePlayer... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * delete all the game players Async task
     */
    private static class deleteAllGamePlayersAsyncTask extends AsyncTask<Void, Void, Void> {
        private final GamePlayerDao mAsyncTaskDao;

        deleteAllGamePlayersAsyncTask(GamePlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * delete the game players Async task
     */
    private static class deleteGamePlayerAsyncTask extends AsyncTask<GamePlayer, Void, Void> {
        private final GamePlayerDao mAsyncTaskDao;

        deleteGamePlayerAsyncTask(GamePlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GamePlayer... params) {
            mAsyncTaskDao.deleteGamePlayer(params[0]);
            return null;
        }
    }
}
