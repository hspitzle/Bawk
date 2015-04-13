package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Hayden on 4/12/2015.
 */
public class Author extends Sprite{

    String name;
    long timeCreated;
    int x, y;

    Author(String name_, long time_, int x_, int y_, boolean flip){
        super(new Texture("dialogbox.png"));

        Texture signTexture = new Texture("dialogbox.png");
        setSize(signTexture.getWidth(), signTexture.getHeight());
        setPosition(x_ - 20, y_ - 35);
        setScale(1f, 0.9f);

        if(flip){
            setFlip(true, false);
        }

        name = name_;
        timeCreated = time_;
        x = x_;
        y = y_;
    }
}
