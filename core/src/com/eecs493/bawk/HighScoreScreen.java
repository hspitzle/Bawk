package com.eecs493.bawk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.audio.Music;

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

    private Stage stage;
    private ImageButton retryButton;
    private ImageButton homeButton;

    private Sprite difficultyMode;

    public HighScoreScreen(BawkGame game_){
        game = game_;
        finalScore = 0;
        highScore = new HighScore(game);
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
        backgroundImage = new Texture("scorescreen.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());
        batch = new SpriteBatch();

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());

        Texture diffModeTexture = new Texture("texteasymode.png");
        difficultyMode = new Sprite(diffModeTexture);
        difficultyMode.setPosition(game.getWidth()/2 - diffModeTexture.getWidth()/2, 400);
        difficultyMode.setSize(diffModeTexture.getWidth(), diffModeTexture.getHeight());

        if(game.difficulty == BawkGame.Difficulty.MEDIUM.getValue())
            difficultyMode.setTexture(new Texture("textmedmode.png"));
        else if(game.difficulty == BawkGame.Difficulty.HARD.getValue())
            difficultyMode.setTexture(new Texture("texthardmode.png"));

        if (game.musicOnFlag) {
            //game.music.setPosition(0f);
            Music endMusic = Gdx.audio.newMusic(Gdx.files.internal("highscoremusic.mp3"));
            endMusic.setLooping(true);
            endMusic.play();
        }

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.scale(1.7f);

        updateHighScore();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Texture retryTextureUp = new Texture("retry.png");
        Texture retryTextureDown = new Texture("retry2.png");
        SpriteDrawable retryDrawableUp = new SpriteDrawable(new Sprite(retryTextureUp));
        SpriteDrawable retryDrawableDown = new SpriteDrawable(new Sprite(retryTextureDown));
        retryDrawableUp.setMinHeight(Gdx.graphics.getWidth()/4);
        retryDrawableUp.setMinWidth(2*Gdx.graphics.getWidth()/4);
        retryDrawableDown.setMinHeight(Gdx.graphics.getWidth()/4);
        retryDrawableDown.setMinWidth(2*Gdx.graphics.getWidth()/4);
        retryButton = new ImageButton(retryDrawableUp, retryDrawableDown);
        // playButton.setSize(playButton.getWidth() * 2, playButton.getHeight() * 2);
        //playButton = new TextButton("Play!", skin); // Use the initialized skin
        retryButton.setPosition(Gdx.graphics.getWidth()/ 5 + Gdx.graphics.getHeight()/32, Gdx.graphics.getWidth()/16 + Gdx.graphics.getHeight()/7 + 70);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        retryButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.music.stop();
                game.setScreen(game.play);
            }
        });

        Texture homeTextureUp = new Texture("scoretohome.png");
        Texture homeTextureDown = new Texture("scoretohome2.png");
        SpriteDrawable homeDrawableUp = new SpriteDrawable(new Sprite(homeTextureUp));
        SpriteDrawable homeDrawableDown = new SpriteDrawable(new Sprite(homeTextureDown));
        homeDrawableUp.setMinHeight(Gdx.graphics.getWidth()/4);
        homeDrawableUp.setMinWidth(2*Gdx.graphics.getWidth()/4);
        homeDrawableDown.setMinHeight(Gdx.graphics.getWidth()/4);
        homeDrawableDown.setMinWidth(2*Gdx.graphics.getWidth()/4);
        homeButton = new ImageButton(homeDrawableUp, homeDrawableDown);
        // playButton.setSize(playButton.getWidth() * 2, playButton.getHeight() * 2);
        //playButton = new TextButton("Play!", skin); // Use the initialized skin
        homeButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/32, Gdx.graphics.getHeight()/7 -Gdx.graphics.getWidth()/7 + 15);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(game.welcome);
            }
        });

        stage.addActor(retryButton);
        stage.addActor(homeButton);

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

        difficultyMode.draw(batch);

        font.draw(batch, "Final Score: "+String.valueOf(finalScore), 110, 380);
        font.draw(batch, "High Score: "+String.valueOf(highScore.getHighScore()), 110, 430);

        batch.end();
    }

    @Override
    public void render(float delta)
    {
        drawBatch();
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        retryButton.setTouchable(Touchable.disabled);
        homeButton.setTouchable(Touchable.disabled);
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
    public void resume() {
        retryButton.setTouchable(Touchable.enabled);
        homeButton.setTouchable(Touchable.enabled);
    }

}
