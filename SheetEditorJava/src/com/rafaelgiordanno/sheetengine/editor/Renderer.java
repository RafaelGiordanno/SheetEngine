package com.rafaelgiordanno.sheetengine.editor;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.sww.Gui;

public class Renderer {
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
		
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontParameter fontParameter;
	private BitmapFont defaultFont;
	
	private Map map;
	private Texture tex;
	
	public Renderer() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/m6x11.ttf"));
		fontParameter = new FreeTypeFontParameter();
		fontParameter.size = 18;
		defaultFont = fontGenerator.generateFont(fontParameter);
		try {
			map = new Map();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tex = new Texture(Gdx.files.internal("Content/maps1.png"));
		tex.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	}
	
	String clickButton = "Touch me!";
	
	public void render(BaseController c, OrthographicCamera hudCamera) {
		Gdx.gl.glClearColor(0.01f, 0.03f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setProjectionMatrix(hudCamera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0.25f, 0.25f, 0.25f, 1f);
		shapeRenderer.rect(
				hudCamera.viewportWidth * .25f, -hudCamera.viewportHeight * 0.5f,
				hudCamera.viewportWidth * 0.25f, hudCamera.viewportHeight);
		shapeRenderer.setColor(0.1f, 0.1f, 0.1f, 1f);
		shapeRenderer.rect(
				hudCamera.viewportWidth * .25f + 2, -hudCamera.viewportHeight * 0.5f + 2,
				hudCamera.viewportWidth * 0.25f - 4, hudCamera.viewportHeight - 4);
		shapeRenderer.end();
		batch.setProjectionMatrix(hudCamera.combined);
		batch.begin();
		drawMapSegments(batch, hudCamera);
		defaultFont.draw(batch, "Maybe it all comes full circle!", -120, 0);
		batch.end();
	}
	
	private void drawMapSegments(SpriteBatch sb, OrthographicCamera cam) {
		// sb.draw(tex, -300, -200, 256, 256);
		// test
//		sb.draw(tex,         // texture
//				-230,-100,   // x and y in the world
//				0.0f, 26.0f,       // origin x and y (to rotate and all)
//				410.0f, 152.0f,    // width and height
//				.5f,  .5f,   // scale x and y
//				0.0f,        // rotation
//				0, 26,       // srcX and srcY
//				410, 152,    // srcWidth and srcHeight,
//				false, false // flip x and y
//		);
		defaultFont.draw(sb, "Sprites", 220, 168);
		SegmentDefinition[] segs = map.getSegments();
		int size = 9;
		float w = cam.viewportWidth;
		float h = cam.viewportHeight;
		for (int i = 0; i < 8; i++) {
			float dw = segs[i].getSrcRect().width;
			float dh = segs[i].getSrcRect().height;
			if (dw > dh) {
				dh = (int)(((float)dh / (float)dw) * 100.0f);
				dw = 100;
			} else {
				dw = (int)(((float)dw / (float)dh) * 100.0f);
				dh = 100;
			}
			sb.draw(tex, // texture
					// x and y in the world
					180, h * 0.28f - (i * (h / size)),
					// origin x and y (to rotate and all)
					0, 30,
					// width and height
					// segs[i].getSrcRect().width, segs[i].getSrcRect().height,
					dw, dh,
					// scale x and y
					.5f,  .5f,
					// rotation
					0.0f,
					// srcX and srcY
					(int) segs[i].getSrcRect().x, (int) segs[i].getSrcRect().y,
				    // srcWidth and srcHeight,
					(int) segs[i].getSrcRect().width, (int) segs[i].getSrcRect().height,
					// flip x and y
					false, false
			);
			defaultFont.draw(sb, segs[i].getName(), 240, h * 0.28f - (i * (h / size) - 36));
		}
		// sb.draw(texture,   x,        y, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
		// sb.draw(       tex, -300, -100,   410,   152,     0,   26,      410,       152, false, false);
		// sb.draw(texture,   x,     y, width, height, srcX, srcY, srcWidth, srcHeight, flipX, flipY)
	}
	
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
		defaultFont.dispose();
		fontGenerator.dispose();
	}
}
