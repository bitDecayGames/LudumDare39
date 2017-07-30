package com.bitdecay.game.pattern;

public class BulletPatternFactory {
    private BulletPatternFactory(){}

    public static AbstractBulletPattern getBulletPatternByName(String name){
        switch (name){
            case "StraightAhead":
                return new StraightAheadBulletPattern();
            case "TriShot":
                return new TriShotBulletPattern();
            case "SingleRandomSpray":
                return new SingleRandomSprayBulletPattern();
            case "SingleRandomSpray_LG":
                return new SingleRandomSprayBulletPattern(50);
            case "SingleRandomSpray_SM":
                return new SingleRandomSprayBulletPattern(10);
            case "ScatterShot":
                return new ScatterShotBulletPattern();
            default:
                throw new RuntimeException("Could not find bullet pattern called: " + name);
        }
    }
}