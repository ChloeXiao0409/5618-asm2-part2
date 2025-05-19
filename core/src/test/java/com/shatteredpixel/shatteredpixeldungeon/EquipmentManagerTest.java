/**
 * This test class verifies the functionality of the EquipmentManager component
 * in isolation from the full game environment. By substituting the Hero dependency
 * with minimal local state and using interface-driven design (IEquipment),
 * the tests demonstrate improved modularity, reusability, and testability.
 *
 * These qualities directly align with the maintainability attributes defined in
 * ISO/IEC 25010, ensuring the equipment system remains robust and adaptable
 * under change without introducing unnecessary coupling to the game engine.
 */


package com.shatteredpixel.shatteredpixeldungeon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.shatteredpixel.shatteredpixeldungeon.equipment.EquipmentManager;
import com.shatteredpixel.shatteredpixeldungeon.equipment.EquipmentSlot;
import com.shatteredpixel.shatteredpixeldungeon.equipment.IEquipment;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;

public class EquipmentManagerTest {

    private EquipmentManager equipmentManager;
    private int defense, attack, luck;

    private IEquipment armor, weapon, amulet;

    @BeforeEach
    void setUp() {
        equipmentManager = new EquipmentManager();
        defense = 0;
        attack = 0;
        luck = 0;

        armor = new IEquipment() {
            @Override public void applyEffect(Hero hero) { defense += 5; }
            @Override public void removeEffect(Hero hero) { defense -= 5; }
            @Override public String getName() { return "Armor"; }
        };

        weapon = new IEquipment() {
            @Override public void applyEffect(Hero hero) { attack += 5; }
            @Override public void removeEffect(Hero hero) { attack -= 5; }
            @Override public String getName() { return "Weapon"; }
        };

        amulet = new IEquipment() {
            @Override public void applyEffect(Hero hero) { luck += 1; }
            @Override public void removeEffect(Hero hero) { luck -= 1; }
            @Override public String getName() { return "Amulet"; }
        };
    }

    @Test void testEquipStoresItemInCorrectSlot() {
        equipmentManager.equip(EquipmentSlot.CHEST, armor, null);
        assertEquals(armor, equipmentManager.getEquipped(EquipmentSlot.CHEST));
    }

    @Test void testEquipArmorIncreasesDefense() {
        assertEquals(0, defense);
        equipmentManager.equip(EquipmentSlot.CHEST, armor, null);
        assertEquals(5, defense);
    }

    @Test void testUnequipArmorRestoresDefense() {
        equipmentManager.equip(EquipmentSlot.CHEST, armor, null);
        equipmentManager.unequip(EquipmentSlot.CHEST, null);
        assertEquals(0, defense);
    }

    @Test void testEquipWeaponIncreasesAttack() {
        assertEquals(0, attack);
        equipmentManager.equip(EquipmentSlot.WEAPON, weapon, null);
        assertEquals(5, attack);
    }

    @Test void testUnequipWeaponRestoresAttack() {
        equipmentManager.equip(EquipmentSlot.WEAPON, weapon, null);
        equipmentManager.unequip(EquipmentSlot.WEAPON, null);
        assertEquals(0, attack);
    }

    @Test void testEquipAmuletIncreasesLuck() {
        assertEquals(0, luck);
        equipmentManager.equip(EquipmentSlot.HEAD, amulet, null);
        assertEquals(1, luck);
    }

    @Test void testUnequipAmuletRestoresLuck() {
        equipmentManager.equip(EquipmentSlot.HEAD, amulet, null);
        equipmentManager.unequip(EquipmentSlot.HEAD, null);
        assertEquals(0, luck);
    }

    @Test void testReplaceArmorRevertsOldEffectAndAppliesNew() {
        IEquipment strongerArmor = new IEquipment() {
            @Override public void applyEffect(Hero hero) { defense += 10; }
            @Override public void removeEffect(Hero hero) { defense -= 5; }
            @Override public String getName() { return "Stronger Armor"; }
        };

        equipmentManager.equip(EquipmentSlot.CHEST, armor, null);
        assertEquals(5, defense);

        equipmentManager.equip(EquipmentSlot.CHEST, strongerArmor, null);
        assertEquals(10, defense);
    }
}

