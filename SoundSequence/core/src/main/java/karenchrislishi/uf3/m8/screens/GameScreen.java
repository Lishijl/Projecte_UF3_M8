package karenchrislishi.uf3.m8.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    private final Game game;
    private final String jugador;

    public GameScreen(Game game, String jugador) {
        this.game = game;
        this.jugador = jugador;
    }
    @Override
    public void show() {
        System.out.println("Bienvenido: " + jugador);

    }

    @Override
    public void render(float delta) {}

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
}
