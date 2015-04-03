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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;

import javafx.scene.control.RadioButton;

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
    ButtonGroup difficultyButtons;


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
        easyDrawableUp.setMinHeight(Gdx.graphics.getWidth()/7);
        easyDrawableUp.setMinWidth(2*Gdx.graphics.getWidth()/7);
        easyDrawableDown.setMinHeight(Gdx.graphics.getWidth()/7);
        easyDrawableDown.setMinWidth(2*Gdx.graphics.getWidth()/7);
        easyButton = new ImageButton(easyDrawableUp, easyDrawableDown, easyDrawableDown);
        easyButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/11, Gdx.graphics.getWidth()/7 + Gdx.graphics.getHeight()/2);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        easyButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);

                //game.setScreen(game.play);
            }
        });

        //medium
        Texture mediumTextureUp = new Texture("medium.png");
        Texture mediumTextureDown = new Texture("medium2.png");
        SpriteDrawable mediumDrawableUp = new SpriteDrawable(new Sprite(mediumTextureUp));
        SpriteDrawable mediumDrawableDown = new SpriteDrawable(new Sprite(mediumTextureDown));
        mediumDrawableUp.setMinHeight(Gdx.graphics.getWidth()/7);
        mediumDrawableUp.setMinWidth(2*Gdx.graphics.getWidth()/7);
        mediumDrawableDown.setMinHeight(Gdx.graphics.getWidth()/7);
        mediumDrawableDown.setMinWidth(2*Gdx.graphics.getWidth()/7);
        mediumButton = new ImageButton(mediumDrawableUp, mediumDrawableDown, mediumDrawableDown);
        mediumButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/11, Gdx.graphics.getWidth()/2 + Gdx.graphics.getHeight()/5);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        mediumButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);

                //game.setScreen(game.play);
            }
        });

        //hard
        Texture hardTextureUp = new Texture("hard.png");
        Texture hardTextureDown = new Texture("hard2.png");
        SpriteDrawable hardDrawableUp = new SpriteDrawable(new Sprite(hardTextureUp));
        SpriteDrawable hardDrawableDown = new SpriteDrawable(new Sprite(hardTextureDown));
        hardDrawableUp.setMinHeight(Gdx.graphics.getWidth()/7);
        hardDrawableUp.setMinWidth(2*Gdx.graphics.getWidth()/7);
        hardDrawableDown.setMinHeight(Gdx.graphics.getWidth()/7);
        hardDrawableDown.setMinWidth(2*Gdx.graphics.getWidth()/7);
        hardButton = new ImageButton(hardDrawableUp, hardDrawableDown, hardDrawableDown);
        hardButton.setPosition(Gdx.graphics.getWidth()/5 + Gdx.graphics.getHeight()/11 , Gdx.graphics.getWidth()/2 + Gdx.graphics.getHeight()/10);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        hardButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);
                //game.setScreen(game.play);
            }
        });

        difficultyButtons = new ButtonGroup();
        difficultyButtons.add(easyButton);
        difficultyButtons.add(mediumButton);
        difficultyButtons.add(hardButton);
        difficultyButtons.setMaxCheckCount(1); //only one difficulty choice can be selected ata  time


        stage.addActor(easyButton);
        stage.addActor(mediumButton);
        stage.addActor(hardButton);


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
