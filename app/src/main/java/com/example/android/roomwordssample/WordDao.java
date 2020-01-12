package com.example.android.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Word word);

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getWordList();
}
