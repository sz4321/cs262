package edu.calvin.cs262.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlayerRepository {

    // ------------------------------------------------------------------------------------------------------------
    //player
    private final PlayerDao mPlayerDao;
    private final LiveData<List<Player>> mAllPlayers;

    PlayerRepository(Application application) {
        PlayerRoomDatabase db = PlayerRoomDatabase.getDatabase(application);
        mPlayerDao = db.playerDao();
        mAllPlayers = mPlayerDao.getAllPlayers();
    }


    LiveData<List<Player>> getAllPlayers() {
        return mAllPlayers;
    }

    public void insert(Player player) {
        new insertAsyncTask(mPlayerDao).execute(player);
    }

    //also delete game
    public void deleteAll() {
        new deleteAllPlayersAsyncTask(mPlayerDao).execute();

    }

    /**
     *
     * @param word
     */
    public void deletePlayer(Player word) {
        new deletePlayerAsyncTask(mPlayerDao).execute(word);
    }


    private static class insertAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDao mAsyncTaskDao;

        insertAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllPlayersAsyncTask extends AsyncTask<Void, Void, Void> {
        private final PlayerDao mAsyncTaskDao;

        deleteAllPlayersAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deletePlayerAsyncTask extends AsyncTask<Player, Void, Void> {
        private final PlayerDao mAsyncTaskDao;

        deletePlayerAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            mAsyncTaskDao.deletePlayer(params[0]);
            return null;
        }
    }


}
