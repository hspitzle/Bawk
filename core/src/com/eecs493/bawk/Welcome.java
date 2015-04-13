package com.eecs493.bawk;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

//import java.awt.Rectangle;

/**
 * Created by Hayden on 3/20/2015.
 */
public class Welcome implements Screen {

    private BawkGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont authorFont;

    private Texture backgroundImage;
    private Rectangle background;
    private ImageButton howToButton;
    private ImageButton playButton;
    private ImageButton settingsButton;

    private ImageButton haydenEgg;
    private ImageButton danielEgg;
    private ImageButton aliEgg;
    private ImageButton katieEgg;
    private ImageButton mattEgg;

    private Skin skin;
    private TextureAtlas buttonAtlas;
    private BitmapFont font;
    private TextButton.TextButtonStyle textButtonStyle;
    private Stage stage;

    private Array<Author> authors;

    public Welcome(BawkGame game_){
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
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());
        batch = new SpriteBatch();

        authorFont = new BitmapFont();
        authorFont.setColor(Color.WHITE);

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());
        backgroundImage = new Texture("titlescreen.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        createBasicSkin();

        addButtons();

        authors = new Array<Author>();
        addAuthorEggs();

        if(game.musicOnFlag) game.music.play();
    }

    private void addAuthorEggs(){
        //hayden: red
        Texture textureUp = new Texture("red_egg.png");
        SpriteDrawable drawableUp = new SpriteDrawable(new Sprite(textureUp));
        drawableUp.setMinWidth(0.75f * game.scaledX(textureUp.getWidth()));
        drawableUp.setMinHeight(0.75f * game.scaledY(textureUp.getHeight()));
        haydenEgg = new ImageButton(drawableUp, drawableUp);
        haydenEgg.setPosition(game.scaledX(65), game.scaledY(188));
        haydenEgg.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                authors.add(new Author("Hayden Spitzley", TimeUtils.millis(),
                        game.unscaledX((int)haydenEgg.getX()),
                        game.unscaledY((int)haydenEgg.getY()), false));
            }
        });
        stage.addActor(haydenEgg);

        //matt: yellow
        textureUp = new Texture("egg_yellow.png");
        drawableUp = new SpriteDrawable(new Sprite(textureUp));
        drawableUp.setMinWidth(0.75f * game.scaledX(textureUp.getWidth()));
        drawableUp.setMinHeight(0.75f * game.scaledY(textureUp.getHeight()));
        mattEgg = new ImageButton(drawableUp, drawableUp);
        mattEgg.setPosition(game.scaledX(395), game.scaledY(220));
        mattEgg.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                authors.add(new Author("Matt Cloutier", TimeUtils.millis(),
                        game.unscaledX((int)mattEgg.getX()),
                        game.unscaledY((int)mattEgg.getY()), true));
            }
        });
        stage.addActor(mattEgg);

        //katie: purple
        textureUp = new Texture("egg_purple.png");
        drawableUp = new SpriteDrawable(new Sprite(textureUp));
        drawableUp.setMinWidth(0.75f * game.scaledX(textureUp.getWidth()));
        drawableUp.setMinHeight(0.75f * game.scaledY(textureUp.getHeight()));
        katieEgg = new ImageButton(drawableUp, drawableUp);
        katieEgg.setPosition(game.scaledX(385), game.scaledY(25));
        katieEgg.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                authors.add(new Author("Kaitlyn Frank", TimeUtils.millis(),
                        game.unscaledX((int)katieEgg.getX()),
                        game.unscaledY((int)katieEgg.getY()), true));
            }
        });
        stage.addActor(katieEgg);

        //ali: blue
        textureUp = new Texture("egg_blue.png");
        drawableUp = new SpriteDrawable(new Sprite(textureUp));
        drawableUp.setMinWidth(0.75f * game.scaledX(textureUp.getWidth()));
        drawableUp.setMinHeight(0.75f * game.scaledY(textureUp.getHeight()));
        aliEgg = new ImageButton(drawableUp, drawableUp);
        aliEgg.setPosition(game.scaledX(152), game.scaledY(372));
        aliEgg.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                authors.add(new Author(" Ali Abdulhamid", TimeUtils.millis(),
                        game.unscaledX((int)aliEgg.getX()),
                        game.unscaledY((int)aliEgg.getY()), false));
            }
        });
        stage.addActor(aliEgg);

        //daniel: green
        textureUp = new Texture("egg_green.png");
        drawableUp = new SpriteDrawable(new Sprite(textureUp));
        drawableUp.setMinWidth(0.75f * game.scaledX(textureUp.getWidth()));
        drawableUp.setMinHeight(0.75f * game.scaledY(textureUp.getHeight()));
        danielEgg = new ImageButton(drawableUp, drawableUp);
        danielEgg.setPosition(game.scaledX(45), game.scaledY(320));
        danielEgg.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                authors.add(new Author("Daniel Miller", TimeUtils.millis(),
                        game.unscaledX((int)danielEgg.getX()),
                        game.unscaledY((int)danielEgg.getY()), false));
            }
        });
        stage.addActor(danielEgg);
    }

    private void addButtons(){
        Texture playTextureUp = new Texture("play.png");
        Texture playTextureDown = new Texture("playpressed.png");
        SpriteDrawable playDrawableUp = new SpriteDrawable(new Sprite(playTextureUp));
        SpriteDrawable playDrawableDown = new SpriteDrawable(new Sprite(playTextureDown));
        playDrawableUp.setMinHeight(1.5f * game.scaledY(playTextureUp.getHeight()));
        playDrawableUp.setMinWidth(1.5f * game.scaledX(playTextureUp.getWidth()));
        playDrawableDown.setMinHeight(1.5f * game.scaledY(playTextureDown.getHeight()));
        playDrawableDown.setMinWidth(1.5f * game.scaledX(playTextureDown.getWidth()));
        playButton = new ImageButton(playDrawableUp, playDrawableDown);
        // playButton.setSize(playButton.getWidth() * 2, playButton.getHeight() * 2);
        //playButton = new TextButton("Play!", skin); // Use the initialized skin

//        playButton.setPosition(Gdx.graphics.getWidth()/ 5 + Gdx.graphics.getHeight()/32, Gdx.graphics.getWidth()/16 + Gdx.graphics.getHeight()/7 + 70);
        playButton.setPosition(game.scaledX(game.getWidth()/2 - (int)(1.5f*playTextureUp.getWidth()/2)),
                Gdx.graphics.getWidth()/16 + Gdx.graphics.getHeight()/7 + 70);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //  playButton.setTouchable(Touchable.disabled);
                //  howToButton.setTouchable(Touchable.disabled);
                //  settingsButton.setTouchable(Touchable.disabled);
                game.music.stop();
                game.setScreen(game.play);
            }
        });
        Texture helpTextureUp = new Texture("help.png");
        Texture helpTextureDown = new Texture("helppressed.png");
        SpriteDrawable helpDrawableUp = new SpriteDrawable(new Sprite(helpTextureUp));
        SpriteDrawable helpDrawableDown = new SpriteDrawable(new Sprite(helpTextureDown));
        helpDrawableUp.setMinWidth(Gdx.graphics.getWidth()/5);
        helpDrawableUp.setMinHeight(Gdx.graphics.getWidth() / 5);
        helpDrawableDown.setMinWidth(Gdx.graphics.getWidth()/5);
        helpDrawableDown.setMinHeight(Gdx.graphics.getWidth()/5);
        howToButton = new ImageButton(helpDrawableUp, helpDrawableDown);
        //howToButton = new TextButton("Help!", skin); // Use the initialized skin
        howToButton.setPosition(Gdx.graphics.getWidth()/2 + 30, Gdx.graphics.getHeight()/7 -Gdx.graphics.getWidth()/7 + 60);
        //howToButton.setWidth(2 * Gdx.graphics.getWidth()/3);

        howToButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(game.howTo);
            }
        });

        Texture settingsTextureUp = new Texture("settings.png");
        Texture settingsTextureDown = new Texture("settingspressed.png");
        SpriteDrawable settingsDrawableUp = new SpriteDrawable(new Sprite(settingsTextureUp));
        SpriteDrawable settingsDrawableDown = new SpriteDrawable(new Sprite(settingsTextureDown));
        settingsDrawableUp.setMinWidth(Gdx.graphics.getWidth()/5);
        settingsDrawableUp.setMinHeight(Gdx.graphics.getWidth()/5);
        settingsDrawableDown.setMinWidth(Gdx.graphics.getWidth()/5);
        settingsDrawableDown.setMinHeight(Gdx.graphics.getWidth()/5);
        settingsButton = new ImageButton(settingsDrawableUp, settingsDrawableDown);
        //playButton = new TextButton("Play!", skin); // Use the initialized skin
        settingsButton.setPosition(Gdx.graphics.getWidth()/4 + 30, Gdx.graphics.getHeight()/7- Gdx.graphics.getWidth()/7 + 60);
        //playButton.setWidth(2 * Gdx.graphics.getWidth()/3);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(game.options);
            }
        });

        stage.addActor(howToButton);
        stage.addActor(playButton);
        stage.addActor(settingsButton);
    }

    private void drawBatch()
    {
        //Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.draw(backgroundImage, background.x, background.y);

//        for(Author author : authors){
//            if(TimeUtils.millis() > author.timeCreated + 3000)
//                authors.removeValue(author, false);
//            else {
//                author.draw(batch);
//                authorFont.draw(batch, author.name, author.x, author.y);
//            }
//        }

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

        batch.begin();
        for(Author author : authors){
            if(TimeUtils.millis() > author.timeCreated + 3000)
                authors.removeValue(author, false);
            else {
                author.draw(batch);
                authorFont.draw(batch, author.name, author.x, author.y);
            }
        }
        batch.end();
    }

    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        playButton.setTouchable(Touchable.disabled);
        howToButton.setTouchable(Touchable.disabled);
        settingsButton.setTouchable(Touchable.disabled);
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
    public void resume() {
        playButton.setTouchable(Touchable.enabled);
        howToButton.setTouchable(Touchable.enabled);
        settingsButton.setTouchable(Touchable.enabled);
    }

}
