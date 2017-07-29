package com.bitdecay.game.system.physics;

import com.bitdecay.game.component.CollisionComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.physics.Manifold;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

/**
 * Created by Monday on 7/29/2017.
 */
public class ManifoldResolutionSystem extends AbstractForEachUpdatableSystem {
    public ManifoldResolutionSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEachComponentDo(PositionComponent.class, pos -> {
            gob.forEachComponentDo(CollisionComponent.class, collisions -> {
                for (Manifold manifold : collisions.manifolds) {
                    pos.x += manifold.all.x;
                    pos.y += manifold.all.y;
                }
                collisions.manifolds.clear();
            });
        });
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(
                CollisionComponent.class,
                PositionComponent.class
        );
    }
}
