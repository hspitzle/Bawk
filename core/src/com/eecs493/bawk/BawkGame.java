package com.eecs493.bawk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BawkGame extends Game
{

    Welcome welcome;
    HowTo howTo;
    Options options;
    Play play;
    GameOver gameOver;

    private final int width = 480;
    private final int height = 800;

    @Override
    public void create()
    {
        welcome = new Welcome(this);
        howTo = new HowTo(this);
        options = new Options(this);
        play = new Play(this);
        gameOver = new GameOver(this);
        setScreen(welcome);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
