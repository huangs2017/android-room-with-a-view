package com.example.android.roomwordssample;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> wordList;

    WordRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        wordList = wordDao.getWordList();
    }

    LiveData<List<Word>> getWordList() {
        return wordList;
    }

    void insert(Word word) {
        new insertAsyncTask(wordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao dao;
        insertAsyncTask(WordDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(final Word... params) {
            dao.insert(params[0]);
            return null;
        }
    }

}
