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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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
    private long lastLaser;
    private long lastEggTime;
    private long lastMovement;

    private BitmapFont font;

    private Sound laserSound;
    private Sound bawkSound;

    private ImageButton fireButton;
//    private Stage stage;

    private final int movement = 48;

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
        lastLaser = 0;
        lastEggTime = TimeUtils.millis();
        lastMovement = TimeUtils.millis();

        if(game.swipe){
            Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

                @Override
                public void onUp() {
                    bawk.moveUp(movement);
                    checkBounds();
                }

                @Override
                public void onRight() {
                    bawk.moveRight(movement);
                    checkBounds();
                }

                @Override
                public void onLeft() {
                    bawk.moveLeft(movement);
                    checkBounds();
                }

                @Override
                public void onDown() {
                    bawk.moveDown(movement);
                    checkBounds();
                }

                @Override
                public void onTap(){
                    fire();
                }
            }));
        }

//        Texture helpTextureUp = new Texture("fire.png");
//        Texture helpTextureDown = new Texture("fire2.png");
//        SpriteDrawable helpDrawableUp = new SpriteDrawable(new Sprite(helpTextureUp));
//        SpriteDrawable helpDrawableDown = new SpriteDrawable(new Sprite(helpTextureDown));
//        helpDrawableUp.setMinWidth(Gdx.graphics.getWidth()/4);
//        helpDrawableUp.setMinHeight(Gdx.graphics.getWidth() / 4);
//        helpDrawableDown.setMinWidth(Gdx.graphics.getWidth()/4);
//        helpDrawableDown.setMinHeight(Gdx.graphics.getWidth()/4);
//        fireButton = new ImageButton(helpDrawableUp, helpDrawableDown);
//        //howToButton = new TextButton("Help!", skin); // Use the initialized skin
//        fireButton.setPosition(Gdx.graphics.getWidth()/2 +5, Gdx.graphics.getHeight()/7 -Gdx.graphics.getWidth()/5);
//        //howToButton.setWidth(2 * Gdx.graphics.getWidth()/3);
//
//        fireButton.addListener(new ChangeListener() {
//            @Override
//            public void changed (ChangeEvent event, Actor actor) {
//                //backgroundImage = new Texture ("howtoscreen.png");
//                // playButton.setTouchable(Touchable.disabled);
//                // howToButton.setTouchable(Touchable.disabled);
//                // settingsButton.setTouchable(Touchable.disabled);
//                long myShotTimer = 220;
//                if(TimeUtils.millis() - lastLaser > myShotTimer) {
//                    fire();
//                }
//            }
//        });

        spawnEgg();
    }

    private void fire(){
        bawk.shoot();
        laserSound.play();
        lastLaser = TimeUtils.millis();
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

        // check if we need to create a new commet
        if(TimeUtils.millis() - lastEggTime > eggTimer) {
            spawnEgg();
            lastEggTime = TimeUtils.millis();
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
        if(game.swipe)
            return;

        long movementTimer = 400;
        if(TimeUtils.millis() - lastMovement < movementTimer)
            return;

        lastMovement = TimeUtils.millis();

        tiltToMove();
        checkBounds();
    }

    private void tiltToMove()
    {
        float tiltX = Gdx.input.getAccelerometerX();
        float tiltY = Gdx.input.getAccelerometerY();
        float boundary = 1.2f;

        if(Math.abs(tiltX) > Math.abs(tiltY)) {
            if (tiltX > boundary) {
                bawk.moveLeft(movement);
            } else if (tiltX < -1f * boundary) {
                bawk.moveRight(movement);
            }
        }
        else {
            if (tiltY > boundary) {
                bawk.moveDown(movement);
            } else if (tiltY < -1f * boundary) {
                bawk.moveUp(movement);
            }
        }
    }

    private void checkBounds()
    {
        // keep within x bounds
        if(bawk.getX() > game.getLocationX(7))
            bawk.setX(game.getLocationX(7));
        else if(bawk.getX() < game.getLocationX(4))
            bawk.setX(game.getLocationX(4));

        // keep within y bounds
        if(bawk.getY() < game.getLocationY(7))
            bawk.setY(game.getLocationY(7));
        else if(bawk.getY() > game.getLocationY(4))
            bawk.setY(game.getLocationY(4));
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        fireButton.setTouchable(Touchable.disabled);
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
    public void resize(int width, int height) {
        fireButton.setTouchable(Touchable.enabled);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

}
