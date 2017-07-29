package com.bitdecay.game.component;

import com.typesafe.config.Config;

public class HealthComponent extends AbstractComponent {
    public float maxHp;
    public float hp;

    public HealthComponent(int hp){
        this.hp = hp;
        this.maxHp = hp;
    }

    public HealthComponent(Config conf){
        hp = (float) conf.getDouble("hp");
        maxHp = hp;
    }
}