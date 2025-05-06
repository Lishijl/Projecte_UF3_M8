package karenchrislishi.uf3.m8.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

    // Textures de fons
    public static Texture initialBackground;

    // Musica
    public static Music musica;

    public static void load() {
        // Fons pantalla inicio
        initialBackground = new Texture(Gdx.files.internal("imatges/initialBackground.png"));

        // Música pantalla inicio
        musica = Gdx.audio.newMusic(Gdx.files.internal("sounds/musica_fondo.mp3"));
        musica.setLooping(true);
    }

    public static void dispose() {
        musica.dispose();
    }
}
