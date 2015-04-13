package com.eecs493.bawk;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Hayden on 4/12/2015.
 */
public class Author{

    String name;
    long timeCreated;
    int x, y;

    Author(String name_, long time_, int x_, int y_){
        name = name_;
        timeCreated = time_;
        x = x_;
        y = y_;
    }
}
