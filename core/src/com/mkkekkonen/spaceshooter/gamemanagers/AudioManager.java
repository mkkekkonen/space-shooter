package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.audio.Music;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AudioManager {
    @Inject ResourceManager resourceManager;

    private Music currentTrack;

    @Inject
    AudioManager() {}

    public void changeMusic(GameState gameState) {
        if (this.currentTrack != null) {
            this.currentTrack.stop();
        }

        if (gameState.equals(GameState.MENU)) {
            this.playMusic("menu");
        } else if (gameState.equals(GameState.GAME_PLAYING)) {
            this.playMusic("level");
        }
    }

    private void playMusic(String key) {
        this.currentTrack = this.resourceManager.getMusicTrack(key);
        this.currentTrack.setLooping(true);
        this.currentTrack.play();
    }
}
