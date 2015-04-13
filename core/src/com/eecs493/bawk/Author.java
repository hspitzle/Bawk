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
    int textX, textY;

    Author(String name_, long time_, int x_, int y_, boolean flip){
        super(new Texture("dialogbox.png"));
        name = name_;
        timeCreated = time_;
        x = x_;
        y = y_;

        if(flip){
            setFlip(true, false);
            x -= 122;
            y += 22;
        }
        else{
            x += 15;
            y += 25;
        }

        Texture dialogTexture = new Texture("dialogbox.png");
        setSize(dialogTexture.getWidth(), dialogTexture.getHeight());
        setPosition(x, y);
        setScale(1f, 0.9f);

        x = (int)getX() + 15;
//        if(name.length() < 15)
            x += 15f*Math.floor((15 - name.length())/2f);
        y += 38;
    }
}
