package karenchrislishi.uf3.m8.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import karenchrislishi.uf3.m8.SoundSequence;


public class GameOver implements Screen {


    private final SoundSequence sndSq;
    private Stage stage;
    private BitmapFont font;
    private int scoreReached;
    private com.badlogic.gdx.audio.Sound gameOverSound;


    public GameOver(SoundSequence sndSq) {
        this.sndSq = sndSq;
        this.scoreReached = scoreReached;


        // Cámara y viewport
        Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);


        // Cargar fuente por defecto
        font = new BitmapFont();


        // Cargar sonido de game over
        //gameOverSound = Gdx.audio.newSound(Gdx.files.internal("audio/game_over_sound.wav"));


        // Reproducir sonido al cargar la pantalla
        gameOverSound.play(1f); // Volumen 100%


        // Crear interfaz
        createUI();
    }


    private void createUI() {
        // Título
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.RED);
        Label titleLabel = new Label("GAME OVER", labelStyle);
        titleLabel.setFontScale(2.5f);


        // Nivel alcanzado
        Label levelLabel = new Label("Llegaste hasta el nivel: " + scoreReached, labelStyle);
        levelLabel.setFontScale(1.5f);


        // Botones
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = com.badlogic.gdx.graphics.Color.WHITE;
        buttonStyle.up = new NinePatchDrawable(new com.badlogic.gdx.graphics.g2d.NinePatch(
            new com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion(
                new com.badlogic.gdx.graphics.Texture("ui/button_up.png"), 0, 0, 100, 50), 1, 1, 1, 1));


        buttonStyle.down = new NinePatchDrawable(new com.badlogic.gdx.graphics.g2d.NinePatch(
            new com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion(
                new com.badlogic.gdx.graphics.Texture("ui/button_down.png"), 0, 0, 100, 50), 1, 1, 1, 1));


        TextButton retryButton = new TextButton("Reintentar", buttonStyle);
        TextButton menuButton = new TextButton("Menú Principal", buttonStyle);
        TextButton exitButton = new TextButton("Salir", buttonStyle);


        // Acciones de los botones
        //retryButton.addListener(new ChangeListener() {
        //@Override
        //public void changed(ChangeEvent event, Actor actor) {
        // Aquí iría la pantalla de juego real cuando la tengas hecha
        //game.setScreen(new GameScreen(game));
        //}
        //});


        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sndSq.setScreen(new InitialScreen(sndSq));
            }
        });


        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });


        // Tabla para centrar todo
        Table table = new Table();
        table.setFillParent(true);
        table.center();


        table.add(titleLabel).padBottom(30).row();
        table.add(levelLabel).padBottom(50).row();
        table.add(retryButton).width(400).height(80).padBottom(20).row();
        table.add(menuButton).width(400).height(80).padBottom(20).row();
        table.add(exitButton).width(400).height(80).padBottom(20).row();


        stage.addActor(table);
    }


    @Override
    public void show() {}


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.6f); // Fondo semi-transparente
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(delta);
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }


    @Override
    public void pause() {}


    @Override
    public void resume() {}


    @Override
    public void hide() {}


    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
        gameOverSound.dispose();
    }
}

