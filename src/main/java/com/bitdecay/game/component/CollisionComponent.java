package com.bitdecay.game.component;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.bitdecay.game.physics.Line;
import com.bitdecay.game.physics.Manifold;
import com.bitdecay.game.system.physics.CollisionSystem;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollisionComponent extends AbstractComponent {
    public Shape2D body;

    public List<Manifold> manifolds = new ArrayList<>();
    public Set<CollisionSystem.CollisionGroup> isPartOfGroups = new HashSet<>();
    public Set<CollisionSystem.CollisionGroup> collidesWith = new HashSet<>();

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
        this(getShapeFromConfig(conf), CollisionSystem.collisionGroupsFromStringList(conf.getStringList("isPartOfGroups")), CollisionSystem.collisionGroupsFromStringList(conf.getStringList("collidesWith")));
    }


    public CollisionComponent(Shape2D shape, Set<CollisionSystem.CollisionGroup> isPartOfGroups, Set<CollisionSystem.CollisionGroup> collidesWith) {
        this.body = shape;
        this.isPartOfGroups = isPartOfGroups;
        this.collidesWith = collidesWith;
    }
}
