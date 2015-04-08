package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

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

    public Array<Laser> lasers;

    int rotation;

    public class Pt{
        public int x, y;
        public Pt(int x_, int y_){
            this.x = x_;
            this.y = y_;
        }
    }

    public Bawk()
    {
        super(new Texture("chicken_yellow.png")); //default color

        textureYellow = new Texture("chicken_yellow.png");
        textureBlue = new Texture("chicken_blue.png");
        texturePurple = new Texture("chicken_purple.png");
        textureGreen = new Texture("chicken_green.png");

        color = Color.YELLOW;
        lasers = new Array<Laser>();

        setPosition(188, 436);
        setSize(textureYellow.getWidth(), textureYellow.getHeight());
        System.out.println(getWidth());
        System.out.println(getX() + " " + getY());

    }

    public void shoot()
    {
        int x = 100, y = 100;
        //find position based on Bawk;

        Pt point = getLaserPosition();

        Laser newLaser = new Laser(color, rotation, point.x, point.y);
        lasers.add(newLaser);
    }

    private Pt getLaserPosition()
    {
        int x = getCenterX();
        int y = getCenterY();
        int offset = 20;

//        switch(rotation){
//            case 0: //down
//                y += offset;
//                break;
//            case 90: //right
//                x -= offset;
//                break;
//            case 180: //up
//                y -= offset;
//                break;
//            case 270: //left
//                x += offset;
//                break;
//            default:
//                break;
//        }

        return new Pt(x, y);
    }

    private int getCenterX()
    {
        return (int)(getX() + (getWidth()/2));
    }

    private int getCenterY()
    {
        return (int)(getY() + (getHeight()/2));
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
    public Color getColor(){
        return color;
    }

    @Override
    public void setColor(Color color_)
    {
        color = color_;

        if(color == Color.BLUE)
            setTexture(textureBlue);
        else if (color == Color.PURPLE)
            setTexture(texturePurple);
        else if(color == Color.GREEN)
            setTexture(textureGreen);
        else
            setTexture(textureYellow);
    }

    public void moveUp(int movement)
    {
        setY(getY() + movement);
        upRotate();
    }

    public void moveDown(int movement)
    {
        setY(getY() - movement);
        downRotate();
    }

    public void moveLeft(int movement)
    {
        setX(getX() - movement);
        leftRotate();
    }

    public void moveRight(int movement)
    {
        setX(getX() + movement);
        rightRotate();
    }


    public void upRotate()
    {
        if (rotation != 180) //initially wasn't facing down
        {
            if (rotation == 0) //initially facing up
                rotate(180);
            else if (rotation == 90) //initially facing right
                rotate(90);
            else //rotation facing left
                rotate(-90);
        }
        rotation = 180;
    }

    public void rightRotate()
    {
        if (rotation != 90) //initially wasn't facing right
        {
            if (rotation == 0) //initially facing up
                rotate(90);
            else if (rotation == 180) //initially facing down
                rotate(-90);
            else //rotation facing left
                rotate(180);
        }
        rotation = 90;
    }

    public void downRotate()
    {
        if (rotation != 0) //initially wasn't facing up
        {
            if (rotation == 90) //initially facing right
                rotate(-90);
            else if (rotation == 180) //initially facing down
                rotate(180);
            else //rotation facing left
                rotate(90);
        }
        rotation = 0;
    }

    public void leftRotate()
    {
        if (rotation != 270) //initially wasn't facing left
        {
            if (rotation == 0) //initially facing up
                rotate(-90);
            else if (rotation == 90) //initially facing right
                rotate(180);
            else //rotation facing down
                rotate(90);
        }
        rotation = 270;
    }

}
