package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class FrictionComponent extends AbstractComponent {
    public float friction = 0;

    public FrictionComponent(float friction) { this.friction = Math.max(Math.min(friction, 1), 0); }

    public FrictionComponent(Config conf){
        friction = (float) conf.getDouble("friction");
    }
}
