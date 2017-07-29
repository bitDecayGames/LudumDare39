package com.bitdecay.game.physics;

/**
 * Created by Monday on 7/28/2017.
 */
public class Manifold {
    public float overlap;
    public float angle;

    public Manifold(float overlap, float angle) {

        this.overlap = overlap;
        this.angle = angle;
    }
}
