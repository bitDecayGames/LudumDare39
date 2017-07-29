package com.bitdecay.game.component;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.bitdecay.game.physics.Line;
import com.bitdecay.game.physics.Manifold;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monday on 7/28/2017.
 */
public class CollisionComponent extends AbstractComponent {
    public Shape2D body;

    public List<Manifold> manifolds = new ArrayList<>();

    private static Shape2D getShapeFromConfig(Config conf) {
        String type = conf.getString("type");
        switch(type) {
            case "circle":
                return new Circle(0, 0, (float) conf.getDouble("radius"));
            case "line":
                return new Line(conf.getConfig("twoPoints"));
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
