package com.eecs493.bawk;

import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by Hayden on 4/7/2015.
 */
public class SimpleTapDetector extends GestureDetector {

    public interface DirectionListener {
        void onTap();
    }

    public SimpleTapDetector(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureAdapter{
        DirectionListener directionListener;

        public DirectionGestureListener(DirectionListener directionListener){
            this.directionListener = directionListener;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            directionListener.onTap();
            return super.tap(x, y, count, button);
        }
    }
}
