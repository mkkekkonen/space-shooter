package com.mkkekkonen.spaceshooter.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mkkekkonen.spaceshooter.enums.GameState;
import com.mkkekkonen.spaceshooter.gamemanagers.GameStateManager;
import com.mkkekkonen.spaceshooter.input.InputManager;
import com.mkkekkonen.spaceshooter.resources.ResourceManager;

import javax.inject.Inject;

import dagger.Module;

@Module
public class ExitButton extends AbstractSprite {
    @Inject InputManager inputManager;

    private boolean clicked = false;

    @Inject
    ExitButton(ResourceManager resourceManager) {
        this.initTexture(resourceManager.getSprite("exitButton"), 2);

        float pad = Gdx.graphics.getWidth() / 50;
        this.position = new Vector2(
                Gdx.graphics.getWidth() - pad - (this.width / 2),
                Gdx.graphics.getHeight() - pad - (this.height / 2)
        );
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void update(float deltaTime) {
        this.inputManager.getInput();

        Vector2 clickLocation = this.inputManager.getClickLocation();

        this.clicked = clickLocation != null
                && clickLocation.x > this.position.x - (this.width / 2)
                && clickLocation.x < this.position.x + (this.width / 2)
                && clickLocation.y > this.position.y - (this.height / 2)
                && clickLocation.y < this.position.y + (this.height / 2);
    }

    public boolean isClicked() {
        return clicked;
    }
}
