package com.bitdecay.game.system;

import com.bitdecay.game.component.DeadComponent;
import com.bitdecay.game.component.ExploderComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.event.EventReactor;
import com.bitdecay.game.event.ExplosionEvent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

/**
 * Created by Monday on 7/29/2017.
 */
public class ExplodeSystem extends AbstractForEachUpdatableSystem {
    public ExplodeSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEachComponentDo(PositionComponent.class, pos -> {
            gob.forEachComponentDo(ExploderComponent.class, explode -> {
                gob.forEachComponentDo(DeadComponent.class, dead -> {
                    room.getGameObjects().add(MyGameObjectFactory.objectFromConf("rocketExplosion", pos.x, pos.y));
                    gob.removeComponent(ExploderComponent.class);
                    EventReactor.fireEvent(new ExplosionEvent());
                });
            });
        });
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(
                DeadComponent.class,
                ExploderComponent.class,
                PositionComponent.class
        );
    }
}
