package com.mcdenny.atesodictionary;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {
    WordAdapter wordAdapter;
    public ArrayList<Word> words;
    public ListView listView;
    MediaPlayer mMediaplayer;
    public EditText searchEditText;
    public ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        words = new ArrayList<Word>();
        //numbers
        words.add(new Word("One", "idopet", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Two", "iyarei", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Three", "iwuni", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Four", "iwongon", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Five", "ikany", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Six", "iknayapei", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Seven", "ikanyarei", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Eight", "ikanyauni", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Nine", "ikanyawongon", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Ten", "itomon", "I am a boy", "Arai eong esapat", R.raw.two));

        //family
        words.add(new Word("Boy", "esapat", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Girl", "apese", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Father", "papa", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Mother", "toto", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Uncle", "mamai", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Auntie", "ija", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Son", "okooka", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Daughter", "akooka", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Sister", "inac", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Brother", "onac", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Grand mother", "tata", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Grand father", "papa", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("In-law", "amuran", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Grand child", "itatait", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Grand grand child", "ijejait", "I am a boy", "Arai eong esapat", R.raw.two));

        //colors


        //days the week

        //months of the year

        //common words
        words.add(new Word("Love", "amina", "I love you", "Amina eong jo", R.raw.two));
        words.add(new Word("Table", "emeesa", "The food is on the table", "Ejaasi inyamat omeesa", R.raw.two));
        words.add(new Word("Boat", "ataker", "I am a boy", "Arai eong esapat", R.raw.two));
        words.add(new Word("Food", "inyamat", "Denis is eating food", "inyami Denis inyamat", R.raw.two));
        words.add(new Word("Cup", "ekopo", "The cup is on the table", "Ejai ekopo omeesa", R.raw.two));


        //directions



        listView = (ListView) findViewById(R.id.wordList);

        wordAdapter = new WordAdapter(this, words);
        //this method is called inorder to display items in a list
        listView.setAdapter(wordAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //gets the word object at a given position use clicked on
                Word word = words.get(position);
                //creates and sets up a media player for the audio resource associated with the current word
                mMediaplayer = MediaPlayer.create(MainActivity.this, word.getResourceAudio());
                mMediaplayer.start();
            }
        });

        //This sorts the list in A-Z order
        Collections.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word firstWord, Word secondWord) {
                return firstWord.getAtesoTranslation().compareTo(secondWord.getAtesoTranslation());
            }
        });

        searchEditText = (EditText) findViewById(R.id.searchListView);
        //adding the textwatcher to the edit text
        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                wordAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "SUBJECT");
                intent.putExtra(Intent.EXTRA_TEXT,"Download Ateso Dictionary on Play Store");
                startActivity(Intent.createChooser(intent, "Choose sharing method"));
                return true;
            case R.id.feedback:
                Intent feedbackIntent = new Intent(Intent.ACTION_SEND);
                feedbackIntent.setData(Uri.parse("email"));
                String[] myemail = {"olukadeno@gmail.com"};
                feedbackIntent.putExtra(Intent.EXTRA_EMAIL, myemail);
                feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback on the Ateso-English dictionary");
                feedbackIntent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(feedbackIntent, "Launch Email");
                startActivity(chooser);
                return true;
            case R.id.setting:
                Intent settingIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingIntent);
                return true;
            case R.id.about:
                Intent aboutIntent = new Intent(this, AboutActivty.class);
                startActivity(aboutIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
