package karenchrislishi.uf3.m8.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import karenchrislishi.uf3.m8.actors.Button;
import karenchrislishi.uf3.m8.screens.GameScreen;

public class InputHandler implements InputProcessor {

    //private final Button btn1on, btn2on, btn3on, btn4on, btn1off, btn2off, btn3off, btn4off;
    private final Button[] btnsOn;
    private final Button[] btnsOff;
    private final GameScreen screen;
    private final Stage stage;
    private Vector2 stageCoord;

    /* TO-DO tant el clicable per Device com per Keyboard
    * */
    public InputHandler(GameScreen screen) {
        this.screen = screen;
        this.stage = screen.getStage();
        this.btnsOn = screen.getBtnsOn();
        this.btnsOff = screen.getBtnsOff();
        /*
        this.btn1on = screen.getBtn1on();
        this.btn2on = screen.getBtn2on();
        this.btn3on = screen.getBtn3on();
        this.btn4on = screen.getBtn4on();
        this.btn1off = screen.getBtn1off();
        this.btn2off = screen.getBtn2off();
        this.btn3off = screen.getBtn3off();
        this.btn4off = screen.getBtn4off();
         */
    }
    @Override
    public boolean keyDown(int keycode) {
        switch (screen.getEstatJoc()) {
            case RUNNING:
                switch (keycode) {
                    case Input.Keys.LEFT:
                        btnsOn[0].blink(1.0f);
                        break;
                    case Input.Keys.UP:
                        btnsOn[1].blink(1.0f);
                        break;
                    case Input.Keys.DOWN:
                        btnsOn[2].blink(1.0f);
                        break;
                    case Input.Keys.RIGHT:
                        btnsOn[3].blink(1.0f);
                        break;
                }
            case GAMEOVER:
                screen.reset();
                break;
        }
        return true;
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
        switch (screen.getEstatJoc()) {
            case RUNNING:
                stageCoord = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
                for (Actor act : stage.getActors()) {
                    if (act instanceof Button) {
                        Button btn = (Button) act;
                        if (btn.getRectArea().contains(stageCoord)) {
                            btn.blink(1.0f);
                            break;
                        }
                    }
                }
            case GAMEOVER:
                screen.reset();
                break;
        }
        return true;
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
