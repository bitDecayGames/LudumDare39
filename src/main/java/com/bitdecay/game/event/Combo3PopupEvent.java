package com.bitdecay.game.event;

import com.badlogic.gdx.math.Vector2;

public class Combo3PopupEvent extends PopupEvent {
    private Vector2 _position;
    public Combo3PopupEvent(Vector2 position){
        _position = position;
    }
    public String name(){
        return "Combo3";
    }
    public Vector2 position(){
        return this._position;
    }
}
