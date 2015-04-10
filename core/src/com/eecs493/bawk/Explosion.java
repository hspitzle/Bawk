package com.eecs493.bawk;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Hayden on 4/10/2015.
 */

public class Explosion {
    TextureRegion currframe;
    float statetime;
    float x, y;

    public Explosion(float x_, float y_) {
        x = x_;
        y = y_;
        statetime = 0f;
    }
}
