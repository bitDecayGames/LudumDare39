package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class SpeedComponent extends AbstractComponent {
    public float speed = 0;

    public SpeedComponent(float speed){
        this.speed = speed;
    }

    public SpeedComponent(Config conf){
        speed = (float) conf.getDouble("speed");
    }
}
