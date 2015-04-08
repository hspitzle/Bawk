package com.eecs493.bawk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

    Music music;
    Sound click;

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

        click = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        music = Gdx.audio.newMusic(Gdx.files.internal("jauntygumption.mp3"));
        music.setLooping(true);
        music.play();

        welcome = new Welcome(this);
        howTo = new HowTo(this);
        options = new Options(this);
        play = new Play(this);
        gameOver = new GameOver(this);
        highScoreScreen = new HighScoreScreen(this);

        setScreen(welcome);
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
}
