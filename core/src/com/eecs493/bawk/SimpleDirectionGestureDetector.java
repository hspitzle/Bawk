package com.eecs493.bawk;

import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by Hayden on 4/7/2015.
 */
public class SimpleDirectionGestureDetector extends GestureDetector {

    public interface DirectionListener {
        void onLeft();

        void onRight();

        void onUp();

        void onDown();

//        void onTap();
    }

    public SimpleDirectionGestureDetector(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureAdapter{
        DirectionListener directionListener;

        public DirectionGestureListener(DirectionListener directionListener){
            this.directionListener = directionListener;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if(Math.abs(velocityX)>Math.abs(velocityY)){
                if(velocityX>0){
                    directionListener.onRight();
                }else{
                    directionListener.onLeft();
                }
            }else{
                if(velocityY>0){
                    directionListener.onDown();
                }else{
                    directionListener.onUp();
                }
            }
            return super.fling(velocityX, velocityY, button);
        }

//        @Override
//        public boolean tap(float x, float y, int count, int button) {
//            directionListener.onTap();
//            return super.tap(x, y, count, button);
//        }
    }
}
