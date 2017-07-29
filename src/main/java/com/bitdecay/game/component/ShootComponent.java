package com.bitdecay.game.component;

import com.typesafe.config.Config;
import com.badlogic.gdx.math.Vector2;

public class ShootComponent extends AbstractComponent {
    public float x = 0;
    public float y = 0;

    public ShootComponent(float x, float y){
        this.x = x;
        this.y = y;
    }

    public ShootComponent(){ }

    /**
     * Immutable
     * @return new Vector2
     */
    public Vector2 toVector2(){
        return new Vector2(x, y);
    }
}
