package com.example.android.roomwordssample;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

@Database(entities = {Word.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    private static volatile MyRoomDatabase instance;

    static MyRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, "word_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    /**
     * we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created
     * for the 1st time, override onCreate()
     */
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(instance).execute();
        }
    };

    // Populate the database in the background.
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao dao;

        PopulateDbAsync(MyRoomDatabase db) {
            dao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            dao.deleteAll();
            dao.insert(new Word("Hello"));
            dao.insert(new Word("World"));
            return null;
        }
    }

}
