package com.bitdecay.game.system.physics;

import com.bitdecay.game.component.*;
import com.bitdecay.game.event.EventReactor;
import com.bitdecay.game.event.PlayerHurtEvent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.physics.CollisionUtils;
import com.bitdecay.game.physics.Manifold;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                    if (gobOutter.hasComponent(DamagerComponent.class) && gobInner.hasComponent(HealthComponent.class)) gobOutter.forEach(DamagerComponent.class, dmg -> {
                        if (dmg.targetGroups.size() == 0 || innerCollider.isPartOfGroups.stream().anyMatch(dmg.targetGroups::contains)) {
                            gobInner.addComponent(new DamageComponent(dmg.damage));
                            if (gobInner.hasComponent(PlayerInputComponent.class)) EventReactor.fireEvent(new PlayerHurtEvent(gobOutter));
                        }
                    });
                    else if (gobInner.hasComponent(DamagerComponent.class) && gobOutter.hasComponent(HealthComponent.class)) gobInner.forEach(DamagerComponent.class, dmg -> {
                        if (dmg.targetGroups.size() == 0 || outterCollider.isPartOfGroups.stream().anyMatch(dmg.targetGroups::contains)) {
                            gobOutter.addComponent(new DamageComponent(dmg.damage));
                            if (gobOutter.hasComponent(PlayerInputComponent.class)) EventReactor.fireEvent(new PlayerHurtEvent(gobInner));
                        }
                    });
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

    public static Set<CollisionGroup> collisionGroupsFromStringList(List<String> groups){
        return groups.stream().map(CollisionSystem.CollisionGroup::valueOf).collect(Collectors.toSet());
    }
}