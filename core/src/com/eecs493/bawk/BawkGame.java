package com.eecs493.bawk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Point;

public class BawkGame extends Game
{

    Welcome welcome;
    HowTo howTo;
    Options options;
    Play play;
    GameOver gameOver;
    HighScoreScreen highScoreScreen;
    IntroScreen introScreen;

    Music music;
    Sound click;
    boolean pausedFlag;
    boolean soundEffectsOnFlag;
    boolean musicOnFlag;

    boolean easyFlag;
    boolean mediumFlag;
    boolean hardFlag;

    boolean tiltFlag;
    boolean swipeFlag;

    boolean firstTimeFlag;

    private Preferences prefs;

    private final int width = 480;
    private final int height = 800;

    boolean swipe;
    int difficulty;

    public enum Difficulty {
        EASY(4000), MEDIUM(3000), HARD(2000);
        private int value;

        Difficulty(int value) {
            this.value = value;
        }
        public int getValue()
        {
            return this.value;
        }
    };

    @Override
    public void create()
    {
        swipe = true;
        difficulty = Difficulty.EASY.getValue();

        prefs = Gdx.app.getPreferences("My Preferences");

        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        music = Gdx.audio.newMusic(Gdx.files.internal("jauntygumption.mp3"));
        music.setLooping(true);
        music.play();
        pausedFlag = false;
        musicOnFlag = getMusicPref();
        soundEffectsOnFlag = getSoundFXPref();

        easyFlag = getEasyPref(); //default difficulty is easy
        mediumFlag = getMediumPref();
        hardFlag = getHardPref();

        tiltFlag = !(getSwipePref()); //default playing mode is with accelerometer
        swipeFlag = getSwipePref();

        firstTimeFlag = getFirstTimePref();//default is true

        welcome = new Welcome(this);
        howTo = new HowTo(this);
        options = new Options(this);
        play = new Play(this);
        gameOver = new GameOver(this);
        highScoreScreen = new HighScoreScreen(this);
        introScreen = new IntroScreen(this);

        if(firstTimeFlag){
            setScreen(introScreen);
            firstTimeFlag = false;
            setFirstTimePref(firstTimeFlag);
        }
        else setScreen(welcome);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Point gridLocation()
    {
        Point p = new Point(0, 200);
        System.out.println(p.x + " " + p.y);
        return p;
    }

    public int getNestX()
    {
        return 0;
    }

    public int getNestY()
    {
        return 200;
    }

    public Point getLocation(int row, int col)
    {
        if(row > 11 || col > 11)
            return null;

        final int x[] = {0, 35, 70, 105, 140, 188, 236, 284, 332, 367, 402, 437};
        final int y[] = {437, 402, 367, 332, 284, 236, 188, 140, 105, 70, 35, 0};

        Point loc = new Point();
        loc.setLocation(x[col] + getNestX(), y[row] + getNestY());
        return loc;
    }

    public int getLocationX(int col)
    {
        if(col > 11)
            return -1;

        final int x[] = {0, 35, 70, 105, 140, 188, 236, 284, 332, 367, 402, 437};

        return x[col] + getNestX();
    }

    public int getLocationY(int row)
    {
        if(row > 11)
            return -1;

        final int y[] = {437, 402, 367, 332, 284, 236, 188, 140, 105, 70, 35, 0};

        return y[row] + getNestY();
    }


    public boolean getEasyPref() {
        return prefs.getBoolean("Easy", true);
    }

    public void setEasyPref(boolean diff){
        prefs.putBoolean("Easy", diff);
        prefs.flush();
    }

    public boolean getMediumPref() {
        return prefs.getBoolean("Medium", false);
    }

    public void setMediumPref(boolean diff){
        prefs.putBoolean("Medium", diff);
        prefs.flush();
    }

    public boolean getHardPref() {
        return prefs.getBoolean("Hard", false);
    }

    public void setHardPref(boolean diff){
        prefs.putBoolean("Hard", diff);
        prefs.flush();
    }


    //true if sound fx are on
    public boolean getSoundFXPref(){
        return prefs.getBoolean("SoundFX", true);
    }

    public void setSoundFXPref(boolean soundFX){
        prefs.putBoolean("SoundFX", soundFX);
        prefs.flush();
    }


    //true if music is on
    public boolean getMusicPref(){
        return prefs.getBoolean("Music", true);
    }

    public void setMusicPref(boolean music){
        prefs.putBoolean("Music", music);
        prefs.flush();
    }

    //true if first time playing
    public boolean getFirstTimePref(){
        return prefs.getBoolean("FirstTime", true);
    }

    public void setFirstTimePref(boolean firstTime){
        prefs.putBoolean("FirstTime", firstTime);
        prefs.flush();
    }


    //true if in swipe mode
    public boolean getSwipePref(){
        return prefs.getBoolean("SwipeMode", true);
    }

    public void setSwipePref(boolean swipe){
        prefs.putBoolean("SwipeMode", swipe);
        prefs.flush();
    }

}
