package com.example.manisedighi.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView result;
    private TextView info;
    String won = "CONGRATULATIONS";
    String lost = "GAME OVER";
    private HangmanGame hangman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = (TextView) findViewById(R.id.WinOrLose);
        info = (TextView) findViewById(R.id.resultTextview);
        hangman = new HangmanGame();



        Intent i = getIntent();
        String wonOrLost = i.getStringExtra("WON_OR_LOST");

        if (wonOrLost.equals("won")) {
            result.setText(won);
            Intent intent = getIntent();
            String points = intent.getStringExtra("TRIES");
            info.setText("You found the hidden word! \n" + points);
        }else{
            result.setText(lost);
            Intent intent = getIntent();
            String word = intent.getStringExtra("WORD");
            info.setText("The hidden word was: " + word);
        }
    }

    public void backToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
