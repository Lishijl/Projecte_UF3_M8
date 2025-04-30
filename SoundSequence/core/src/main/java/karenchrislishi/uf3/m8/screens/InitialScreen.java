package karenchrislishi.uf3.m8.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import karenchrislishi.uf3.m8.helpers.AssetManager;

public class InitialScreen implements Screen {
    private final Game game;
    private final Stage stage;
    private final Batch batch;
    private final OrthographicCamera camera;

    private Skin skin;

    private Texture background;

    private TextField textField;
    private TextButton startButton;

    public InitialScreen(Game game) {
        this.game = game;

        background = AssetManager.initialBackground;

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(width,height);
        camera.setToOrtho(false);
        StretchViewport viewport = new StretchViewport(width, height, camera);

        stage = new Stage(viewport);
        batch = stage.getBatch();

        Gdx.input.setInputProcessor(stage);

        // Cargamos el skin para los elementos UI
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        skin.getFont("default-font").getData().setScale(3.5f);

        // Campo de texto
        textField = new TextField("", skin);
        textField.setMessageText("Introduce tu nombre");
        textField.setHeight(150);
        textField.setWidth(1100);
        textField.setAlignment(Align.center);

        // Elegimos el estilo que tendrá el botón + texto
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.newDrawable("default-round", Color.WHITE);
        buttonStyle.font = skin.getFont("default-font");
        buttonStyle.fontColor = com.badlogic.gdx.graphics.Color.BLACK;

        // Ponemos el texto al botón
        startButton = new TextButton("START", buttonStyle);
        startButton.setSize(550, 180);

        // Botón START: redirigirá al GameScreen y pasará el nombre del usuario
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String nombre = textField.getText().trim();
                if (!nombre.isEmpty()) {
                    game.setScreen(new GameScreen(game, nombre));
                }
            }
        });

        // Añadimos los elementos a la stage
        stage.addActor(textField);
        stage.addActor(startButton);

        // Posicionamos bien los elementos
        resize(width, height);

        // Se activa la música
        AssetManager.musica.play();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Actualiza el viewport con las nuevas dimensiones y centra la cámara
        stage.getViewport().update(width, height, true);

        // obtiene la anchura y la altura del stage
        float stageWidth = stage.getViewport().getWorldWidth();
        float stageHeight = stage.getViewport().getWorldHeight();

        // Se centran los elementos y se ajusta un poco la altura
        textField.setPosition((stageWidth - textField.getWidth()) / 2, (stageHeight * 0.6f) - 70);
        startButton.setPosition((stageWidth - startButton.getWidth()) / 2, (stageHeight * 0.45f) - 220);
    }


    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
