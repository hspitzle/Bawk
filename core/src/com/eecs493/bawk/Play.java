package com.eecs493.bawk;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

import sun.util.resources.cldr.uk.TimeZoneNames_uk;

//import java.awt.Rectangle;

/**
 * Created by Hayden on 3/20/2015.
 */
public class Play implements Screen {

    private BawkGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture backgroundImage;
    private Texture gameplayImage;
    private Rectangle background;
    private Rectangle gameplay;
    private Bawk bawk;

    private ArrayList<ArrayList<Egg>> eggs;
    private int score;
    private int eggTimer;
    private long lastLaser;
    private long lastEggTime;

    public Play(BawkGame game_){
        game = game_;
    }

    @Override
    public void show()
    {
        System.out.println("Show");
        backgroundImage = new Texture("gamebackground.png");
        gameplayImage = new Texture("gameplayarea.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());
        batch = new SpriteBatch();

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());
//        System.out.println(grid.x + " " + grid.y);
        gameplay = new Rectangle(game.getNestX(), game.getNestY(),
                                 gameplayImage.getWidth(),
                                 gameplayImage.getWidth());

        bawk = new Bawk();

        eggs = new ArrayList<ArrayList<Egg>>();
        for(int i=0; i<16; ++i){
            eggs.add(new ArrayList<Egg>());
        }



        score = 0;
        eggTimer = 5000;
        lastLaser = 0;
        lastEggTime = TimeUtils.millis();

        spawnEgg();
    }

    private void drawBatch()
    {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.draw(backgroundImage, background.x, background.y);
        batch.draw(gameplayImage, gameplay.x, gameplay.y);

        bawk.draw(batch);

        for(ArrayList<Egg> arr : eggs)
            for(Egg egg : arr)
                egg.draw(batch);

        batch.end();
    }

    @Override
    public void render(float delta)
    {
        drawBatch();

        //updating & input detection

        updateBawkLocation();

        if (Gdx.input.isTouched())
        {
            Gdx.app.log("my app", "screen touched");
            bawk.rotate90(true);
        }

        long myShotTimer = 180;
        if(Gdx.input.isTouched() && TimeUtils.millis() - lastLaser > myShotTimer) {
//            bawk.shoot();
            lastLaser = TimeUtils.millis();
        }

        // check if we need to create a new commet
        if(TimeUtils.millis() - lastEggTime > eggTimer) {
            spawnEgg();
            lastEggTime = TimeUtils.millis();
        }


    }

    private void spawnEgg()
    {
        Random rand = new Random();
        int num = rand.nextInt(16);

        if(eggs.get(num).size() == 4)
            game.setScreen(game.gameOver);

        boolean vertical = true;
        int x = 100, y = 100;

        if(num <= 3){
            vertical = false;
            y = game.getLocationY(0);
            x = game.getLocationX(4 + num);
        }
        else if(num <= 7){
            y = game.getLocationY(4 + num%4);
            x = game.getLocationX(11);
        }
        else if(num <= 11){
            vertical = false;
            y = game.getLocationY(11);
            x = game.getLocationX(7 - num%4);
        }
        else{
            y = game.getLocationY(7 - num%4);
            x = game.getLocationX(0);
        }

        for(Egg egg : eggs.get(num))
            egg.shiftToNest();

        Egg newEgg = new Egg(vertical, num);
        newEgg.setPosition(x, y);
        eggs.get(num).add(newEgg);

    }

    private void updateBawkLocation()
    {
        // move bawk around
        float tilt = Gdx.input.getAccelerometerX();
        float boundary = 6.5f;
        int movement = 48;
        if(tilt > boundary)
            bawk.setX(bawk.getX() - movement);
        else if(tilt < -1f*boundary)
            bawk.setX(bawk.getX() + movement);

        tilt = Gdx.input.getAccelerometerY();
        if(tilt > boundary)
            bawk.setY(bawk.getY() - movement);
        else if(tilt < -1f*boundary)
            bawk.setY(bawk.getY() + movement);

//        keep within x bounds
        if(bawk.getX() > game.getLocationX(7))
            bawk.setX(game.getLocationX(7));
        else if(bawk.getX() < game.getLocationX(4))
            bawk.setX(game.getLocationX(4));

        if(bawk.getY() < game.getLocationY(7))
            bawk.setY(game.getLocationY(7));
        else if(bawk.getY() > game.getLocationY(4))
            bawk.setY(game.getLocationY(4));
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
    }

    @Override
    public void dispose()
    {
        // backgroundImage.dispose();
        // batch.dispose();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

}
