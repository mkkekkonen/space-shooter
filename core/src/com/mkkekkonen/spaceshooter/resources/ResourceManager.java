package com.mkkekkonen.spaceshooter.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mkkekkonen.spaceshooter.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;

@Singleton
public class ResourceManager {
    private Map<String, Texture> textures;
    private Map<String, Music> music;
    private Map<String, BitmapFont> fonts;

    private FreeTypeFontGenerator fontGenerator;

    @Inject
    ResourceManager() {
        this.loadResources();
    }

    public void dispose() {
        for (String key : this.textures.keySet()) {
            this.textures.get(key).dispose();
        }

        for (String key : this.music.keySet()) {
            this.music.get(key).dispose();
        }

        for (String key : this.fonts.keySet()) {
            this.fonts.get(key).dispose();
        }

        this.fontGenerator.dispose();
    }

    public void loadResources() {
        this.loadTextures();
        this.loadMusic();
        this.loadFonts();
    }

    private void loadTextures() {
        this.textures = new HashMap<>();
        this.textures.put("ship", this.loadTexture("ship.png"));
        this.textures.put("shootingBarComponent", this.loadTexture("shootingbar.png"));
        this.textures.put("bullet", this.loadTexture("bullet.png"));

        for (int i = 1; i <= 6; i++) {
            this.textures.put("roid" + i, this.loadTexture("roid" + i + ".png"));
        }

        for (int i = 1; i <= 6; i++) {
            this.textures.put("explosion" + i, this.loadTexture("explosion" + i + ".png"));
        }
    }

    private void loadMusic() {
        this.music = new HashMap<>();
        this.music.put("menu", this.loadMusicFile("menu.wav"));
        this.music.put("level", this.loadMusicFile("level.wav"));
    }

    private void loadFonts() {
        this.fonts = new HashMap<>();

        this.fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/SatellaRegular.ttf"));

        this.fonts.put("menu", this.generateFont(Constants.MENU_FONT_SIZE, Color.SALMON, 2, Color.WHITE));
        this.fonts.put("small", this.generateFont(Constants.SMALL_FONT_SIZE, Color.SALMON, 2, Color.WHITE));
    }

    private Texture loadTexture(String path) {
        return new Texture(Gdx.files.internal("sprites/" + path));
    }

    private Music loadMusicFile(String path) {
        return Gdx.audio.newMusic(Gdx.files.internal("music/" + path));
    }

    private BitmapFont generateFont(
            int size,
            Color color,
            int borderWidth,
            Color borderColor
    ) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.borderWidth = borderWidth;
        parameter.borderColor = borderColor;

        return this.fontGenerator.generateFont(parameter);
    }

    public Texture getSprite(String key) {
        return this.textures.get(key);
    }

    public Music getMusicTrack(String key) {
        return this.music.get(key);
    }

    public BitmapFont getFont(String key) {
        return this.fonts.get(key);
    }
}
