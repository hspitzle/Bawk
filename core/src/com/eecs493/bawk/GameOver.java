package com.eecs493.bawk;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

//import java.awt.Rectangle;

/**
 * Created by Hayden on 3/20/2015.
 */
public class GameOver implements Screen {

    private BawkGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture backgroundImage;
    private Rectangle background;

    private long displayTime;
    private long startTime;

    private Sound thunder;

    public GameOver(BawkGame game_){
        game = game_;
    }

    @Override
    public void show()
    {
        System.out.println("Show");
        backgroundImage = new Texture("gameover.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());
        batch = new SpriteBatch();

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());

        thunder = Gdx.audio.newSound(Gdx.files.internal("thunder.wav"));
        thunder.play();

        displayTime = 2000;
        startTime = TimeUtils.millis();
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
        batch.end();
    }

    @Override
    public void render(float delta)
    {
        drawBatch();

        if(TimeUtils.millis() > startTime + displayTime)
            game.setScreen(game.highScoreScreen);
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
    }

    @Override
    public void dispose()
    {
        backgroundImage.dispose();
        batch.dispose();
        thunder.dispose();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

}
