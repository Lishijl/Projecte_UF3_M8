package karenchrislishi.uf3.m8.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import karenchrislishi.uf3.m8.helpers.AssetManager;

public class Button extends Actor {
    private Vector2 position;
    private int width, height;
    private Rectangle rectArea;
    private Texture sprite;
    private Sound sound;
    private boolean active = true;

    public Button(float x, float y, int width, int height, Texture btnSprite, Sound btnSound) {
        this.width = width;
        this.height = height;
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
        setBounds(position.x, position.y, width, height);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getButtonTexture(), position.x, position.y, width, height);
    }
    public Texture getButtonTexture() {
        // casos de colors
        return sprite;
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
    public void blink(float duration) {
        this.turnOn();
        addAction(Actions.sequence(
            Actions.delay(duration),
            Actions.run(this::turnOff)
        ));
    }
}
