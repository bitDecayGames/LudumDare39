package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class TargetSizeComponent extends AbstractComponent {
    public float w = 0;
    public float h = 0;

    public TargetSizeComponent(Config conf) {
        this((float) conf.getDouble("w"), (float) conf.getDouble("h"));
    }

    public TargetSizeComponent(float width, float height){
        this.w = width;
        this.h = height;
    }

    public void set(float width, float height) {
        w = width;
        h = height;
    }
}
