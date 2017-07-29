package com.bitdecay.game.physics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Monday on 7/28/2017.
 */
public class Manifold {
    public float overlap;
    public float angle;
    public Vector2 all;

    public Manifold(float overlap, float angle) {
        this.overlap = overlap;
        this.angle = angle;

        all = new Vector2(1, 0).rotate(angle).scl(overlap);
    }
}
