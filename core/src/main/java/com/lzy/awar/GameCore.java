package com.lzy.awar;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.lzy.awar.unit.Infantry;
import com.lzy.awar.utiltest.CamController;

public class GameCore extends ApplicationAdapter {
    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private BitmapFont font;
    private SpriteBatch batch;
    private CamController camController;

    Infantry infantry;

    VisUI visUI;
    VisTable visTable;

    int levelWidth = 0;
    int levelHeight = 0;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        // Logger (width and height of application)
        Gdx.app.log("width & height", w +"-"+ h);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / 2, h / 2);
        camera.update();

        font = new BitmapFont();
        batch = new SpriteBatch();

        map = new TmxMapLoader().load("maps/_untitled.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f / 2f);
        MapProperties mapProperties = map.getProperties();

        levelWidth = mapProperties.get("width", Integer.class);
        levelHeight = mapProperties.get("height", Integer.class);

        camController = new CamController(camera);
        Gdx.input.setInputProcessor(camController);

        visUI = new VisUI();
        visUI.load();
        visTable = new VisTable(true);

        /// Initialize Infantry unit
        infantry = new Infantry(new Vector2(64, 64));
    }

    @Override
    public void render() {
        float startX = camera.viewportWidth / 2;
        float startY = camera.viewportHeight / 2;
        camController.boundary(camera, startX, startY, levelWidth * 64 - startX * 2, levelHeight * 64 - startY * 2);
        Gdx.gl.glClearColor(1, .8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();
        batch.begin();
        camController.debuggerMessagesRenderer(batch, font);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 60);
        /**
         * Currently it not render in tile level right now.
         * (It's render on @SpriteBatch like camController debugger and FPS data render)
         * so TODO: Make infantry unit display at tile level
         */
        infantry.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        map.dispose();
        visUI.dispose();
        infantry.dispose();
    }
}
