package com.example.android.roomwordssample;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import java.util.List;

public class WordListActivity extends AppCompatActivity {

    WordViewModel viewModel;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        viewModel.getWordList().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(final List<Word> words) {
                adapter.setWords(words);
            }
        });
    }

    int i;
    public void insertWord(View view) {
        i++;
        Word word = new Word("word" + i);
        viewModel.insert(word);
    }

}
