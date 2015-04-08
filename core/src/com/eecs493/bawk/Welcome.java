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

//import java.awt.Rectangle;

/**
 * Created by Hayden on 3/20/2015.
 */
public class Welcome implements Screen {

    private BawkGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture backgroundImage;
    private Rectangle background;
    private ImageButton howToButton;
    private ImageButton playButton;
    private ImageButton settingsButton;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private BitmapFont font;
    private TextButton.TextButtonStyle textButtonStyle;
    private Stage stage;

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

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());
        backgroundImage = new Texture("titlescreen.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        createBasicSkin();
        System.out.println("Show");

        Texture playTextureUp = new Texture("play.png");
        Texture playTextureDown = new Texture("playpressed.png");
        SpriteDrawable playDrawableUp = new SpriteDrawable(new Sprite(playTextureUp));
        SpriteDrawable playDrawableDown = new SpriteDrawable(new Sprite(playTextureDown));
        playDrawableUp.setMinHeight(Gdx.graphics.getWidth()/4);
        playDrawableUp.setMinWidth(2*Gdx.graphics.getWidth()/4);
        playDrawableDown.setMinHeight(Gdx.graphics.getWidth()/4);
        playDrawableDown.setMinWidth(2*Gdx.graphics.getWidth()/4);
        playButton = new ImageButton(playDrawableUp, playDrawableDown);
       // playButton.setSize(playButton.getWidth() * 2, playButton.getHeight() * 2);
        //playButton = new TextButton("Play!", skin); // Use the initialized skin
        playButton.setPosition(Gdx.graphics.getWidth()/ 5 + Gdx.graphics.getHeight()/32, Gdx.graphics.getWidth()/16 + Gdx.graphics.getHeight()/7);
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
        howToButton.setPosition(Gdx.graphics.getWidth()/2 + 5, Gdx.graphics.getHeight()/7 -Gdx.graphics.getWidth()/7);
        //howToButton.setWidth(2 * Gdx.graphics.getWidth()/3);

        howToButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {

                //backgroundImage = new Texture ("howtoscreen.png");
               // playButton.setTouchable(Touchable.disabled);
               // howToButton.setTouchable(Touchable.disabled);
               // settingsButton.setTouchable(Touchable.disabled);
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
        settingsButton.setPosition(Gdx.graphics.getWidth()/4 + 50, Gdx.graphics.getHeight()/7- Gdx.graphics.getWidth()/7);
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
