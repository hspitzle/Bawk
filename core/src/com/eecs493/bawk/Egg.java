package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.Random;

/**
 * Created by Kaitlyn on 3/20/2015.
 */
public class Egg extends Sprite
{
    private Texture texture;
    private boolean vertical;//true is vertical, false is horizontal
    private Color color;
    private int street;

    float rotation;

    public Egg(boolean vertical_, Color color_, int street_){
        super(new Texture("egg_blue.png"));
        street = street_;
        vertical = vertical_;

       color = color_;
        setPosition(100,100);
        setSize(getTexture().getWidth(), getTexture().getHeight());
        if(color == Color.BLUE) setTexture(new Texture("egg_blue.png"));
        else {
            if (color == Color.PURPLE) setTexture(new Texture("egg_purple.png"));
            else {
                if(color == Color.GREEN)setTexture(new Texture("egg_green.png"));
                else {
                    setTexture(new Texture("egg_yellow.png"));
                }
            }
        }

        //if egg should be horizontal rotate it
        if(!vertical){
            rotate90(true);
            setSize(getTexture().getHeight(), getTexture().getWidth());
        }

    }

    public Egg(boolean vertical_, int street_)
    {
        super(new Texture("egg_blue.png"));
        street = street_;
        vertical = vertical_;

        //generate a random int between 0 and 3
        Random randomGenerator = new Random();
        int colorInt = randomGenerator.nextInt(4);
    setPosition(100,100);
     setSize(getTexture().getWidth(), getTexture().getHeight());
        //based on random int set color
         switch(colorInt){
             case 0:
                 color = Color.BLUE;
                 setTexture(new Texture("egg_blue.png"));
                 break;
             case 1:
                 color = Color.GREEN;
                 setTexture(new Texture("egg_green.png"));
                 break;
             case 2:
                 color = Color.PURPLE;
                 setTexture(new Texture("egg_purple.png"));
                 break;
             default:
                 color = Color.YELLOW;
                 setTexture(new Texture("egg_yellow.png"));
                 break;

         }

        //if egg should be horizontal rotate it
        if(!vertical){
            rotate90(true);
            setSize(getTexture().getHeight(), getTexture().getWidth());
        }

    }

    private boolean isVertical(){
        return vertical;
    }

    public void shiftToNest(){
        int quad = street/4;
        switch(quad){
            case 0:
                setPosition(getX(), getY()-35);
                break;
            case 1:
                setPosition(getX()-35, getY());
                break;
            case 2:
                setPosition(getX(), getY()+35);
                break;
            case 3:
                setPosition(getX()+35, getY());
                break;
            default:
                break;
        }
    }

    @Override
    public Color getColor(){
        return color;
    }

    @Override
    public void setColor(Color color_){
        color = color_;

        if(color == Color.BLUE) setTexture(new Texture("egg_blue.png"));
        else {
            if (color == Color.PURPLE) setTexture(new Texture("egg_purple.png"));
            else {
                if(color == Color.GREEN)setTexture(new Texture("egg_green.png"));
                else {
                  setTexture(new Texture("egg_yellow.png"));
                }
            }
        }


    }


}
