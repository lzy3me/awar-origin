package com.lzy.awar.utiltest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class CamController extends InputAdapter {
    private OrthographicCamera camera;
    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();

    public CamController(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        camera.unproject(curr.set(x, y, 0));
        if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
            camera.unproject(delta.set(last.x, last.y, 0));
            delta.sub(curr);
            camera.position.add(delta.x, delta.y, 0);
        }
        last.set(x, y, 0);
        return false;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // set current zoom level when scroll your mouse from 1 to 4
        if (camera.zoom != 0 && camera.zoom < 5)
            camera.zoom += amountY;
        // Check if your scroll wheel is so fast it won't go out of bound
        if (camera.zoom >= 5)
            camera.zoom = 4;
        if (camera.zoom <= 0)
            camera.zoom = 1;
        return false;
    }

    public void debuggerMessagesRenderer(SpriteBatch batch, BitmapFont font) {
        font.draw(batch, "Camera Zoom:"+camera.zoom, 10,  20);
        font.draw(batch, "camera.position:"+camera.position, 10, 40);
    }

    public void boundary(Camera camera, float startX, float startY, float width, float height) {
        Vector3 position = camera.position;
        // Set bound on X axis
        if (position.x < startX)
            position.x = startX;
        if (position.x > startX + width)
            position.x = startX + width;
        // Set bound on Y axis
        if (position.y < startY)
            position.y = startY;
        if (position.y > startY + height)
            position.y = startY + height;

        camera.position.set(position);
        camera.update();
    }
    
}
