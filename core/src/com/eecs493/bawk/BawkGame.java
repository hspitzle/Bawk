package com.eecs493.bawk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

    public int getLocationX()
    {

        return 0;
    }

    public int getLocationY()
    {

        return 0;
    }
}
