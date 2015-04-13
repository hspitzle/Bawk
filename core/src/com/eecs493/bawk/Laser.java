package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Hayden on 4/2/2015.
 */
public class Laser extends Sprite {

    private int direction;
    private int speed = 20;
    private Color color;
    private boolean reversed;
    int comboSize;

    public Laser(){ comboSize = 0; }

    public void negateSpeed(){
        speed *= -1;
        reversed = !reversed;
    }

    public boolean isReversed(){
        return reversed;
    }

    public enum Direction {
        DOWN(0), RIGHT(1), UP(2), LEFT(3);
        private int value;

        Direction(int value) {
            this.value = value;
        }
        public int getValue()
        {
            return this.value;
        }
        public void setValue(int value)
        {
            this.value = value;
        }
    };

    public Laser(Color color_, int rotation, int x, int y){
        super(new Texture("laser_blue.png"));
        color = color_;
        reversed = false;

        if (color_ == Color.PURPLE)
            setTexture(new Texture("laser_purple.png"));
        else if(color_ == Color.GREEN)
            setTexture(new Texture("laser_green.png"));
        else if(color_ == Color.YELLOW)
            setTexture(new Texture("laser_yellow.png"));

        direction = rotation/90;

        if (direction % 2 == 0) //up or down configuration
        {
            rotate90(true); //rotate the laser so it's facing up/down
            setSize(getTexture().getHeight(), getTexture().getWidth());
        }
        else
            setSize(getTexture().getWidth(), getTexture().getHeight());

        setPosition(x, y);

    }

    @Override
    public Color getColor(){
        return color;
    }

    @Override
    public void setColor(Color color_){
        color = color_;

        if(color == Color.BLUE) setTexture(new Texture("laser_blue.png"));
        else {
            if (color == Color.PURPLE) setTexture(new Texture("laser_purple.png"));
            else {
                if(color == Color.GREEN)setTexture(new Texture("laser_green.png"));
                else {
                    setTexture(new Texture("laser_yellow.png"));
                }
            }
        }

        if (direction % 2 == 0) //up or down configuration
        {
            rotate90(true); //rotate the laser so it's facing up/down
            setSize(getTexture().getHeight(), getTexture().getWidth());
        }
        else
            setSize(getTexture().getWidth(), getTexture().getHeight());

    }

    public void update(){
        float curX = getX();
        float curY = getY();

        if (direction == Direction.RIGHT.getValue()) //laser should move to the right
        {
            setX(curX + speed);
        }
        else if (direction == Direction.DOWN.getValue()) //laser should move down
        {
            setY(curY - speed);
        }
        else if (direction == Direction.LEFT.getValue()) //laser should move to the left
        {
            setX(curX - speed);
        }
        else if (direction == Direction.UP.getValue()) //laser should move up
        {
            setY(curY + speed);
        }
    }
}
