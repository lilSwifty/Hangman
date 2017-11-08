package com.example.manisedighi.hangman;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by manisedighi on 24/10/2017.
 */

/**
 * Hangman class, code for playing the game
 */
public class HangmanGame {

    private String[] listWords;
    private String realWord;
    private char[] guess;
    private ArrayList<Character> guessed = new ArrayList<>();
    private int idx;

    public String getGuessed(){
        String joined = TextUtils.join(" ", guessed);
        return  joined;
    }

    /**
     * Default constructor
     */
    public HangmanGame(){

    }



    /**
     * Generates a random word from the list
     */
    public String randomWord(){

        listWords = new String[10];

        listWords[0] = "apple";
        listWords[1] = "macintosh";
        listWords[2] = "orange";
        listWords[3] = "priceless";
        listWords[4] = "elephant";
        listWords[5] = "alliance";
        listWords[6] = "parliament";
        listWords[7] = "garage";
        listWords[8] = "savage";
        listWords[9] = "plague";

        Random random = new Random();
        idx = random.nextInt(10);

        realWord = listWords[idx];

        return realWord;

    }

    /**
     * Hides the random word for the player to guess
     * @return string containingt characters of '*'
     */
    public String hideWord(){

        guess = new char[realWord.length()];
        for (int p = 0; p < realWord.length(); p++)
            guess[p] = '*';

        return String.valueOf(guess);

    }


    /**
     * checks if hidden word contains '*' and returns true if does. When false is returned, the game is won
     * @return true / false
     */
    public boolean hasWon(){
        boolean winner = false;

        for (int i = 0; i < realWord.length(); i++) {
            if (guess[i] == '*')
                winner = true;

        }
        return winner;

    }


    /**
     * Checks if the user input is already used
     * @param letter user input
     * @return true / false
     */
    public boolean checkLetter(char letter){

        boolean containing = false;

        for (char c: guessed) {
            if (c == letter){
                containing = true;
            }
        }

        return containing;

    }


    /**
     * checks if user hiddenword contains user input
     * @param letter user input
     * @return true / false
     */
    public boolean hitLetter(String letter){
        boolean hit = false;



        for (int i = 0; i < realWord.length(); i++) {
            if (letter.charAt(0) == realWord.charAt(i)) {
                hit = true;
            }
        }
        return hit;
    }

    /**
     *
     * @return hidden string with correct inputs showing
     */
    public String getGuess() {

        return String.valueOf(guess);
    }

    /**
     * if hitLetter returns true, this method will change the hidden word to show the correct guessed letters
     * @param letter user input
     * @return new hidden string, showing the correctly guessed letters
     */
    public String makeGuess(String letter){

        char playerGuess = letter.charAt(0);
        for (int i = 0; i < realWord.length() ; i++) {
            if (playerGuess == realWord.charAt(i)) {
                guess[i] = playerGuess;
            }
        }

        return String.valueOf(guess);
    }

    /**
     * Takes a userinput and puts it in a list with used letters
     * @param playerGuess
     */
    public void addLettersToList(char playerGuess){
        guessed.add(playerGuess);
    }






}

