package com.shatteredpixel.shatteredpixeldungeon.equipment;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;

/**
 * 通用装备接口
 * 适配现有 Armor、Weapon、Amulet 等
 */
public interface IEquipment {
    void applyEffect(Hero hero);
    void removeEffect(Hero hero);
    String getName();
}