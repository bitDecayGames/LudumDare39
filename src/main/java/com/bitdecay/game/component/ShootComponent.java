package com.bitdecay.game.component;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by tristan on 7/28/17.
 */
public class ShootComponent extends AbstractComponent {
    public float x = 0;
    public float y = 0;

    public ShootComponent(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Immutable
     * @return new Vector2
     */
    public Vector2 toVector2(){
        return new Vector2(x, y);
    }


}
