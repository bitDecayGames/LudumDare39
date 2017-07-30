package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class PickupComponent extends AbstractComponent {
    public String type;

    public PickupComponent(String type){
        this.type = type;
    }

    public PickupComponent(Config conf){
        type = conf.getString("type");
    }
}