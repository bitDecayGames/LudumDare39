package com.bitdecay.game.event;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.trait.IEvent;

public class PlayerHurtEvent implements IEvent {
    public MyGameObject causedBy = null;
    public PlayerHurtEvent(MyGameObject causedBy){
        this.causedBy = causedBy;
    }
}
