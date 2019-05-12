package com.rafaelgiordanno.sheetengine.editor;

import com.rafaelgiordanno.sheetengine.Input;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class SheetEditor implements ApplicationListener {
	private OrthographicCamera hudCamera;
	private Input input;
	BaseController mainController;
	Renderer renderer;
	
	@Override
	public void create() {
		hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		input = new Input();
		Gdx.input.setInputProcessor(input);
		mainController = new BaseController();
		renderer = new Renderer();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void render() {
		mainController.update(Gdx.graphics.getDeltaTime(), input, hudCamera);
		renderer.render(mainController, hudCamera);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		renderer.dispose();
	}
}
