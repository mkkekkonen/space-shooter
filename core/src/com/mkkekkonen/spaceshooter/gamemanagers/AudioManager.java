package com.mkkekkonen.spaceshooter.gamemanagers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AudioManager {
    private ResourceManager resourceManager;

    private Music currentTrack;
    private Sound explosionSound;

    private boolean mute = false;

    @Inject
    AudioManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;

        this.explosionSound = resourceManager.getSound("explosion");
    }

    public void changeMusic(GameState gameState) {
        if (this.currentTrack != null) {
            this.currentTrack.stop();
        }

        if (gameState.equals(GameState.MENU) || gameState.equals(GameState.HIGH_SCORES)) {
            this.playMusic("menu");
        } else if (gameState.equals(GameState.GAME_PLAYING)) {
            this.playMusic("level");
        }
    }

    public void toggleAudio() {
        if (this.mute) {
            this.mute = false;
            this.currentTrack.play();
        } else {
            this.mute = true;
            this.currentTrack.stop();
        }
    }

    private void playMusic(String key) {
        if (this.mute) {
            return;
        }

        this.currentTrack = this.resourceManager.getMusicTrack(key);
        this.currentTrack.setLooping(true);
        this.currentTrack.play();
    }

    public void playExplosionSound() {
        if (this.mute) {
            return;
        }

        this.explosionSound.play();
    }

    public boolean isMuted() {
        return this.mute;
    }
}
