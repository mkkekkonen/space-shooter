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

    private void loadResources() {
        this.textures = new HashMap<>();
        this.textures.put("ship", new Texture(Gdx.files.internal("ship.png")));
    }

    public Texture getSprite(String key) {
        return this.textures.get(key);
    }
}
