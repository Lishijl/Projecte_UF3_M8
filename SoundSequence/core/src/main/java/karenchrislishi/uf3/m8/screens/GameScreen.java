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
    private Button[] btnsOn;
    private Button[] btnsOff;
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
        this.stage = new Stage(vp, batch);
        this.batch = stage.getBatch();
        this.with = stage.getWidth();
        this.height = stage.getHeight();
        // x,y + tamanys
        btnsOn = new Button[4];
        btnsOff = new Button[4];
        btnsOn[0] = new Button(with/5, height/2,20, 20, AssetManager.btn1purpleOn, AssetManager.snd1);
        btnsOff[0] = new Button(with/5, height/2,20, 20, AssetManager.btn1purpleOff, null);
        btnsOn[1] = new Button(with/2, height/5, 20, 20, AssetManager.btn2greenOn, AssetManager.snd2);
        btnsOff[1] = new Button(with/2, height/5, 20, 20, AssetManager.btn2greenOff, null);
        btnsOn[2] = new Button(with/2, (height*5-height)/5, 20, 20, AssetManager.btn3blueOn, AssetManager.snd3);
        btnsOff[2] = new Button(with/2, (height*5-height)/5, 20, 20, AssetManager.btn3blueOff, null);
        btnsOn[3] = new Button((with*5-with)/5, height/2,20, 20, AssetManager.btn4yellowOn, AssetManager.snd4);
        btnsOff[3] = new Button((with*5-with)/5, height/2,20, 20, AssetManager.btn4yellowOff, null);
        /*
        btn1on = new Button(with/5, height/2,20, 20);
        btn1off = new Button(with/5, height/2,20, 20);
        btn2on = new Button(with/2, height/5, 20, 20);
        btn2off = new Button(with/2, height/5, 20, 20);
        btn3on = new Button(with/2, (height*5-height)/5, 20, 20);
        btn3off = new Button(with/2, (height*5-height)/5, 20, 20);
        btn4on = new Button((with*5-with)/5, height/2,20, 20);
        btn4off = new Button((with*5-with)/5, height/2,20, 20);
        stage.addActor(btnsOff[0]);
        stage.addActor(btnsOff[1]);
        stage.addActor(btnsOff[2]);
        stage.addActor(btnsOff[3]);
        */
        // statics, botons on dinamics en el batch
        for (Button btn : btnsOff) { stage.addActor(btn); }
        // noms per botons dinàmics
        btnsOn[0].setName("pOn");
        btnsOn[1].setName("gOn");
        btnsOn[2].setName("bOn");
        btnsOn[3].setName("yOn");
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
        switch (statJoc) {
            case RUNNING: running(delta); break;
            case GAMEOVER: gameOver(delta); break;
        }
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
        lvl = 1;
        scoreTxt.setText(AssetManager.font, "Score: " + lvl);
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
    public Button[] getBtnsOn() { return btnsOn; }
    public Button[] getBtnsOff() { return btnsOff; }
    /*
    public Button getBtn1on() { return btn1on; }
    public Button getBtn2on() { return btn2on; }
    public Button getBtn3on() { return btn3on; }
    public Button getBtn4on() { return btn4on; }
    public Button getBtn1off() { return btn1off; }
    public Button getBtn2off() { return btn2off; }
    public Button getBtn3off() { return btn3off; }
    public Button getBtn4off() { return btn4off; }
    */
}
