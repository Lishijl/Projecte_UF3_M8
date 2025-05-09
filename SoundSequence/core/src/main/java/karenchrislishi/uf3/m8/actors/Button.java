package karenchrislishi.uf3.m8.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.awt.Rectangle;

public class Button extends Actor {
    private Vector2 position;
    private int width, height;
    private Rectangle rectArea;
    public Button(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
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
    public TextureRegion getButtonTexture() {
        // casos de colors
        return null; // TO-DO
    }
}
