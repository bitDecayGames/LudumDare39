package com.bitdecay.game.physics;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Monday on 7/28/2017.
 */
public class CollisionUtils {

    // returns null if the shapes do not collide.
    // returns a Manifold from the aspect of 'reference'
    public static Manifold collide(Shape2D reference, Vector2 refPos, Shape2D other, Vector2 otherPos) {
        if (reference instanceof Circle && other instanceof Circle) {
            Circle refCircle = (Circle) reference;
            Circle otherCircle = (Circle) other;

            if (refCircle.overlaps(otherCircle)) {
                Vector2 refCenter = new Vector2(refCircle.x + refPos.x, refCircle.y + refPos.y);
                Vector2 otherCenter = new Vector2(otherCircle.x + otherPos.x, otherCircle.y + otherPos.y);

                float overlap =  refCenter.cpy().sub(otherCenter).len() - (refCircle.radius + otherCircle.radius);
                if (overlap < 0) {
                    // we have overlapped
                    float angle = otherCenter.cpy().sub(refCenter).angle();
                    return new Manifold(overlap*.1f, angle);
                }
            }
        } else if (reference instanceof Circle && other instanceof Line) {
            
        }
        return null;
    }
}
