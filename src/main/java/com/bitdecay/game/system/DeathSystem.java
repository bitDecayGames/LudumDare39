package com.bitdecay.game.system;

import com.bitdecay.game.Launcher;
import com.bitdecay.game.component.*;
import com.bitdecay.game.event.EnemyHurtEvent;
import com.bitdecay.game.event.EventReactor;
import com.bitdecay.game.event.PlayerKillEvent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.screen.CreditsScreen;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

public class DeathSystem extends AbstractForEachUpdatableSystem {
    public DeathSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(HealthComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(HealthComponent.class, health -> {
            if (health.hp <= 0) {
                gob.addComponent(RemoveNowComponent.class);
                gob.addComponent(new DeadComponent());

                gob.forEach(PlayerInputComponent.class, player -> room.setScreen(new CreditsScreen(Launcher.GAME)));
                gob.forEach(AiComponent.class, ai -> {
                    EventReactor.fireEvent(new EnemyHurtEvent());
                    gob.forEach(PositionComponent.class, pos -> EventReactor.fireEvent(new PlayerKillEvent(pos.toVector2())));
                });
            }
        });
    }
}
