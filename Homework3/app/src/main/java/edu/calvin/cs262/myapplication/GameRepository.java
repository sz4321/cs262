package edu.calvin.cs262.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GameRepository {

    //game
    private final GameDao mGameDao;
    private final LiveData<List<Game>> mAllGames;

    GameRepository(Application application) {
        GameRoomDatabase db = GameRoomDatabase.getDatabase(application);
        mGameDao = db.gameDao();
        mAllGames = mGameDao.getAllGames();
    }

    /**
     * get all games
     * @return <List<Game>
     */
    LiveData<List<Game>> getAllGames() {
        return mAllGames;
    }

    /**
     * insert game
     * @param game
     */
    public void insert(Game game) {
        new GameRepository.insertAsyncTask(mGameDao).execute(game);
    }

    /**
     * deleteGame
     * @param word
     */
    public void deleteGame(Game word) {
        new deleteGameAsyncTask(mGameDao).execute(word);
    }

    /**
     * delete all games
     */
    public void deleteAll() {
        new deleteAllGamesAsyncTask(mGameDao).execute();

    }

    /**
     * insertAsyncTask for games
     */
    private static class insertAsyncTask extends AsyncTask<Game, Void, Void> {

        private final GameDao mAsyncTaskDao;

        insertAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * deleteAllGamesAsyncTask for games
     */
    private static class deleteAllGamesAsyncTask extends AsyncTask<Void, Void, Void> {
        private final GameDao mAsyncTaskDao;

        deleteAllGamesAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * deleteGameAsyncTask for game
     */
    private static class deleteGameAsyncTask extends AsyncTask<Game, Void, Void> {
        private final GameDao mAsyncTaskDao;

        deleteGameAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.deleteGame(params[0]);
            return null;
        }
    }

}
