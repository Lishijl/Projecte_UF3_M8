package karenchrislishi.uf3.m8.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {

    // Textures de fons
    public static Texture initialBackground;

    // Musica
    public static Music musica;

    public static Texture btn1purpleOn, btn2greenOn, btn3blueOn, btn4yellowOn, btn1purpleOff, btn2greenOff, btn3blueOff, btn4yellowOff;
    public static Sound snd1, snd2, snd3, snd4;

    public static BitmapFont font;

    public static void load() {
        // Fons pantalla inicio
        initialBackground = new Texture(Gdx.files.internal("imatges/initialBackground.png"));

        // Música pantalla inicio
        musica = Gdx.audio.newMusic(Gdx.files.internal("sounds/musica_fondo.mp3"));
        musica.setLooping(true);

        btn1purpleOn = new Texture("imatges/purple_1_on.ong");
        btn2greenOn = new Texture("imatges/green_2_on.ong");
        btn3blueOn = new Texture("imatges/blue_3_on.ong");
        btn4yellowOn = new Texture("imatges/yellow_4_on.ong");
        btn1purpleOff = new Texture("imatges/purple_1_off.ong");
        btn2greenOff = new Texture("imatges/green_2_off.ong");
        btn3blueOff = new Texture("imatges/blue_3_off.ong");
        btn4yellowOff = new Texture("imatges/yellow_4_off.ong");

        snd1 = Gdx.audio.newSound(Gdx.files.internal("sounds/sound1_purple.mp3"));
        snd2 = Gdx.audio.newSound(Gdx.files.internal("sounds/sound2_green.mp3"));
        snd3 = Gdx.audio.newSound(Gdx.files.internal("sounds/sound3_blue.mp3"));
        snd4 = Gdx.audio.newSound(Gdx.files.internal("sounds/sound4_orange.mp3"));

        FileHandle fontFile = Gdx.files.internal("skin/default.fnt");
        font = new BitmapFont(fontFile, true);
        font.getData().setScale(0.6f);

    }

    public static void dispose() {
        musica.dispose();
        btn1purpleOn.dispose();
        btn1purpleOff.dispose();
        btn2greenOn.dispose();
        btn2greenOff.dispose();
        btn3blueOn.dispose();
        btn3blueOff.dispose();
        btn4yellowOn.dispose();
        btn4yellowOff.dispose();
        snd1.dispose();
        snd2.dispose();
        snd3.dispose();
        snd4.dispose();
    }
}
