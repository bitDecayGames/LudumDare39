package com.bitdecay.game.physics;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public class CollisionUtils {

    private static final float resolutionRatio = 0.1f;

    // returns null if the shapes do not collide.
    // returns a Manifold from the aspect of 'reference'
    public static Manifold collide(Shape2D reference, Vector2 refPos, Shape2D other, Vector2 otherPos) {
        if (reference instanceof Circle && other instanceof Circle) {
            Circle refCircle = (Circle) reference;
            refCircle = new Circle(refCircle);
            refCircle.x = refPos.x;
            refCircle.y = refPos.y;

            Circle otherCircle = (Circle) other;
            otherCircle = new Circle(otherCircle);
            otherCircle.x = otherPos.x;
            otherCircle.y = otherPos.y;

            if (refCircle.overlaps(otherCircle)) {
                Vector2 refCenter = new Vector2(refCircle.x, refCircle.y);
                Vector2 otherCenter = new Vector2(otherCircle.x, otherCircle.y);

                float overlap =  refCenter.cpy().sub(otherCenter).len() - (refCircle.radius + otherCircle.radius);
                if (overlap < 0) {
                    // we have overlapped
                    float  angle = otherCenter.cpy().sub(refCenter).angle();
                    return new Manifold(overlap*resolutionRatio, angle);
                }
            }
        } else if (reference instanceof Circle && other instanceof Line) {
            Circle refCircle = (Circle) reference;
            refCircle = new Circle(refCircle);
            refCircle.x = refPos.x;
            refCircle.y = refPos.y;

            Line otherLine = (Line) other;

            Vector2 direction = new Vector2();
            float amount = Intersector.intersectSegmentCircleDisplace(otherLine.start, otherLine.end, new Vector2(refCircle.x, refCircle.y), refCircle.radius, direction);
            if (amount != Float.POSITIVE_INFINITY) {
//                direction.scl(-1); // the direction is original the amount the LINE has to move. We wan't to move the circle.
                return new Manifold(refCircle.radius - amount, otherLine.normal.angle());
            } else if(otherLine.start.cpy().add(otherLine.normal).dst(refPos) > otherLine.start.cpy().sub(otherLine.normal).dst(refPos)){
                return new Manifold(otherLine.normal.len(), otherLine.normal.angle());
            }
        }
        return null;
    }
}
