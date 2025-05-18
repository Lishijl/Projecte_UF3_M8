    package karenchrislishi.uf3.m8.helpers;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.Input;
    import com.badlogic.gdx.InputProcessor;
    import com.badlogic.gdx.math.Vector2;
    import com.badlogic.gdx.scenes.scene2d.Actor;
    import com.badlogic.gdx.scenes.scene2d.Stage;

    import karenchrislishi.uf3.m8.actors.Button;
    import karenchrislishi.uf3.m8.screens.GameScreen;

    public class InputHandler implements InputProcessor {

        private final Button[] btnsOn;
        private final Button[] btnsOff;
        private final GameScreen screen;
        private final Stage stage;
        private Vector2 coor;

        public InputHandler(GameScreen screen) {
            this.screen = screen;
            this.stage = screen.getStage();
            this.btnsOn = screen.getBtnsOn();
            this.btnsOff = screen.getBtnsOff();
        }
        @Override
        public boolean keyDown(int keycode) {
            switch (screen.getEstatJoc()) {
                case RUNNING:
                    int clicIndex = -1;
                    switch (keycode) {
                        case Input.Keys.LEFT:
                            clicIndex = 1;
                            break;
                        case Input.Keys.UP:
                            clicIndex = 3;
                            break;
                        case Input.Keys.DOWN:
                            clicIndex = 0;
                            break;
                        case Input.Keys.RIGHT:
                            clicIndex = 2;
                            break;
                    }
                    if (clicIndex != -1) {
                        handleInput(clicIndex);
                    }
                    break;
                case GAMEOVER:
                    screen.reset();
                    AssetManager.musica.stop();
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
        private void handleInput(int clicIndex) {
            if (!screen.isWaitUser()) return;
            // Haz que el botón parpadee
            btnsOn[clicIndex].blink(0.1f);
            // quan es toca un dinamic, verifica index
            int[] patro = screen.getPatronOri();
            int index = screen.getIndexActual();
            // en el for si i actual coincideix amb el que hi ha a patro sequencialment
            if (clicIndex == patro[index]) {
                // pasa al seguent patró
                screen.setIndexActual(index + 1);
                // comprobar si no estoy al final del patron
                if (patro.length == screen.getIndexActual()) {
                    // guanya nivell
                    screen.nextLvl();
                }
            } else {
                screen.setEstatJoc(GameScreen.GameState.GAMEOVER);
                AssetManager.musica.play();
                screen.setWaitUser(false);
            }
            Gdx.app.log("GameScreen", "Patrón introducido: " + clicIndex);
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            coor = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
            switch (screen.getEstatJoc()) {
                case RUNNING:
                    if (!screen.isWaitUser()) return false;
                    for (int i = 0; i < btnsOn.length; i++) {
                        // si algun dels botons on coincideix en el tocat
                        if (btnsOn[i].getRectArea().contains(coor)) {
                            handleInput(i);
                            break;
                        }
                    }
                    break;
                case GAMEOVER:
                    screen.reset();
                    AssetManager.musica.stop();
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
