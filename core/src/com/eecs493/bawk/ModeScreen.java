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
    private ImageButton classicButton;
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

        float scale = 1.5f;

        Texture normTextureUp = new Texture("classic.png");
        Texture normTextureDown = new Texture("classic2.png");
        SpriteDrawable normDrawableUp = new SpriteDrawable(new Sprite(normTextureUp));
        SpriteDrawable normDrawableDown = new SpriteDrawable(new Sprite(normTextureDown));
        normDrawableUp.setMinHeight(scale * game.scaledY(normTextureUp.getHeight()));
        normDrawableUp.setMinWidth(scale * game.scaledX(normTextureUp.getWidth()));
        normDrawableDown.setMinHeight(scale * game.scaledY(normTextureDown.getHeight()));
        normDrawableDown.setMinWidth(scale * game.scaledX(normTextureDown.getWidth()));
        classicButton = new ImageButton(normDrawableUp, normDrawableDown);
        classicButton.setPosition(game.scaledX(game.getWidth() / 2 - (int) (scale * normTextureUp.getWidth() / 2)), game.scaledY(170));
        classicButton = new ImageButton(normDrawableUp, normDrawableDown, normDrawableDown);
        classicButton.setPosition(game.scaledX(game.getWidth()/2 - (int)(scale*normTextureUp.getWidth()/2)), game.scaledY(170));
        classicButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.puzzleFlag = false;
                backgroundImage = background1;
            }
        });

        Texture puzzleTextureUp = new Texture("puzzle.png");
        Texture puzzleTextureDown = new Texture("puzzle2.png");
        SpriteDrawable puzzleDrawableUp = new SpriteDrawable(new Sprite(puzzleTextureUp));
        SpriteDrawable puzzleDrawableDown = new SpriteDrawable(new Sprite(puzzleTextureDown));
        puzzleDrawableUp.setMinHeight(scale * game.scaledY(puzzleTextureUp.getHeight()));
        puzzleDrawableUp.setMinWidth(scale * game.scaledX(puzzleTextureUp.getWidth()));
        puzzleDrawableDown.setMinHeight(scale * game.scaledY(puzzleTextureDown.getHeight()));
        puzzleDrawableDown.setMinWidth(scale * game.scaledX(puzzleTextureDown.getWidth()));
        puzzleButton = new ImageButton(puzzleDrawableUp, puzzleDrawableDown, puzzleDrawableDown);
        puzzleButton.setPosition(game.scaledX(game.getWidth()/2 - (int)(scale*puzzleTextureUp.getWidth()/2)), game.scaledY(60));
        puzzleButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.puzzleFlag = true;
                backgroundImage = background2;
            }
        });

        modeButtons = new ButtonGroup();
        modeButtons.add(classicButton);
        modeButtons.add(puzzleButton);
        modeButtons.setMaxCheckCount(1); //only one difficulty choice can be selected ata  time

        stage.addActor(classicButton);
        stage.addActor(puzzleButton);

        float smallscale = 0.8f;
        int offset = 25;

        //home button initializing
        Texture homeTextureUp = new Texture("home.png");
        Texture homeTextureDown = new Texture("home2.png");
        SpriteDrawable homeDrawableUp = new SpriteDrawable(new Sprite(homeTextureUp));
        SpriteDrawable homeDrawableDown = new SpriteDrawable(new Sprite(homeTextureDown));
        homeDrawableUp.setMinHeight(smallscale * game.scaledY(homeTextureUp.getHeight()));
        homeDrawableUp.setMinWidth(smallscale * game.scaledX(homeTextureUp.getWidth()));
        homeDrawableDown.setMinHeight(smallscale * game.scaledY(homeTextureDown.getHeight()));
        homeDrawableDown.setMinWidth(smallscale * game.scaledX(homeTextureDown.getWidth()));
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
        playDrawableUp.setMinHeight(smallscale * game.scaledY(playTextureUp.getHeight()));
        playDrawableUp.setMinWidth(smallscale * game.scaledX(playTextureUp.getWidth()));
        playDrawableDown.setMinHeight(smallscale * game.scaledY(playTextureDown.getHeight()));
        playDrawableDown.setMinWidth(smallscale * game.scaledX(playTextureDown.getWidth()));
        playButton = new ImageButton(playDrawableUp, playDrawableDown, playDrawableDown);
        playButton.setPosition(
                game.scaledX(game.getWidth() - (int)(smallscale*playTextureUp.getWidth()) - offset),
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
        classicButton.setTouchable(Touchable.disabled);
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
        classicButton.setTouchable(Touchable.enabled);
        puzzleButton.setTouchable(Touchable.enabled);
    }

}
