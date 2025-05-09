package karenchrislishi.uf3.m8;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import karenchrislishi.uf3.m8.helpers.AssetManager;
import karenchrislishi.uf3.m8.screens.InitialScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SoundSequence extends Game {
    @Override
    public void create() {
        Gdx.app.log("LifeCycle", "create()");
        AssetManager.load();
        setScreen(new InitialScreen(this));
    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        Gdx.app.log("LifeCycle", "resize(" + Integer.toString(width) + ", " +
            Integer.toString(height) + ")");
    }
    @Override
    public void render() {
        super.render();
    }
    @Override
    public void pause() {
        super.pause();
        Gdx.app.log("LifeCycle", "pause()");
    }
    @Override
    public void resume() {
        super.resume();
        Gdx.app.log("LifeCycle", "resume()");
    }
    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
        Gdx.app.log("LifeCycle", "dispose()");
    }
}
