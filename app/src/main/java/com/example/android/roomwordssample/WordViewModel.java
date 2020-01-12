package com.example.android.roomwordssample;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private LiveData<List<Word>> wordList;

    public WordViewModel(Application application) {
        super(application);
        repository = new WordRepository(application);
        wordList = repository.getWordList();
    }

    LiveData<List<Word>> getWordList() {
        return wordList;
    }

    void insert(Word word) {
        repository.insert(word);
    }
}
