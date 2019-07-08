package com.raghavapp.mynotes;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText noteEditText;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleEditText = findViewById(R.id.titleEditText);
        noteEditText = findViewById(R.id.noteEditText);

        noteId = getIntent().getIntExtra("noteId", -1);
        if (noteId != -1) {
            titleEditText.setText(MainActivity.titles.get(noteId));
            noteEditText.setText(MainActivity.notes.get(noteId));
        } else {
            MainActivity.titles.add("");
            MainActivity.notes.add("");
            noteId = MainActivity.titles.size() - 1;
        }

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.titles.set(noteId, String.valueOf(s));
                if (MainActivity.titles.get(noteId).equals("")) {
                    MainActivity.titles.set(noteId, String.valueOf(s));
                }
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.raghavapp.mynotes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.titles);
                sharedPreferences.edit().putStringSet("titles", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteId, String.valueOf(s));
                if (MainActivity.titles.get(noteId).equals("")) {
                    MainActivity.titles.set(noteId, String.valueOf(s));
                    MainActivity.arrayAdapter.notifyDataSetChanged();
                }

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.raghavapp.mynotes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
