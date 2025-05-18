package karenchrislishi.uf3.m8.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import karenchrislishi.uf3.m8.SoundSequence;
import karenchrislishi.uf3.m8.actors.Button;
import karenchrislishi.uf3.m8.helpers.AssetManager;
import karenchrislishi.uf3.m8.helpers.InputHandler;

public class GameScreen implements Screen {
    private Stage stage;
    private SoundSequence sndSq;
    private final String jugador;
    private Button[] btnsOn;
    private Button[] btnsOff;
    private Batch batch;
    private GlyphLayout scoreTxt;
    private GameState statJoc;
    private float with, height;
    private int lvl;
    private int[] patronOri;
    private int indexActual;
    private boolean waitUser;
    public enum GameState {
        RUNNING, GAMEOVER
    }

    public GameScreen(Batch batch, Viewport vp, String jugador) {
        Gdx.app.log("GameScreen", "Creando GameScreen");
        this.jugador = jugador;
        OrthographicCamera cam = (OrthographicCamera) vp.getCamera();
        cam.setToOrtho(true);
        vp.setCamera(cam);
        this.stage = new Stage(vp, batch);
        this.batch = stage.getBatch();
        this.with = stage.getWidth();
        this.height = stage.getHeight();
        // x,y + tamanys
        btnsOn = new Button[4];
        btnsOff = new Button[4];
        btnsOn[0] = new Button(with/4.7f, height/3.4f, AssetManager.btn1purpleOn, AssetManager.snd1);
        btnsOff[0] = new Button(with/4.7f, height/3.4f, AssetManager.btn1purpleOff, null);
        btnsOn[1] = new Button(with/2.35f, height/15, AssetManager.btn2greenOn, AssetManager.snd2);
        btnsOff[1] = new Button(with/2.35f, height/15, AssetManager.btn2greenOff, null);
        btnsOn[2] = new Button(with/2.35f, (height*5-height)/7.5f, AssetManager.btn3blueOn, AssetManager.snd3);
        btnsOff[2] = new Button(with/2.35f, (height*5-height)/7.5f, AssetManager.btn3blueOff, null);
        btnsOn[3] = new Button((with*4.35f-with)/5.25f, height/3.4f, AssetManager.btn4yellowOn, AssetManager.snd4);
        btnsOff[3] = new Button((with*4.35f-with)/5.25f, height/3.4f, AssetManager.btn4yellowOff, null);

        // statics, botons on dinamics en el batch
        for (Button btn : btnsOff) { stage.addActor(btn); }
        for (Button btn : btnsOn) {
            btn.setVisible(false);
            stage.addActor(btn);
        }
        // noms per botons dinàmics
        btnsOn[0].setName("pOn");
        btnsOn[1].setName("gOn");
        btnsOn[2].setName("bOn");
        btnsOn[3].setName("yOn");
        scoreTxt = new GlyphLayout();
        lvl = 1;
        scoreTxt.setText(AssetManager.font, "SCORE: " + lvl);
        Gdx.input.setInputProcessor(new InputHandler(this));
        statJoc = GameState.RUNNING;
        waitUser = false;
    }
    @Override
    public void show() {
        System.out.println("Bienvenido: " + jugador);

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(delta);
        stage.draw();

        switch (statJoc) {
            case RUNNING: running(delta); break;
            case GAMEOVER: gameOver(delta); break;
        }
    }
    // desenvolupament de la jugabilitat
    public void running(float delta) {
        batch.begin();
        AssetManager.font.draw(batch, scoreTxt, with/1.25f, height/1.25f);
        batch.end();
        if (!waitUser) {
            patroNivell(lvl);
            playPatro(delta);
            waitUser = true;
        }
    }
    public void patroNivell(int lvl) {
        patronOri = new int[this.lvl];
        for (int i = 0; i < patronOri.length; i++) {
            patronOri[i] = (int) (Math.random() * 4);
        }
    }
    public void playPatro(float delta) {
        stage.act(delta);
        SequenceAction sqAct = new SequenceAction();
        sqAct.addAction(Actions.delay(1f));
        StringBuilder log = new StringBuilder("Patrón generado: ");
        for (int i = 0; i < patronOri.length; i++) {
            int indexP = patronOri[i];
            final Button btn = btnsOn[indexP];
            log.append(patronOri[i]).append(" ");
            sqAct.addAction(Actions.run(() -> btn.blink(0.4f)));
            sqAct.addAction(Actions.delay(0.6f));
        }
        Gdx.app.log("GameScreen", log.toString());
        sqAct.addAction(Actions.run(() -> {
            waitUser = true;
            indexActual = 0;
        }));
        stage.addAction(sqAct);
    }
    public void nextLvl() {
        lvl++;
        scoreTxt.setText(AssetManager.font, "SCORE: " + lvl);
        waitUser = false;
        // reinicio indexPatro
        indexActual = 0;
        patronOri = null;
    }
    // reinicia partida
    public void gameOver(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(delta);
        batch.begin();
        String record = "Jugador: " + jugador + " - LVL: " + lvl;
        GlyphLayout layout = new GlyphLayout(AssetManager.font, record);
        float x = (with - layout.width) / 2;
        AssetManager.font.draw(batch, "GAME OVER", with / 2.6f, height / 3.5f);
        AssetManager.font.draw(batch, record, x, height / 2f);
        AssetManager.font.draw(batch, "Haz click para reiniciar", with / 3.4f, height / 1.5f);
        batch.end();
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
    public boolean isWaitUser() { return waitUser; }
    public int[] getPatronOri() { return patronOri; }
    public int getIndexActual() { return indexActual; }
    public void setIndexActual(int ind) { this.indexActual = ind; }
    public void setWaitUser(boolean waiting) { this.waitUser = waiting; }
}
