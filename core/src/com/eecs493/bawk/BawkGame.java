package com.eecs493.bawk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BawkGame extends Game {
    Welcome welcome;
//    Play myGame;
//    GameOver gameOver;

    private final int width = 480;
    private final int height = 800;

    @Override
    public void create()
    {
//        prefs = new Gdx.getPreferences("My Preferences");
//        myGame = new Play(this);
        welcome = new Welcome(this);
//        gameOver = new GameOver(this);
        setScreen(welcome);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
