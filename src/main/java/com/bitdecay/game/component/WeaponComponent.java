package com.bitdecay.game.component;

import com.bitdecay.game.pattern.AbstractBulletPattern;
import com.bitdecay.game.pattern.BulletPatternFactory;
import com.typesafe.config.Config;

public class WeaponComponent extends AbstractComponent {
    public String weaponName;
    public String bullet;
    public AbstractBulletPattern pattern;
    public int ammo = 1;
    public boolean unlimitedAmmo = false;
    public float secondsPerBullet = 1;
    public float cooldown = 0;

    private WeaponComponent(String weaponName, String bullet, String pattern, float bulletsPerSecond, int maxBullets, int ammo, Boolean unlimitedAmmo){
        this.weaponName = weaponName;
        this.bullet = bullet;
        this.pattern = BulletPatternFactory.getBulletPatternByName(pattern);
        this.secondsPerBullet = bulletsPerSecond;
        this.ammo = ammo;
        this.unlimitedAmmo = unlimitedAmmo;
    }

    public WeaponComponent(Config conf) {
        weaponName = conf.getString("weaponName");
        bullet = conf.getString("bullet");
        pattern = BulletPatternFactory.getBulletPatternByName(conf.getString("pattern"));
        secondsPerBullet = (float) conf.getDouble("secondsPerBullet");
        ammo = conf.getInt("ammo");
        unlimitedAmmo = conf.getBoolean("unlimitedAmmo");
    }
}
