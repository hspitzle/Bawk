package com.eecs493.bawk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Kaitlyn on 4/2/15.
 */
public class HighScore {
    private Preferences prefs;
    private int highscore;

    public HighScore(){
        prefs = Gdx.app.getPreferences("My Preferences");
    }

    public int getHighScore(){
        highscore = prefs.getInteger("highscore", 0);
        return highscore;
    }

    public void setHighScore(int finalScore){
        highscore = prefs.getInteger("highscore", 0);
        if(highscore == 0 || highscore < finalScore){
            prefs.putInteger("highscore", finalScore);
            prefs.flush();
        }
    }

}
