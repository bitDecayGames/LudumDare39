package com.bitdecay.game.event;

import com.badlogic.gdx.math.Vector2;

public class Combo5PopupEvent extends PopupEvent {
    private Vector2 _position;
    public Combo5PopupEvent(Vector2 position){
        _position = position;
    }
    public String name(){
        return "Combo5";
    }
    public Vector2 position(){
        return this._position;
    }
}
