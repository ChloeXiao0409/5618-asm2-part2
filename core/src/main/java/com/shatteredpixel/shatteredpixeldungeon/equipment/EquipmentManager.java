package com.shatteredpixel.shatteredpixeldungeon.equipment;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;

import java.util.HashMap;
import java.util.Map;

/**
 * 装备管理器
 */
public class EquipmentManager {
    private Map<EquipmentSlot, IEquipment> equippedItems = new HashMap<>();

    public void equip(EquipmentSlot slot, IEquipment equipment, Hero hero) {
        IEquipment current = equippedItems.get(slot);
        if (current != null) {
            current.removeEffect(hero);
        }
        equippedItems.put(slot, equipment);
        equipment.applyEffect(hero);
    }

    public void unequip(EquipmentSlot slot, Hero hero) {
        IEquipment equipment = equippedItems.remove(slot);
        if (equipment != null) {
            equipment.removeEffect(hero);
        }
    }
    public IEquipment getEquipped(EquipmentSlot slot) {
        return equippedItems.get(slot);
    }
}