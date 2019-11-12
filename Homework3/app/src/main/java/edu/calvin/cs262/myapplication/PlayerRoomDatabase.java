package edu.calvin.cs262.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Player.class}, version = 1, exportSchema = false)

public abstract class PlayerRoomDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();

    private static PlayerRoomDatabase INSTANCE;

    public static PlayerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlayerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlayerRoomDatabase.class, "player_database")
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
    

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlayerDao mDao;
        Player player;
        String[] players = {};

        PopulateDbAsync(PlayerRoomDatabase db) {
            mDao = db.playerDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            if (mDao.getAnyPlayer().length < 1) {
                mDao.insert(new Player(5, "Seriy", "st@gmail.com"));
                mDao.insert(new Player(6, "Sebrina", "Halo@gmail.com"));
                mDao.insert(new Player(7, "Sam", "Hii@gmail.com"));

            }
            return null;
        }
    }
}
