package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Point;

/**
 * Created by Hayden on 3/20/2015.
 */
public class Bawk extends Sprite
{
    private Texture texture;
    //private Texture textureBlue;
    //private Texture textureRed;
    //private Texture textureGreen;

    float rotation;

    public Bawk()
    {
        super(new Texture("chicken_yellow.png"));
        texture = new Texture("chicken_yellow.png");
        setTexture(texture);
        System.out.println("this is the width");

        setPosition(188, 436);
        setSize(texture.getWidth(), texture.getHeight());
        System.out.println(getWidth());
        System.out.println(getX() + " " + getY());
        //chicken = new SpriteBatch();
        //chicken.draw(textureUp, 48, 48);
    }

    @Override
    public float getRotation()
    {
        return rotation;
    }

    @Override
    public void rotate90(boolean clockwise)
    {
        if(clockwise)
            rotation += 90;
        else
            rotation -= 90;

        if(rotation >= 360) {
            rotation = 0;
        }
        super.rotate90(clockwise);
    }
}
