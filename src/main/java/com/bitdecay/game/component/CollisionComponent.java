package com.bitdecay.game.component;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.typesafe.config.Config;

/**
 * Created by Monday on 7/28/2017.
 */
public class CollisionComponent extends AbstractComponent {
    public Shape2D body;

    private static Shape2D getShapeFromConfig(Config conf) {
        String type = conf.getString("type");
        switch(type) {
            case "circle":
                return new Circle(0, 0, (float) conf.getDouble("radius"));
            default:
                return new Circle(0, 0, 1);
        }
    }

    public CollisionComponent(Config conf) {
        this(getShapeFromConfig(conf));
    }


    public CollisionComponent(Shape2D shape) {
        this.body = shape;
    }
}
