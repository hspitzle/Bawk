package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Hayden on 3/20/2015.
 */
public class Bawk extends Sprite
{
    private Texture textureUp;
    private Texture textureDown;
    private Texture textureLeft;
    private Texture textureRight;

    float rotation;

    public Bawk()
    {

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
