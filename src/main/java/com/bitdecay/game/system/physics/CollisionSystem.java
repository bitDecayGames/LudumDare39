package com.bitdecay.game.system.physics;

import com.bitdecay.game.component.*;
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
                if (gobOutter == gobInner) return;

                CollisionComponent outterCollider = gobOutter.getComponent(CollisionComponent.class).get();
                PositionComponent outterPosition = gobOutter.getComponent(PositionComponent.class).get();
                CollisionComponent innerCollider = gobInner.getComponent(CollisionComponent.class).get();
                PositionComponent innerPosition = gobInner.getComponent(PositionComponent.class).get();

                if (outterCollider.isPartOfGroups.stream().noneMatch(innerCollider.collidesWith::contains) &&
                        innerCollider.isPartOfGroups.stream().noneMatch(outterCollider.collidesWith::contains)) return;

                Manifold manifold = CollisionUtils.collide(outterCollider.body, outterPosition.toVector2(), innerCollider.body, innerPosition.toVector2());
                if (manifold != null) {
                    outterCollider.manifolds.add(manifold);
                    if (gobOutter.hasComponent(DamagerComponent.class) && gobInner.hasComponent(HealthComponent.class)) gobOutter.forEach(DamagerComponent.class, dmg -> gobInner.addComponent(new DamageComponent(dmg.damage)));
                    else if (gobInner.hasComponent(DamagerComponent.class) && gobOutter.hasComponent(HealthComponent.class)) gobInner.forEach(DamagerComponent.class, dmg -> gobOutter.addComponent(new DamageComponent(dmg.damage)));
                }
            });
        });
    }

    public enum CollisionGroup {
        PLAYER,
        ENEMY,
        PLAYER_BULLET,
        ENEMY_BULLET,
        WALL
    }
}