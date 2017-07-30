package com.bitdecay.game.system;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.event.*;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractUpdatableSystem;
import com.bitdecay.game.trait.IEvent;
import com.bitdecay.game.trait.IEventListener;

public class ComboSystem extends AbstractUpdatableSystem implements IEventListener {

    private int maxCombo = 0;
    private int currentCombo = 0;
    private float comboTimerReset = 2;
    private float comboTimer = comboTimerReset;

    public ComboSystem(AbstractRoom room) {
        super(room);
        EventReactor.listenForEvents(this);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return false;//gob.hasComponent(SoundComponent.class);
    }

    @Override
    public void update(float delta) {
        if (comboTimer < 0){
            if (currentCombo > maxCombo) maxCombo = currentCombo;
            currentCombo = 0;
        } else comboTimer -= delta;
    }

    @Override
    public void handleEvent(IEvent event) {
        if (event instanceof PlayerKillEvent){
            comboTimer = comboTimerReset;
            currentCombo++;

            Vector2 pos = ((PlayerKillEvent) event).position.cpy();
            switch (currentCombo){
                case 100: EventReactor.fireDelayedEvent(0, new Combo1PopupEvent(pos)); break;
                case 200: EventReactor.fireDelayedEvent(0, new Combo2PopupEvent(pos)); break;
                case 300: EventReactor.fireDelayedEvent(0, new Combo3PopupEvent(pos)); break;
                case 400: EventReactor.fireDelayedEvent(0, new Combo4PopupEvent(pos)); break;
                case 500: EventReactor.fireDelayedEvent(0, new Combo5PopupEvent(pos)); break;
                default: break;
            }
        }
    }
}
