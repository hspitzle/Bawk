package com.eecs493.bawk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Hayden on 4/13/2015.
 */
public class ModeScreen implements Screen {

    private BawkGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture backgroundImage;
    private Texture background1;
    private Texture background2;
    private Rectangle background;


    private BitmapFont font;

    private Music endMusic;

    private Stage stage;
    private ImageButton normalButton;
    private ImageButton puzzleButton;
    private ButtonGroup modeButtons;

    private ImageButton playButton;
    private ImageButton homeButton;

    private Sprite modeSelect;
    private Sprite barnSign;

    public ModeScreen(BawkGame game_){
        game = game_;
    }

    @Override
    public void show()
    {
        background1 = new Texture("classicselectscreen.png");
        background2 = new Texture("puzzleselectscreen.png");
        backgroundImage = background1;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());
        batch = new SpriteBatch();

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());

        Texture signTexture = new Texture("sign2.png");
        barnSign = new Sprite(signTexture);
        barnSign.setSize(signTexture.getWidth(), signTexture.getHeight());
        barnSign.setScale(0.55f, 0.38f);
        barnSign.setPosition(game.getWidth()/2 - signTexture.getWidth()/2, 560);
        barnSign.rotate(-2.5f);

        //TODO: select a mode screen
        Texture diffModeTexture = new Texture("texteasymode.png");
        modeSelect = new Sprite(diffModeTexture);
        modeSelect.setPosition(game.getWidth()/2 - diffModeTexture.getWidth()/2, 650);
        modeSelect.setSize(diffModeTexture.getWidth(), diffModeTexture.getHeight());
        modeSelect.setScale(0.95f);
        modeSelect.rotate(-2.5f);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.scale(1.7f);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //TODO: change to normal mode button
        Texture normTextureUp = new Texture("classic.png");
        Texture normTextureDown = new Texture("classic2.png");
        SpriteDrawable normDrawableUp = new SpriteDrawable(new Sprite(normTextureUp));
        SpriteDrawable normDrawableDown = new SpriteDrawable(new Sprite(normTextureDown));
        normDrawableUp.setMinHeight(Gdx.graphics.getWidth()/4);
        normDrawableUp.setMinWidth(2*Gdx.graphics.getWidth()/4);
        normDrawableDown.setMinHeight(Gdx.graphics.getWidth()/4);
        normDrawableDown.setMinWidth(2*Gdx.graphics.getWidth()/4);
        normalButton = new ImageButton(normDrawableUp, normDrawableDown);
        // playButton.setSize(playButton.getWidth() * 2, playButton.getHeight() * 2);
        //playButton = new TextButton("Play!", skin); // Use the initialized skin
        normalButton.setPosition(Gdx.graphics.getWidth() / 5 + Gdx.graphics.getHeight() / 32, Gdx.graphics.getWidth() / 16 + Gdx.graphics.getHeight() / 7 + 70);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        normalButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //normalButton.toggle();
                game.puzzleFlag = false;
//                normalButton.toggle();
                //TODO: change background texture
                backgroundImage = background1;
            }
        });

        //TODO: change to puzzle mode button
        Texture puzzleTextureUp = new Texture("puzzle.png");
        Texture puzzleTextureDown = new Texture("puzzle2.png");
        SpriteDrawable puzzleDrawableUp = new SpriteDrawable(new Sprite(puzzleTextureUp));
        SpriteDrawable puzzleDrawableDown = new SpriteDrawable(new Sprite(puzzleTextureDown));
        puzzleDrawableUp.setMinHeight(Gdx.graphics.getWidth()/4);
        puzzleDrawableUp.setMinWidth(2*Gdx.graphics.getWidth()/4);
        puzzleDrawableDown.setMinHeight(Gdx.graphics.getWidth()/4);
        puzzleDrawableDown.setMinWidth(2*Gdx.graphics.getWidth()/4);
        puzzleButton = new ImageButton(puzzleDrawableUp, puzzleDrawableDown);
        // playButton.setSize(playButton.getWidth() * 2, playButton.getHeight() * 2);
        //playButton = new TextButton("Play!", skin); // Use the initialized skin
        puzzleButton.setPosition(Gdx.graphics.getWidth() / 5 + Gdx.graphics.getHeight() / 32, Gdx.graphics.getHeight() / 7 - Gdx.graphics.getWidth() / 7 + 15);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        puzzleButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               // puzzleButton.toggle();
                game.puzzleFlag = true;
//                puzzleButton.toggle();
                //TODO: change background texture
                backgroundImage = background2;
            }
        });

        modeButtons = new ButtonGroup();
        modeButtons.add(normalButton);
        modeButtons.add(puzzleButton);
        modeButtons.setMaxCheckCount(1); //only one difficulty choice can be selected ata  time

        stage.addActor(normalButton);
        stage.addActor(puzzleButton);

        float scale = 0.8f;
        int offset = 25;

        //home button initializing
        Texture homeTextureUp = new Texture("home.png");
        Texture homeTextureDown = new Texture("home2.png");
        SpriteDrawable homeDrawableUp = new SpriteDrawable(new Sprite(homeTextureUp));
        SpriteDrawable homeDrawableDown = new SpriteDrawable(new Sprite(homeTextureDown));
        homeDrawableUp.setMinHeight(scale * game.scaledY(homeTextureUp.getHeight()));
        homeDrawableUp.setMinWidth(scale * game.scaledX(homeTextureUp.getWidth()));
        homeDrawableDown.setMinHeight(scale * game.scaledY(homeTextureDown.getHeight()));
        homeDrawableDown.setMinWidth(scale * game.scaledX(homeTextureDown.getWidth()));
        homeButton = new ImageButton(homeDrawableUp, homeDrawableDown, homeDrawableDown);
        homeButton.setPosition(
                game.scaledX(offset),
                Gdx.graphics.getWidth()/17);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(game.welcome); //return to the home screen
            }
        });
        stage.addActor(homeButton);

        Texture playTextureUp = new Texture("check.png");
        Texture playTextureDown = new Texture("check2.png");
        SpriteDrawable playDrawableUp = new SpriteDrawable(new Sprite(playTextureUp));
        SpriteDrawable playDrawableDown = new SpriteDrawable(new Sprite(playTextureDown));
        playDrawableUp.setMinHeight(scale * game.scaledY(playTextureUp.getHeight()));
        playDrawableUp.setMinWidth(scale * game.scaledX(playTextureUp.getWidth()));
        playDrawableDown.setMinHeight(scale * game.scaledY(playTextureDown.getHeight()));
        playDrawableDown.setMinWidth(scale * game.scaledX(playTextureDown.getWidth()));
        playButton = new ImageButton(playDrawableUp, playDrawableDown, playDrawableDown);
        playButton.setPosition(
                game.scaledX(game.getWidth() - (int)(scale*playTextureUp.getWidth()) - offset),
                Gdx.graphics.getWidth()/17);

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(game.play); //return to the home screen
            }
        });
        stage.addActor(playButton);

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

//        barnSign.draw(batch);
  //      modeSelect.draw(batch);

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
        normalButton.setTouchable(Touchable.disabled);
        puzzleButton.setTouchable(Touchable.disabled);
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
        normalButton.setTouchable(Touchable.enabled);
        puzzleButton.setTouchable(Touchable.enabled);
    }

}
