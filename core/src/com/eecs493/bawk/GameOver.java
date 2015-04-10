package com.eecs493.bawk;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
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

    Texture boomImage;
    int COLS = 8;
    int ROWS = 8;
    Animation boom;
    Array<TextureRegion> boomRegion;

    Array<Explosion> explosions;

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
        if (game.soundEffectsOnFlag)
            thunder.play();

        displayTime = 2000;
        startTime = TimeUtils.millis();

        boomImage = new Texture("smoke.png");
        TextureRegion[][] tmp = TextureRegion.split(boomImage, boomImage.getWidth() / COLS, boomImage.getHeight() / ROWS );
        boomRegion = new Array<TextureRegion>();

        int index = 0;
        for(int i = 0; i < ROWS; ++i)
            for(int j = 0; j < COLS; ++j)
                boomRegion.add(tmp[i][j]);

        boom = new Animation(.075f,boomRegion);

        explosions = new Array<Explosion>();

        explosions.add(new Explosion(400, 400));
        explosions.add(new Explosion(50, 450));
        explosions.add(new Explosion(250, 250));
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

        for(Explosion exp : explosions){
            exp.statetime += Gdx.graphics.getDeltaTime();
            if(boom.isAnimationFinished(exp.statetime)){
                explosions.removeValue(exp, false);
            }
            else{
                exp.currframe = boom.getKeyFrame(exp.statetime, false);
                batch.draw(exp.currframe, exp.x, exp.y);
            }
        }

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
