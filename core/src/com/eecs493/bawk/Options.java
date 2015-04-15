package com.eecs493.bawk;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;


//import java.awt.Rectangle;

/**
 * Created by Hayden on 3/20/2015.
 */
public class Options implements Screen {

    private BawkGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture backgroundImage;
    private Rectangle background;

    //private RadioGroup difficultyButtons; //prevents multiple difficulties from being checked at once
    private Stage stage;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private BitmapFont font;
    private TextButton.TextButtonStyle textButtonStyle;

    //difficulty buttons
    private ImageButton easyButton;
    private ImageButton mediumButton;
    private ImageButton hardButton;
    private ImageButton swipeButton;
    private ImageButton tiltButton;
    private ImageButton muteMusicButton;
    private ImageButton muteSoundsButton;
    private ImageButton homeButton;
    ButtonGroup difficultyButtons;
    ButtonGroup modeButtons;

    public Options(BawkGame game_){
        game = game_;
    }

    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        font.setScale(5, 7);
        font.setColor( Color.GREEN);
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("background",new Texture(pixmap));
        //skin.add("background", backgroundImage);

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

    }

    @Override
    public void show()
    {
        System.out.println("Show options screen");
        backgroundImage = new Texture("settingsscreen.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        createBasicSkin();
        System.out.println("Show");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());
        batch = new SpriteBatch();

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());

        //initializing the difficulty buttons
        //easy
        Texture easyTextureUp = new Texture("easy.png");
        Texture easyTextureDown = new Texture("easy2.png");
        SpriteDrawable easyDrawableUp = new SpriteDrawable(new Sprite(easyTextureUp));
        SpriteDrawable easyDrawableDown = new SpriteDrawable(new Sprite(easyTextureDown));
        easyDrawableUp.setMinHeight(3*Gdx.graphics.getWidth()/28);
        easyDrawableUp.setMinWidth(Gdx.graphics.getWidth()/3);
        easyDrawableDown.setMinHeight(3*Gdx.graphics.getWidth()/28);
        easyDrawableDown.setMinWidth(Gdx.graphics.getWidth()/3);
        easyButton = new ImageButton(easyDrawableUp, easyDrawableDown, easyDrawableDown);
        easyButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/11, Gdx.graphics.getWidth()/6 + Gdx.graphics.getHeight()/2);
        float easyPosition = Gdx.graphics.getWidth()/6 + Gdx.graphics.getHeight()/2;
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        if (game.getEasyPref())
            easyButton.toggle();
        easyButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);

                //game.setScreen(game.play);
                game.difficulty = BawkGame.Difficulty.EASY.getValue();
                game.easyFlag = true;
                game.mediumFlag = false;
                game.hardFlag = false;

                game.setEasyPref(game.easyFlag);
                game.setMediumPref(game.mediumFlag);
                game.setHardPref(game.hardFlag);
            }
        });

        //medium
        Texture mediumTextureUp = new Texture("medium.png");
        Texture mediumTextureDown = new Texture("medium2.png");
        SpriteDrawable mediumDrawableUp = new SpriteDrawable(new Sprite(mediumTextureUp));
        SpriteDrawable mediumDrawableDown = new SpriteDrawable(new Sprite(mediumTextureDown));
        mediumDrawableUp.setMinHeight(3*Gdx.graphics.getWidth()/28);
        mediumDrawableUp.setMinWidth(Gdx.graphics.getWidth()/3);
        mediumDrawableDown.setMinHeight(3*Gdx.graphics.getWidth()/28);
        mediumDrawableDown.setMinWidth(Gdx.graphics.getWidth()/3);
        mediumButton = new ImageButton(mediumDrawableUp, mediumDrawableDown, mediumDrawableDown);
        mediumButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/11, easyPosition - (3*Gdx.graphics.getWidth()/28));
        float mediumPosition = easyPosition - (3*Gdx.graphics.getWidth()/28);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        if (game.getMediumPref())
            mediumButton.toggle();
        mediumButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);

                //game.setScreen(game.play);
                game.difficulty = BawkGame.Difficulty.MEDIUM.getValue();
                game.easyFlag = false;
                game.mediumFlag = true;
                game.hardFlag = false;

                game.setEasyPref(game.easyFlag);
                game.setMediumPref(game.mediumFlag);
                game.setHardPref(game.hardFlag);
            }
        });

        //hard
        Texture hardTextureUp = new Texture("hard.png");
        Texture hardTextureDown = new Texture("hard2.png");
        SpriteDrawable hardDrawableUp = new SpriteDrawable(new Sprite(hardTextureUp));
        SpriteDrawable hardDrawableDown = new SpriteDrawable(new Sprite(hardTextureDown));
        hardDrawableUp.setMinHeight(3*Gdx.graphics.getWidth()/28);
        hardDrawableUp.setMinWidth(Gdx.graphics.getWidth()/3);
        hardDrawableDown.setMinHeight(3*Gdx.graphics.getWidth()/28);
        hardDrawableDown.setMinWidth(Gdx.graphics.getWidth()/3);
        hardButton = new ImageButton(hardDrawableUp, hardDrawableDown, hardDrawableDown);
        hardButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/11 , mediumPosition - (3*Gdx.graphics.getWidth()/28));
        float hardPosition = mediumPosition - (3*Gdx.graphics.getWidth()/28);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        if (game.getHardPref())
            hardButton.toggle();

        hardButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);
                //game.setScreen(game.play);
                game.difficulty = BawkGame.Difficulty.HARD.getValue();
                game.easyFlag = false;
                game.mediumFlag = false;
                game.hardFlag = true;

                game.setEasyPref(game.easyFlag);
                game.setMediumPref(game.mediumFlag);
                game.setHardPref(game.hardFlag);
            }
        });

        difficultyButtons = new ButtonGroup();
        difficultyButtons.add(easyButton);
        difficultyButtons.add(mediumButton);
        difficultyButtons.add(hardButton);
        difficultyButtons.setMaxCheckCount(1); //only one difficulty choice can be selected ata  time

        //swipe
        Texture swipeTextureUp = new Texture("swipe.png");
        Texture swipeTextureDown = new Texture("swipe2.png");
        SpriteDrawable swipeDrawableUp = new SpriteDrawable(new Sprite(swipeTextureUp));
        SpriteDrawable swipeDrawableDown = new SpriteDrawable(new Sprite(swipeTextureDown));
        swipeDrawableUp.setMinHeight(3*Gdx.graphics.getWidth()/28);
        swipeDrawableUp.setMinWidth(Gdx.graphics.getWidth()/3);
        swipeDrawableDown.setMinHeight(3*Gdx.graphics.getWidth()/28);
        swipeDrawableDown.setMinWidth(Gdx.graphics.getWidth()/3);
        swipeButton = new ImageButton(swipeDrawableUp, swipeDrawableDown, swipeDrawableDown);
        swipeButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/11 , hardPosition - 2*(3*Gdx.graphics.getWidth()/28));
        float swipePosition = hardPosition - 2*(3*Gdx.graphics.getWidth()/28);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        if (game.getSwipePref())
        {
            swipeButton.toggle();
        }
        swipeButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);
                //game.setScreen(game.play);
                game.swipe = true;
                game.swipeFlag = true;
                game.tiltFlag = false;
                game.setSwipePref(game.swipeFlag);
            }
        });

        //tilt
        Texture tiltTextureUp = new Texture("tilt.png");
        Texture tiltTextureDown = new Texture("tilt2.png");
        SpriteDrawable tiltDrawableUp = new SpriteDrawable(new Sprite(tiltTextureUp));
        SpriteDrawable tiltDrawableDown = new SpriteDrawable(new Sprite(tiltTextureDown));
        tiltDrawableUp.setMinHeight(3*Gdx.graphics.getWidth()/28);
        tiltDrawableUp.setMinWidth(Gdx.graphics.getWidth()/3);
        tiltDrawableDown.setMinHeight(3*Gdx.graphics.getWidth()/28);
        tiltDrawableDown.setMinWidth(Gdx.graphics.getWidth()/3);
        tiltButton = new ImageButton(tiltDrawableUp, tiltDrawableDown, tiltDrawableDown);
        tiltButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/11 , swipePosition - (3*Gdx.graphics.getWidth()/28));
        float tiltPosition = swipePosition - (3*Gdx.graphics.getWidth()/28);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        if (!game.getSwipePref())
        {
            tiltButton.toggle();
        }
        tiltButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);
                //game.setScreen(game.play);
               // game.difficulty = BawkGame.Difficulty.HARD.getValue();
                // = BawkGame.Mode.TILT.getValue();
                game.swipe = false;
                game.swipeFlag = false;
                game.tiltFlag = true;
                game.setSwipePref(game.swipeFlag);
            }
        });

        float scale = 1.1f;
        int offset = 100;
        int muteYposition = 75;

        //muteSounds
        Texture muteSoundsTextureUp = new Texture("mute.png");
        Texture muteSoundsTextureDown = new Texture("mute2.png");
        SpriteDrawable muteSoundsDrawableUp = new SpriteDrawable(new Sprite(muteSoundsTextureUp));
        SpriteDrawable muteSoundsDrawableDown = new SpriteDrawable(new Sprite(muteSoundsTextureDown));
        muteSoundsDrawableUp.setMinHeight(scale * game.scaledY(muteSoundsTextureUp.getHeight()));
        muteSoundsDrawableUp.setMinWidth(scale * game.scaledX(muteSoundsTextureUp.getWidth()));
        muteSoundsDrawableDown.setMinHeight(scale * game.scaledY(muteSoundsTextureDown.getHeight()));
        muteSoundsDrawableDown.setMinWidth(scale * game.scaledX(muteSoundsTextureDown.getWidth()));
        muteSoundsButton = new ImageButton(muteSoundsDrawableUp, muteSoundsDrawableDown, muteSoundsDrawableDown);
        muteSoundsButton.setPosition(
                game.scaledX(offset),
                game.scaledY(muteYposition));
        float muteSoundsPosition = tiltPosition - 3*(3*Gdx.graphics.getWidth()/28);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);

        if (!game.getSoundFXPref()) //the user has muted the sound effects
            muteSoundsButton.toggle();

        muteSoundsButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);
                //game.setScreen(game.play);
                // game.difficulty = BawkGame.Difficulty.HARD.getValue();
                // = BawkGame.Mode.TILT.getValue();
                //if (game.music.isPlaying())
               //     game.music.pause();
               // else
               //     game.music.play();

                if (game.getSoundFXPref())
                    game.soundEffectsOnFlag = false;
                else
                    game.soundEffectsOnFlag = true;

                game.setSoundFXPref(game.soundEffectsOnFlag);
            }
        });

        //muteMusic
        Texture muteMusicTextureUp = new Texture("mute.png");
        Texture muteMusicTextureDown = new Texture("mute2.png");
        SpriteDrawable muteMusicDrawableUp = new SpriteDrawable(new Sprite(muteMusicTextureUp));
        SpriteDrawable muteMusicDrawableDown = new SpriteDrawable(new Sprite(muteMusicTextureDown));
        muteMusicDrawableUp.setMinHeight(scale * game.scaledY(muteMusicTextureUp.getHeight()));
        muteMusicDrawableUp.setMinWidth(scale * game.scaledX(muteMusicTextureUp.getWidth()));
        muteMusicDrawableDown.setMinHeight(scale * game.scaledY(muteMusicTextureDown.getHeight()));
        muteMusicDrawableDown.setMinWidth(scale * game.scaledX(muteMusicTextureDown.getWidth()));
        muteMusicButton = new ImageButton(muteMusicDrawableUp, muteMusicDrawableDown, muteMusicDrawableDown);
        muteMusicButton.setPosition(
                game.scaledX(game.getWidth() - (int)(scale*muteMusicTextureUp.getWidth()) - offset + 10),
                game.scaledY(muteYposition));
        float muteMusicPosition = muteSoundsPosition - 3*(3*Gdx.graphics.getWidth()/28);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);

        if (!game.getMusicPref()) //the user has muted the music
            muteMusicButton.toggle();

        muteMusicButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);
                //game.setScreen(game.play);
                // game.difficulty = BawkGame.Difficulty.HARD.getValue();
                // = BawkGame.Mode.TILT.getValue();
                if (game.music.isPlaying())
                {
                    game.musicOnFlag = false;
                    game.music.pause();
                }
                else
                {
                    game.musicOnFlag = true;
                    game.music.play();
                }

                game.setMusicPref(game.musicOnFlag);
            }
        });

        //home button initializing
        Texture homeTextureUp = new Texture("home.png");
        Texture homeTextureDown = new Texture("home2.png");
        SpriteDrawable homeDrawableUp = new SpriteDrawable(new Sprite(homeTextureUp));
        SpriteDrawable homeDrawableDown = new SpriteDrawable(new Sprite(homeTextureDown));
        homeDrawableUp.setMinHeight(Gdx.graphics.getWidth()/11);
        homeDrawableUp.setMinWidth(Gdx.graphics.getWidth()/11);
        homeDrawableDown.setMinHeight(Gdx.graphics.getWidth()/11);
        homeDrawableDown.setMinWidth(Gdx.graphics.getWidth()/11);
        homeButton = new ImageButton(homeDrawableUp, homeDrawableDown, homeDrawableDown);
        homeButton.setPosition(29*Gdx.graphics.getWidth()/34, Gdx.graphics.getWidth()/17);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        homeButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(game.welcome); //return to the home screen
            }
        });

        stage.addActor(homeButton);

        modeButtons = new ButtonGroup();
        modeButtons.add(swipeButton);
        modeButtons.add(tiltButton);
        modeButtons.setMaxCheckCount(1); //only one difficulty choice can be selected ata  time

        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);
        stage.addActor(swipeButton);
        stage.addActor(tiltButton);
        stage.addActor(muteMusicButton);
        stage.addActor(muteSoundsButton);


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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawBatch();
        stage.act();
        stage.draw();

        //updating & input detection

    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        easyButton.setTouchable(Touchable.disabled);
        mediumButton.setTouchable(Touchable.disabled);
        hardButton.setTouchable(Touchable.disabled);
        swipeButton.setTouchable(Touchable.disabled);
        tiltButton.setTouchable(Touchable.disabled);
        muteMusicButton.setTouchable(Touchable.disabled);
        muteSoundsButton.setTouchable(Touchable.disabled);
        homeButton.setTouchable(Touchable.disabled);
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
