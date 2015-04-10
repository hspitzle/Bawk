package com.eecs493.bawk;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

//import java.awt.Rectangle;

/**
 * Created by Hayden on 3/20/2015.
 */
public class HowTo implements Screen {

    private BawkGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture backgroundImage;
    private Rectangle background;

    private Stage stage;
    //the home button
    private ImageButton homeButton;

    public HowTo(BawkGame game_){
        game = game_;
    }

    @Override
    public void show()
    {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        System.out.println("Show");

        backgroundImage = new Texture("howtoscreen.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());
        batch = new SpriteBatch();

        background = new Rectangle(0, 0, game.getWidth(), game.getHeight());

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
        //if(Gdx.input.isTouched())
          //  game.setScreen(game.welcome);
        //updating & input detection
        System.out.println("made it to render");
        stage.act();
        stage.draw();

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
