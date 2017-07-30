package com.bitdecay.game.system;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.SpeedComponent;
import com.bitdecay.game.component.VelocityComponent;
import com.bitdecay.game.event.EventReactor;
import com.bitdecay.game.event.PopupEvent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.gameobject.MyGameObjectFromConf;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;
import com.bitdecay.game.trait.IEvent;
import com.bitdecay.game.trait.IEventListener;

public class PopupSystem extends AbstractUpdatableSystem implements IEventListener {

    public PopupSystem(AbstractRoom room) {
        super(room);
        EventReactor.listenForEvents(this);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return false;//gob.hasComponent(SoundComponent.class);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void handleEvent(IEvent event) {
        if (event instanceof PopupEvent){
            PopupEvent pop = (PopupEvent) event;
            Vector2 pos = pop.position();
            MyGameObject popup = MyGameObjectFromConf.objectFromConf(pop.name(), pos.x, pos.y);
            popup.forEach(VelocityComponent.class, vel -> popup.forEach(SpeedComponent.class, speed -> vel.set(0, speed.speed)));
            room.getGameObjects().add(popup);
        }
    }
}
