package com.eecs493.bawk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Kaitlyn on 4/2/15.
 */
public class HighScore {
    private Preferences prefs;
    private int highscore;
    private String highscoreString;
    private BawkGame game;

    public HighScore(BawkGame game_){
        game = game_;
        if(!game.puzzleFlag) highscoreString = "highscore"+game.difficulty;
        else highscoreString = "highscorePuzzle";
        prefs = Gdx.app.getPreferences("My Preferences");
    }

    public int getHighScore(){
        if(!game.puzzleFlag) highscoreString = "highscore"+game.difficulty;
        else highscoreString = "highscorePuzzle";
        highscore = prefs.getInteger(highscoreString, 0);
        return highscore;
    }

    public void setHighScore(int finalScore){
        if(!game.puzzleFlag) highscoreString = "highscore"+game.difficulty;
        else highscoreString = "highscorePuzzle";
        highscore = prefs.getInteger(highscoreString, 0);
        if(highscore == 0 || highscore < finalScore){
            prefs.putInteger(highscoreString, finalScore);
            prefs.flush();
        }
    }

}
