package edu.calvin.cs262.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Player.class, Game.class,  GamePlayer.class}, version = 3, exportSchema = false)
public abstract class GamePlayerRoomDatabase extends RoomDatabase {
    public abstract GamePlayerDao gamePlayerDao();
    public abstract GameDao gameDao();
    public abstract PlayerDao playerDao();
    private static GamePlayerRoomDatabase INSTANCE;


    static GamePlayerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GamePlayerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GamePlayerRoomDatabase.class, "gamePlayer_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final GamePlayerDao mDao;
        private final PlayerDao pDao;
        private final GameDao gDao;
        GamePlayer gamePlayer;

        PopulateDbAsync(GamePlayerRoomDatabase db) {
            mDao = db.gamePlayerDao();
            pDao = db.playerDao();
            gDao = db.gameDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            if (pDao.getAnyPlayer().length < 1) {
                pDao.insert(new Player(5, "Sebrina", "halo@gmail.com"));
                pDao.insert(new Player(4, "Sebrina", "halo@gmail.com"));

            }

            if (gDao.getAnyGame().length < 1) {
                gDao.insert(new Game(1, "12:1:1"));
                gDao.insert(new Game(2, "1:1:1"));

            }

            if (mDao.getAnyGamePlayer().length < 1) {
                mDao.insert(new GamePlayer(5, 1));

            }

            return null;
        }
    }

}
