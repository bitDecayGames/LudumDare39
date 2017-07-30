package com.bitdecay.game.component;

import com.typesafe.config.Config;

/**
 * Created by Monday on 7/30/2017.
 */
public class NextFloorComponent extends AbstractComponent {
    public String name;

    public NextFloorComponent(String name) {
        this.name = name;
    }

    public NextFloorComponent(Config conf) {
        this.name = conf.getString("floorName");
    }
}
