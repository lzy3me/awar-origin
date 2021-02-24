package com.lzy.awar.unit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class Infantry implements Disposable {
    private Vector2 position;
    private Texture infantryTexture;

    /**
     * Infantry
     * @param spawnPosition set starting point for infantry unit.
     */
    public Infantry(Vector2 spawnPosition) {
        this.position = spawnPosition;
        infantryTexture = new Texture(Gdx.files.internal("unit/testsubject.png"));
    }

    public void update(float delta) {
        // TODO: Update data on frametime at this method
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(infantryTexture, position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public void dispose() {
        infantryTexture.dispose();
    }
}
