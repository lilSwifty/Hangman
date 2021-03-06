package com.example.manisedighi.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * opens the acitivity window when clicked
     * @param view
     */
    public void onAboutClicked(View view){
        Intent intent = new Intent(this, AboutActivity.class);

        startActivity(intent);
    }

    /**
     * Actionbar for navigating
     * @param item
     * @return chosen action
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.about_logo):
                onAboutClicked(findViewById(R.id.about_logo));
                return true;
            case (R.id.play_logo):
                onPlayClicked(findViewById(R.id.play_logo));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Starts a new game in the GameActivity window
     * @param view
     */
    public void onPlayClicked(View view){
        Intent intent = new Intent(this, GameActivity.class);

        startActivity(intent);
    }

    /**
     * Method for actionbar
     * @param menu
     * @return actionbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
