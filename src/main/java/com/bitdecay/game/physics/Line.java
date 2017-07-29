package com.bitdecay.game.physics;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.typesafe.config.Config;

/**
 * Created by Monday on 7/29/2017.
 */
public class Line implements Shape2D {
    public Vector2 start;
    public Vector2 end;

    public Line(Vector2 start, Vector2 end) {
        this.start = start;
        this.end = end;
    }

    public Line(Config conf) {
        start = new Vector2(
                (float)conf.getDouble("x1"),
                (float)conf.getDouble("y1")
        );
        end = new Vector2(
                (float)conf.getDouble("x2"),
                (float)conf.getDouble("y2")
        );
    }
}
