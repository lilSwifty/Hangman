package com.example.manisedighi.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.manisedighi.hangman.R.id.secretWord;
import static com.example.manisedighi.hangman.R.id.usedLetters;

/**
 * The game activity, this is where the game is played.
 */
public class GameActivity extends AppCompatActivity {

    private TextView used;
    private EditText guess;
    private HangmanGame hangman;
    private TextView answer;
    private TextView totalTries;
    private ImageView img;
    private String word;
    private int tries = 10;
    private int picIdx = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        used = (TextView) findViewById(usedLetters);

        used = (TextView) findViewById(R.id.usedLetters);

        hangman = new HangmanGame();

        answer = (TextView) findViewById(secretWord);

        word = hangman.randomWord();

        answer.setText(hangman.hideWord());

        totalTries = (TextView) findViewById(R.id.triesLeft);

        totalTries.setText("Tries left: " + tries);

        img = (ImageView) findViewById(R.id.imageView);



    }


    /**
     * changes the hangman-image depending on how many tries the player has left
     * @return img of hangman
     */
    public int getImg() {

        switch(picIdx){
            case 0:
                img.setImageResource(R.drawable.hang9);
                break;
            case 1:
                img.setImageResource(R.drawable.hang8);
                break;
            case 2:
                img.setImageResource(R.drawable.hang7);
                break;
            case 3:
                img.setImageResource(R.drawable.hang6);
                break;
            case 4:
                img.setImageResource(R.drawable.hang5);
                break;
            case 5:
                img.setImageResource(R.drawable.hang4);
                break;
            case 6:
                img.setImageResource(R.drawable.hang3);
                break;
            case 7:
                img.setImageResource(R.drawable.hang2);
                break;
            case 8:
                img.setImageResource(R.drawable.hang1);
                break;
            case 9:
                img.setImageResource(R.drawable.hang0);
                break;

        }
        return picIdx;
    }


    /**
     * This method starts when the button is clicked. It will check a given input from the textview.
     * @param view
     */
    public void takeGuess(View view){
        guess = (EditText) findViewById(R.id.guessTextView);
        String letter = guess.getText().toString();
        used = (TextView) findViewById(R.id.usedLetters);
        used.setText(hangman.getGuessed());



            if (letter.matches(".*\\d.*")) {
                Toast.makeText(getApplicationContext(), "Numbers are not allowed, please type a letter.",
                        Toast.LENGTH_SHORT).show();
            }
            else if(letter.length() < 1 || (letter.length() > 1)){
                Toast.makeText(getApplicationContext(), "You need to type a single letter",
                        Toast.LENGTH_SHORT).show();

            }else if(hangman.checkLetter(letter.charAt(0))){
                Toast.makeText(getApplicationContext(),"You've allready guessed this letter.",
                        Toast.LENGTH_SHORT).show();
            }
            else {

                if (letter.length() == 1) {

                    if (!hangman.checkLetter(letter.charAt(0))){
                        if (hangman.hitLetter(letter)) {
                            hangman.makeGuess(letter);
                            if (!hangman.hasWon()){
                                Intent intent = new Intent(this, ResultActivity.class);
                                intent.putExtra("TRIES", totalTries.getText().toString());
                                intent.putExtra("WORD", answer.getText().toString());
                                intent.putExtra("WON_OR_LOST", "won");


                                startActivity(intent);
                            }

                        }
                        else {
                            picIdx++;
                            tries--;
                            hangman.addLettersToList(letter.charAt(0));
                            getImg();

                            if (tries == 0 ) {

                                Intent intent = new Intent(this, ResultActivity.class);
                                intent.putExtra("TRIES", totalTries.getText().toString());
                                answer.setText(word);
                                intent.putExtra("WORD", answer.getText().toString());
                                intent.putExtra("WON_OR_LOST", "lost");


                                startActivity(intent);
                            }

                        }

                    }


                }
            }


        guess.setText("");
        used.setText(hangman.getGuessed());
        answer.setText(hangman.getGuess());
        totalTries.setText("Tries left: " + tries);

    }

    /**
     * Method for actionbar, contains two buttons, one for accessing the about page, one for quitting the game and returning to main activity
     * @param item
     * @return chosen activity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.about_logo):
                onAboutClicked(findViewById(R.id.about_logo));
                return true;
            case (R.id.return_logo):
                backToMain(findViewById(R.id.return_logo));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * method for clicking the about button in the actionbar
     * @param view
     */
    public void onAboutClicked(View view){
        Intent intent = new Intent(this, AboutActivity.class);

        startActivity(intent);
    }

    /**
     * method for clicking the quit logo in the actionbar
     * @param view
     */
    public void backToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This is needen for the actionbar to be visibile properly
     * @param menu
     * @return onCreateOptionsMenu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
