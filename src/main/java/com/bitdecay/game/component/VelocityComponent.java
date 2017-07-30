package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;

public class VelocityComponent extends AbstractComponent {
    public float x = 0;
    public float y = 0;
    public Vector2 lastIntended = new Vector2();

    /**
     * Immutable
     * @return new Vector2
     */
    public Vector2 toVector2(){
        return new Vector2(x, y);
    }


    public VelocityComponent set(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }

    public VelocityComponent set(Vector2 v){
        this.x = v.x;
        this.y = v.y;
        return this;
    }
}
