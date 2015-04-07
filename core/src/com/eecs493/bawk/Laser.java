package com.eecs493.bawk;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Hayden on 4/2/2015.
 */
public class Laser extends Sprite {

    private int direction;
    private final int speed = 10;

    public Laser(){ }

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

    public Laser(Color color, int rotation, int x, int y){
        super(new Texture("laser_blue.png"));

        if (color == Color.PURPLE)
            setTexture(new Texture("laser_purple.png"));
        else if(color == Color.GREEN)
            setTexture(new Texture("laser_green.png"));
        else if(color == Color.YELLOW)
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
