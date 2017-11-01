package com.example.manisedighi.hangman;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by manisedighi on 24/10/2017.
 */

public class HangmanGame {

    private String[] listWords;
    private String mysteryWord;
    private String realWord;
    private char[] guess;
    private ArrayList<Character> guessed = new ArrayList<>();
    private int idx;

    public String getGuessed(){
        String joined = TextUtils.join(" ", guessed);
        return  joined;
    }

    public HangmanGame(){

    }




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

    public String hideWord(String word){

        guess = new char[realWord.length()];
        for (int p = 0; p < realWord.length(); p++)
            guess[p] = '*';

        return String.valueOf(guess);

    }



    public boolean hasWon(){
        boolean winner = false;

        for (int i = 0; i < realWord.length(); i++) {
            if (guess[i] == '*')
                winner = true;
;
        }
        return winner;

    }



    public boolean checkLetter(char letter){

        boolean containing = false;

        for (char c: guessed) {
            if (c == letter){
                containing = true;
            }
        }

        return containing;

    }


    public boolean hitLetter(String letter){
        boolean hit = false;



        for (int i = 0; i < realWord.length(); i++) {
            if (letter.charAt(0) == realWord.charAt(i)) {
                hit = true;
            }
        }
        return hit;
    }


    public String getGuess() {

        return String.valueOf(guess);
    }

    public String makeGuess(String letter){

        char playerGuess = letter.charAt(0);
        for (int i = 0; i < realWord.length() ; i++) {
            if (playerGuess == realWord.charAt(i)) {
                guess[i] = playerGuess;
            }
        }

        return String.valueOf(guess);
    }


    public void addLettersToList(char playerGuess){
        guessed.add(playerGuess);
    }


    public void onSaveInstanceState(Bundle outState){

    }



}

