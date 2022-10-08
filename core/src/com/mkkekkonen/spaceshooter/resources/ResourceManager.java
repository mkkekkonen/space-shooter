package com.mkkekkonen.spaceshooter.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;

@Singleton
public class ResourceManager {
    private Map<String, Texture> textures;

    @Inject
    ResourceManager() {
        this.loadResources();
    }

    public void dispose() {
        for (String key : this.textures.keySet()) {
            this.textures.get(key).dispose();
        }
    }

    private void loadResources() {
        this.textures = new HashMap<>();
        this.textures.put("ship", this.loadTexture("ship.png"));
        this.textures.put("shootingBarComponent", this.loadTexture("shootingbar.png"));

        for (int i = 1; i <= 6; i++) {
            this.textures.put("roid" + i, this.loadTexture("roid" + i + ".png"));
        }
    }

    private Texture loadTexture(String path) {
        return new Texture(Gdx.files.internal(path));
    }

    public Texture getSprite(String key) {
        return this.textures.get(key);
    }
}
