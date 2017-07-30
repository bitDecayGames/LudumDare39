package com.bitdecay.game.event;

import com.badlogic.gdx.math.Vector2;

public class AmmoPopupEvent extends PopupEvent {
    private Vector2 _position;
    public AmmoPopupEvent(Vector2 position){
        _position = position;
    }
    public String name(){
        return "+Ammo";
    }
    public Vector2 position(){
        return this._position;
    }
}
