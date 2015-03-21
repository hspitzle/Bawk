package com.eecs493.bawk;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;

import java.awt.Point;

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

        batch.end();
    }

    @Override
    public void render(float delta)
    {
        drawBatch();

        //updating & input detection
        if (Gdx.input.isTouched())
        {
            Gdx.app.log("my app", "screen touched");
            bawk.rotate90(true);
        }

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
