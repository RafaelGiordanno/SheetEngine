package com.rafaelgiordanno.sheetengine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class SheetEditor implements ApplicationListener {
	
	private OrthographicCamera hudCamera;
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontParameter fontParameter;
	private BitmapFont font12;
	private SpriteBatch spriteBatch;
	
	@Override
	public void create() {
		hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("m6x11.ttf"));
		fontParameter = new FreeTypeFontParameter();
		fontParameter.size = 18;
		font12 = fontGenerator.generateFont(fontParameter);
		spriteBatch = new SpriteBatch();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.02f, 0.1f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.setProjectionMatrix(hudCamera.combined);
		spriteBatch.begin();
		font12.draw(spriteBatch, "Maybe it all comes full circle!", -120, 0);
		spriteBatch.end();
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		font12.dispose();
		fontGenerator.dispose();
	}
}
