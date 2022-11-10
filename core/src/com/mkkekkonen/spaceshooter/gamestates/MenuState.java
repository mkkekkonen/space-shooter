package com.mkkekkonen.spaceshooter.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;

@Module
public class MenuState extends AbstractGameState {
    @Inject InputManager inputManager;

    private BitmapFont menuFont;

    @Inject
    MenuState(ResourceManager resourceManager) {
        this.menuFont = resourceManager.getFont("menu");
    }

    @Override
    public void update(float deltaTime) {
        this.inputManager.getInput();
    }

    @Override
    public void draw(SpriteBatch batch) {
        this.menuFont.draw(
                batch,
                "New Game",
                50,
                this.getY(50)
        );

        this.menuFont.draw(
                batch,
                "High Scores",
                50,
                this.getY(100)
        );
    }

    private float getY(float distance) {
        return Gdx.graphics.getHeight() - distance;
    }
}
