package com.bitdecay.game.component;

import com.bitdecay.game.animation.Animation;
import com.bitdecay.game.animation.AnimationDirection;
import com.bitdecay.game.animation.AnimationState;
import com.bitdecay.game.animation.AnimationUtils;
import com.bitdecay.game.pattern.AbstractBulletPattern;
import com.bitdecay.game.pattern.BulletPatternFactory;
import com.typesafe.config.Config;

import java.util.HashMap;
import java.util.Map;

public class WeaponComponent extends AbstractComponent {
    public String weaponName;
    public String bullet;
    public AbstractBulletPattern pattern;
    public int ammo = 1;
    public int maxAmmo = 1;
    public boolean unlimitedAmmo = false;
    public float secondsPerBullet = 1;
    public float cooldown = 0;

    public Map<AnimationState, Map<AnimationDirection, Animation>> animationMap = new HashMap<>();

    private WeaponComponent(String weaponName, String bullet, String pattern, float bulletsPerSecond, int maxBullets, int ammo, Boolean unlimitedAmmo){
        this.weaponName = weaponName;
        this.bullet = bullet;
        this.pattern = BulletPatternFactory.getBulletPatternByName(pattern);
        this.secondsPerBullet = bulletsPerSecond;
        this.ammo = ammo;
        this.maxAmmo = ammo;
        this.unlimitedAmmo = unlimitedAmmo;
    }

    public WeaponComponent(Config conf) {
        weaponName = conf.getString("weaponName");
        bullet = conf.getString("bullet");
        pattern = BulletPatternFactory.getBulletPatternByName(conf.getString("pattern"));
        secondsPerBullet = (float) conf.getDouble("secondsPerBullet");
        ammo = conf.getInt("ammo");
        maxAmmo = ammo;
        unlimitedAmmo = conf.getBoolean("unlimitedAmmo");
        animationMap = AnimationUtils.parseAnimationBundle(conf.getString("animationBundle"));
    }
}
