package com.bitdecay.game.component;

import com.bitdecay.game.system.physics.CollisionSystem;
import com.typesafe.config.Config;

import java.util.HashSet;
import java.util.Set;

public class DamagerComponent extends AbstractComponent {
    public float damage;
    public Set<CollisionSystem.CollisionGroup> targetGroups = new HashSet<>();

    public DamagerComponent(float damage, Set<CollisionSystem.CollisionGroup> targetGroups){
        this.damage = damage;
        this.targetGroups = targetGroups;
    }

    public DamagerComponent(Config conf){
        damage = (float) conf.getDouble("damage");
        if (conf.hasPath("targetGroups")) targetGroups = CollisionSystem.collisionGroupsFromStringList(conf.getStringList("targetGroups"));
    }
}