package com.mcdenny.atesodictionary;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    public Button listenButton;
    public TextView englishWd;
    public TextView atesoWd;
    public TextView atesoSentence;
    public TextView englishSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        listenButton = (Button) findViewById(R.id.listenBtn);
        atesoWd = (TextView) findViewById(R.id.ateso_detail);
        englishWd = (TextView) findViewById(R.id.english_detail);
        atesoSentence = (TextView) findViewById(R.id.ateso_sentence);
        englishSentence = (TextView) findViewById(R.id.english_sentence);

        Bundle bundle = getIntent().getExtras();
        String ateso_detail = bundle.getString("ateso_key");
        String english_detail = bundle.getString("english_key");
        String teso_sent = bundle.getString("ateso_sentence_key" );
        String eng_sent = bundle.getString("english_sentence_key");

        atesoWd.setText(ateso_detail);
        englishWd.setText(english_detail);
        atesoSentence.setText(teso_sent);
        englishSentence.setText(eng_sent);
        setTitle(ateso_detail);

        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        formatAteso();
        formatEnglish();
    }

    public void formatAteso(){
        atesoWd.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    public void formatEnglish(){
        englishWd.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
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
