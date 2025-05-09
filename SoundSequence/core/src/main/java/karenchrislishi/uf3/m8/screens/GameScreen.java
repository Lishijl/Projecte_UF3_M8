package karenchrislishi.uf3.m8.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import karenchrislishi.uf3.m8.SoundSequence;
import karenchrislishi.uf3.m8.actors.Button;
import karenchrislishi.uf3.m8.helpers.AssetManager;
import karenchrislishi.uf3.m8.helpers.InputHandler;

public class GameScreen implements Screen {
    private Stage stage;
    private SoundSequence sndSq;
    private final String jugador;
    private Button btn1on, btn2on, btn3on, btn4on, btn1off, btn2off, btn3off, btn4off;
    private Batch batch;
    private GlyphLayout scoreTxt;
    private GameState statJoc;
    private float with, height;
    private int lvl;
    public enum GameState {
        RUNNING, GAMEOVER
    }

    public GameScreen(Batch batch, Viewport vp, String jugador) {
        Gdx.app.log("GameScreen", "Creando GameScreen");
        this.jugador = jugador;
        stage = new Stage(vp, batch);
        batch = stage.getBatch();
        with = stage.getWidth();
        height = stage.getHeight();
        // x,y + tamanys
        btn1on = new Button(with/5, height/2,20, 20);
        btn1off = new Button(with/5, height/2,20, 20);
        btn2on = new Button(with/2, height/5, 20, 20);
        btn2off = new Button(with/2, height/5, 20, 20);
        btn3on = new Button(with/2, (height*5-height)/5, 20, 20);
        btn3off = new Button(with/2, (height*5-height)/5, 20, 20);
        btn4on = new Button((with*5-with)/5, height/2,20, 20);
        btn4off = new Button((with*5-with)/5, height/2,20, 20);
        // statics, botons on dinamics en el batch
        stage.addActor(btn1off);
        stage.addActor(btn2off);
        stage.addActor(btn3off);
        stage.addActor(btn4off);
        // noms per botons dinàmics
        btn1on.setName("pOn");
        btn2on.setName("gOn");
        btn3on.setName("bOn");
        btn4on.setName("yOn");
        scoreTxt = new GlyphLayout();
        lvl = 1;
        scoreTxt.setText(AssetManager.font, "Score " + lvl);
        Gdx.input.setInputProcessor(new InputHandler(this));
        statJoc = GameState.RUNNING;
    }
    @Override
    public void show() {
        System.out.println("Bienvenido: " + jugador);

    }
    @Override
    public void render(float delta) {
        batch.begin();
        AssetManager.font.draw(batch, scoreTxt, with/1.25f, height/1.25f);
        batch.end();
    }
    // desenvolupament de la jugabilitat
    public void running(float delta) {
        stage.act(delta);
    }
    // reinicia partida
    public void gameOver(float delta) {
        stage.act(delta);
        // pantalla gameover... reinicia el joc o pantalla d'inici?
        // TO-DO
        lvl = 1;
    }
    public void reset() {
        statJoc = GameState.RUNNING;
        // reiniciar Score corresponent al nivell 0
    }
    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
    public Stage getStage() { return stage; }
    public GameState getEstatJoc() { return statJoc; }
    public void setEstatJoc(GameState estatActual) { this.statJoc = estatActual; }
}
