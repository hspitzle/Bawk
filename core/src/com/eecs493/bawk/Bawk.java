package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Color;
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
    private Texture textureYellow;
    private Texture textureBlue;
    private Texture texturePurple;
    private Texture textureGreen;

    private Color color;

    float rotation;

    public Bawk()
    {
        super(new Texture("chicken_yellow.png")); //default color

        textureYellow = new Texture("chicken_yellow.png");
        textureBlue = new Texture("chicken_blue.png");
        texturePurple = new Texture("chicken_purple.png");
        textureGreen = new Texture("chicken_green.png");

        setPosition(188, 436);
        setSize(textureYellow.getWidth(), textureYellow.getHeight());
        System.out.println(getWidth());
        System.out.println(getX() + " " + getY());
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

    @Override
    public void setColor(Color color_)
    {
        color = color_;

        if(color == Color.BLUE) setTexture(textureBlue);
        else {
            if (color == Color.PURPLE) setTexture(texturePurple);
            else {
                if(color == Color.GREEN)setTexture(textureGreen);
                else {
                    setTexture(textureYellow);
                }
            }
        }
    }

    public void upRotate()
    {
        if (rotation != 180) //initially wasn't facing down
        {
            if (rotation == 0) //initially facing up
            {
                rotate(180);
            }
            else if (rotation == 90) //initially facing right
            {
                rotate(90);
            }
            else //rotation facing left
            {
                rotate(-90);
            }
        }
        rotation = 180;
    }

    public void rightRotate()
    {
        if (rotation != 90) //initially wasn't facing right
        {
            if (rotation == 0) //initially facing up
            {
                rotate(90);
            }
            else if (rotation == 180) //initially facing down
            {
                rotate(-90);
            }
            else //rotation facing left
            {
                rotate(180);
            }
        }
        rotation = 90;
    }

    public void downRotate()
    {
        if (rotation != 0) //initially wasn't facing up
        {
            if (rotation == 90) //initially facing right
            {
                rotate(-90);
            }
            else if (rotation == 180) //initially facing down
            {
                rotate(180);
            }
            else //rotation facing left
            {
                rotate(90);
            }
        }
        rotation = 0;
    }

    public void leftRotate()
    {
        if (rotation != 180) //initially wasn't facing left
        {
            if (rotation == 0) //initially facing up
            {
                rotate(-90);
            }
            else if (rotation == 90) //initially facing right
            {
                rotate(180);
            }
            else //rotation facing down
            {
                rotate(90);
            }
        }
        rotation = 270;
    }

    public void shootLaser()
    {

    }
}
