package com.bitdecay.game.event;

import com.badlogic.gdx.math.Vector2;

public class Combo4PopupEvent extends PopupEvent {
    private Vector2 _position;
    public Combo4PopupEvent(Vector2 position){
        _position = position;
    }
    public String name(){
        return "Combo4";
    }
    public Vector2 position(){
        return this._position;
    }
}
