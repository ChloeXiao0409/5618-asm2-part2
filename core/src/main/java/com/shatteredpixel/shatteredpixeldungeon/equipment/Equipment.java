package com.shatteredpixel.shatteredpixeldungeon.equipment;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;

/**
 * 抽象装备基类
 */
public abstract class Equipment {
    private String name;
    private String description;

    public Equipment(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void applyEffect(Hero hero);

    public abstract void removeEffect(Hero hero);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}