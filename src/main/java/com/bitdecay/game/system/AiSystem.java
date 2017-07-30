package com.bitdecay.game.system;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.AiComponent;
import com.bitdecay.game.component.PlayerInputComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;

public class AiSystem extends AbstractUpdatableSystem {
    public AiSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponent(AiComponent.class) || gob.hasComponent(PlayerInputComponent.class);
    }

    @Override
    public void update(float delta) {
        gobs.stream().filter(gob -> gob.hasComponent(PlayerInputComponent.class)).findFirst().ifPresent(player -> {
            Vector2 playerPos = player.getComponent(PositionComponent.class).map(PositionComponent::toVector2).orElseGet(()->new Vector2(0, 0));
            gobs.forEach(aiGob -> aiGob.forEach(AiComponent.class, ai -> {
                ai.behavior.update(delta, aiGob, playerPos);
            }));
        });
    }
}
