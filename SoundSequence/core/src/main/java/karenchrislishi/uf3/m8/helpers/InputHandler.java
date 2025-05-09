package karenchrislishi.uf3.m8.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import karenchrislishi.uf3.m8.actors.Button;
import karenchrislishi.uf3.m8.screens.GameScreen;

public class InputHandler implements InputProcessor {

    private Button btn1on, btn2on, btn3on, btn4on;
    private GameScreen screen;
    private Stage stage;

    /* TO-DO tant el clicable per Device com per Keyboard
    * */
    public InputHandler(GameScreen screen) {
        this.screen = screen;
        // getters d'actors dinamics per implementar
        // btn1on = screen.getBtn();
        stage = screen.getStage();
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
