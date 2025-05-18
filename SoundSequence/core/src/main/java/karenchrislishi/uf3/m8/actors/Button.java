package karenchrislishi.uf3.m8.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Button extends Actor {
    private Vector2 position;
    private float width, height;
    private Rectangle rectArea;
    private Texture sprite;
    private Sound sound;
    private boolean active = false;
    public Button(float x, float y, Texture btnSprite, Sound btnSound) {
        this.width = btnSprite.getWidth() * 3;
        this.height = btnSprite.getHeight() * 3;
        this.sprite = btnSprite;
        this.sound = btnSound;
        position = new Vector2(x, y);
        rectArea = new Rectangle();
        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);
    }
    // comportament del botó
    public void act(float delta) {
        super.act(delta);
        rectArea.set(position.x, position.y, width, height);
        setBounds(position.x, position.y, width, height);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (sprite != null) {
            batch.draw(sprite, position.x, position.y, width, height);
        }
    }
    public void playSound() { if (sound != null) sound.play(); }
    public void turnOn() {
        active = true;
        playSound();
    }
    public void turnOff() {
        active = false;
    }
    public Rectangle getRectArea() { return rectArea; }
    // animacio
    public void blink(float duration) {
        setVisible(true);
        this.turnOn();
        addAction(Actions.sequence(
            Actions.delay(duration),
            Actions.run(() -> {
                this.turnOff();
                setVisible(false);
            })
        ));
    }
}
