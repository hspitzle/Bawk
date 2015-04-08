package com.eecs493.bawk;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;



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

    private Array<Array<Egg>> eggs;
    private int score;
    private int eggTimer;
    private double timeReduce;
    private int timeCounter;
    private long lastLaser;
    private long lastEggTime;
    private long lastMovement;

    private BitmapFont font;

    private Sound laserSound;
    private Sound bawkSound;

    public Play(BawkGame game_){
        game = game_;
    }

    @Override
    public void show()
    {
        System.out.println("Show");
        backgroundImage = new Texture("gamebackground.png");
        gameplayImage = new Texture("gameplayarea.png");

        laserSound = Gdx.audio.newSound(Gdx.files.internal("laserSound.wav"));
        bawkSound = Gdx.audio.newSound(Gdx.files.internal("bawksound.mp3"));
        bawkSound.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());
        batch = new SpriteBatch();

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());

        gameplay = new Rectangle(game.getNestX(), game.getNestY(),
                                 gameplayImage.getWidth(),
                                 gameplayImage.getWidth());

        bawk = new Bawk();

        eggs = new Array<Array<Egg>>();
        for(int i=0; i<16; ++i){
            eggs.add(new Array<Egg>());
        }
//        font = new BitmapFont(Gdx.files.internal("fontVer2.fnt"), Gdx.files.internal("fontImage.png"), false);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.scale(2);

        score = 0;
        eggTimer = game.difficulty;
        timeReduce = .001;
        timeCounter = 0;
        lastLaser = 0;
        lastEggTime = TimeUtils.millis();
        lastMovement = TimeUtils.millis();

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

        for(Array<Egg> arr : eggs)
            for(Egg egg : arr)
                egg.draw(batch);

        for(Laser laser : bawk.lasers) {
            laser.update();
            if(!laser.getBoundingRectangle().overlaps(gameplay))
                bawk.lasers.removeValue(laser, false); //destroy the laser
            else
                laser.draw(batch);
        }

        font.draw(batch, "Score: "+String.valueOf(score), 150, 100);

        batch.end();
    }

    @Override
    public void render(float delta)
    {
        drawBatch();

        //updating & input detection
        updateBawkLocation();

        detectOverlaps();

        long myShotTimer = 220;
        if((Gdx.input.isTouched() || Gdx.input.isButtonPressed(Input.Keys.SPACE))
                && TimeUtils.millis() - lastLaser > myShotTimer) {
            bawk.shoot();
            laserSound.play();
            lastLaser = TimeUtils.millis();
        }

        // check if we need to create a new egg
        if(TimeUtils.millis() - lastEggTime > eggTimer) {
            spawnEgg();
            lastEggTime = TimeUtils.millis();
            timeCounter++;
        }


        if(timeCounter == 3){
            eggTimer -= (int)(eggTimer*timeReduce);
            timeCounter = 0;
        }


    }

    private void detectOverlaps()
    {
        int comboSize = 0;

        for(Array<Egg> arr : eggs) {
            for (Egg egg : arr) {
                for (Laser j : bawk.lasers) {
                    if (j.getBoundingRectangle().overlaps(egg.getBoundingRectangle())) {

                        if (bawk.getColor() == egg.getColor()) //the laser and the colliding egg have the same color
                        {
                            arr.removeValue(egg, false); //destroy the egg
                            ++score;
                            ++comboSize;
                        }
                        else //laser and egg are different colors, so swap them
                        {
                            Color temp = egg.getColor();
                            egg.setColor(bawk.getColor());
                            bawk.setColor(temp);
                            bawk.lasers.removeValue(j, false); //destroy the laser
                            break;
                        }

                    }
                }
            }
        }

        int multiplier = 0;
        if(comboSize == 2)
            multiplier = 1;
        else if(comboSize == 3)
            multiplier = 2;
        else if(comboSize == 4)
            multiplier = 3;

        score += multiplier*comboSize;
    }

    private void spawnEgg()
    {
        Random rand = new Random();
        int num = rand.nextInt(16);

        if(eggs.get(num).size == 4){
            bawkSound.play();
            game.highScoreScreen.setFinalScore(score);
            game.setScreen(game.gameOver);
        }

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
        newEgg.setPosition(x+4, y+4); // +4 to fix centering
        eggs.get(num).add(newEgg);

    }

    private void updateBawkLocation()
    {
        long movementTimer = 400;
        if(TimeUtils.millis() - lastMovement < movementTimer)
            return;

        lastMovement = TimeUtils.millis();

        float tiltX = Gdx.input.getAccelerometerX();
        float tiltY = Gdx.input.getAccelerometerY();
        float boundary = 1.2f;
        int movement = 48;

        if(Math.abs(tiltX) > Math.abs(tiltY)) {
            if (tiltX > boundary) {
                bawk.setX(bawk.getX() - movement);
                bawk.leftRotate();
            } else if (tiltX < -1f * boundary) {
                bawk.setX(bawk.getX() + movement);
                bawk.rightRotate();
            }
        }
        else {
            if (tiltY > boundary) {
                bawk.setY(bawk.getY() - movement);
                bawk.downRotate();
            } else if (tiltY < -1f * boundary) {
                bawk.setY(bawk.getY() + movement);
                bawk.upRotate();
            }
        }

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
        laserSound.dispose();
        bawkSound.dispose();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

}
