package com.mkkekkonen.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mkkekkonen.spaceshooter.interfaces.DaggerIGame;
import com.mkkekkonen.spaceshooter.interfaces.IGame;


public class SpaceShooter extends ApplicationAdapter {
	SpriteBatch batch;
	IGame game;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.game = DaggerIGame.builder().build();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.3f, 0.25f, 0.75f, 1);
		batch.begin();
		this.game.game().render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
