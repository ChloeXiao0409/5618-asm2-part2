package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

public enum Augment {
    SPEED   (0.7f, 2/3f),
    DAMAGE  (1.5f, 5/3f),
    NONE	(1.0f, 1f);

    private float damageFactor;
    private float delayFactor;

    Augment(float dmg, float dly){
        damageFactor = dmg;
        delayFactor = dly;
    }

    public int damageFactor(int dmg){
        return Math.round(dmg * damageFactor);
    }

    public float delayFactor(float dly){
        return dly * delayFactor;
    }
}