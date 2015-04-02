package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Hayden on 4/2/2015.
 */
public class Laser extends Sprite {

    private int direction;

    public Laser(){ }

    public enum Direction { DOWN, RIGHT, UP, LEFT};

    public Laser(Color color, int rotation, int x, int y){
        super(new Texture("laser_blue.png"));

        if (color == Color.PURPLE)
            setTexture(new Texture("laser_purple.png"));
        else if(color == Color.GREEN)
            setTexture(new Texture("laser_green.png"));
        else if(color == Color.YELLOW)
            setTexture(new Texture("laser_yellow.png"));

        direction = rotation/90;

        setPosition(x, y);
    }

    public void update(){

    }
}
