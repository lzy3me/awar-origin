package com.lzy.awar.unit;

import com.badlogic.gdx.math.Vector2;

public class Infantry {
    private Vector2 position;

    /**
     * Infantry
     * @param spawnPosition set starting point for infantry unit.
     */
    public Infantry(Vector2 spawnPosition) {
        this.position = spawnPosition;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
