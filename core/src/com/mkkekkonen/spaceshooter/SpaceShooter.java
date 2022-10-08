package com.mkkekkonen.spaceshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mkkekkonen.spaceshooter.game.Game;
import com.mkkekkonen.spaceshooter.interfaces.DaggerIGame;
import com.mkkekkonen.spaceshooter.interfaces.IGame;


public class SpaceShooter extends ApplicationAdapter {
	SpriteBatch batch;
	IGame game;
	Game _game;
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();

		this.game = DaggerIGame.builder().build();
		this._game = game.game();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.3f, 0.25f, 0.75f, 1);

		this._game.update();

		this.batch.begin();
		this._game.render(batch);
		this.batch.end();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
		this._game.dispose();
	}
}
