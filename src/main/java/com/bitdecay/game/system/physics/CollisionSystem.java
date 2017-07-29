package com.bitdecay.game.system.physics;

import com.bitdecay.game.component.CollisionComponent;
import com.bitdecay.game.component.ManifoldComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.physics.CollisionUtils;
import com.bitdecay.game.physics.Manifold;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;

/**
 * Created by Monday on 7/28/2017.
 */
public class CollisionSystem extends AbstractUpdatableSystem {
    public CollisionSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(
                PositionComponent.class,
                CollisionComponent.class
        );
    }

    @Override
    public void update(float delta) {
        gobs.forEach( gobOutter -> {
            gobs.forEach( gobInner -> {
                if (gobOutter == gobInner) {
                    return;
                }

                CollisionComponent outterCollider = gobOutter.getComponent(CollisionComponent.class).get();
                PositionComponent outterPosition = gobOutter.getComponent(PositionComponent.class).get();
                CollisionComponent innerCollider = gobInner.getComponent(CollisionComponent.class).get();
                PositionComponent innerPosition = gobInner.getComponent(PositionComponent.class).get();

                Manifold manifold = CollisionUtils.collide(outterCollider.body, outterPosition.toVector2(), innerCollider.body, innerPosition.toVector2());
                if (manifold != null) {
                    gobOutter.addComponent(new ManifoldComponent(manifold));
                }
            });
        });
    }
}
