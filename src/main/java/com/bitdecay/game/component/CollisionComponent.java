package com.bitdecay.game.component;

import com.badlogic.gdx.math.Shape2D;

/**
 * Created by Monday on 7/28/2017.
 */
public class CollisionComponent extends AbstractComponent {
    public Shape2D body;

    public CollisionComponent(Shape2D shape) {
        this.body = shape;
    }
}
