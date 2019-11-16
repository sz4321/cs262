package edu.calvin.cs262.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Game.class}, version = 1, exportSchema = false)

/**
 * Roombase database for game
 */
public abstract class GameRoomDatabase extends RoomDatabase {
    private static GameRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * @param context
     * @return
     */
    public static GameRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GameRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GameRoomDatabase.class, "game_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract GameDao gameDao();

    /**
     * PopulateDbAsync
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final GameDao mDao;
        Game game;
        String[] games = {};

        PopulateDbAsync(GameRoomDatabase db) {
            mDao = db.gameDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            if (mDao.getAnyGame().length < 1) {
                mDao.insert(new Game(1, "12:12:12"));
                mDao.insert(new Game(2, "1:2:3"));
                mDao.insert(new Game(3, "10:3:1"));

                Game[] test = mDao.getAnyGame();
            }
            return null;
        }
    }
}
