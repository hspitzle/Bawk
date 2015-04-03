package com.eecs493.bawk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Hayden on 4/2/2015.
 */
public class HighScoreScreen implements Screen {

    private BawkGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture backgroundImage;
    private Rectangle background;

    private int finalScore;
    private HighScore highScore;

    private BitmapFont font;

    public HighScoreScreen(BawkGame game_){
        game = game_;
        finalScore = 0;
        highScore = new HighScore();
    }

    public void setFinalScore(int finalScore_){
        finalScore = finalScore_;
    }

    public void updateHighScore(){
        highScore.setHighScore(finalScore);
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

        game.music.play();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.scale(2);

        updateHighScore();

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


        font.draw(batch, "Final Score: "+String.valueOf(finalScore), 100, 400);
        font.draw(batch, "High Score: "+String.valueOf(highScore.getHighScore()), 100, 450);


        batch.end();
    }

    @Override
    public void render(float delta)
    {
        drawBatch();

        if(Gdx.input.isTouched())
            game.setScreen(game.welcome);
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
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

}
