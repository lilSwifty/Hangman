package com.example.manisedighi.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.manisedighi.hangman.R.id.guessTextView;
import static com.example.manisedighi.hangman.R.id.secretWord;
import static com.example.manisedighi.hangman.R.id.usedLetters;

public class GameActivity extends AppCompatActivity {

    private TextView used;
    private EditText guess;
    private HangmanGame hangman;
    private TextView answer;
    private TextView guessView;
    private TextView totalTries;
    private int tries = 10;
    private int points;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        used = (TextView) findViewById(usedLetters);
        used = (TextView) findViewById(R.id.usedLetters);
        guessView = (TextView) findViewById(guessTextView);
        hangman = new HangmanGame();
        answer = (TextView) findViewById(secretWord);
        answer.setText(hangman.randomWord());
        totalTries = (TextView) findViewById(R.id.triesLeft);
        totalTries.setText("Tries left: " + tries);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        points = 0;


    }

    public int getImg(int index) {

        switch(index){
            case 0: return R.drawable.hang9;
            case 1: return R.drawable.hang8;
            case 2: return R.drawable.hang7;
            case 3: return R.drawable.hang6;
            case 4: return R.drawable.hang5;
            case 5: return R.drawable.hang4;
            case 6: return R.drawable.hang3;
            case 7: return R.drawable.hang2;
            case 8: return R.drawable.hang1;
            case 9: return R.drawable.hang0;

            default: return -1;
        }

    }




    public void takeGuess(View view){
        guess = (EditText) findViewById(R.id.guessTextView);
        String letter = guess.getText().toString();
        //hangman.addLettersToList(letter.charAt(0));
        used = (TextView) findViewById(R.id.usedLetters);
        used.setText(hangman.getGuessed());



            if (tries == 0) {

                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
            }
            else if(hangman.checkLetter(letter.charAt(0))){
                Toast.makeText(getApplicationContext(), "You've allready guessed this letter.",
                        Toast.LENGTH_SHORT).show();

            }else if (letter.length() < 1 || (letter.length() > 1)) {
                Toast.makeText(getApplicationContext(), "You need to type a single letter.",
                        Toast.LENGTH_SHORT).show();
            }
            else {



                if (letter.length() == 1) {

                    if (!hangman.checkLetter(letter.charAt(0))){
                        if (hangman.hitLetter(letter)) {
                            hangman.makeGuess(letter);
                            points++;
                        }
                        else
                            hangman.tries();
                            hangman.addLettersToList(letter.charAt(0));

                    }


                }
            }


        guess.setText("");
        used.setText(hangman.getGuessed());
        answer.setText(hangman.getGuess());



    }






}

/*
if (points == hangman.randomWord().length()){
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
 */

/*
 else {

                    tries--;
                    totalTries.setText("Tries left: " + tries);
                    if (tries == 0) {
                        Intent intent = new Intent(this, ResultActivity.class);
                        startActivity(intent);
                    }
                }
 */

/*
                    else {
                        for (int i = 0; i < hangman.randomWord().length(); i++) {

                            if (letter.charAt(0) == hangman.randomWord().charAt(i)) {
                                points++;
                            }
                         }
                    }
                    */