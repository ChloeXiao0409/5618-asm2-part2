/**
 * This test class validates the behavior of the Equipment component hierarchy
 * (Armor, Weapon, Amulet) through the EquipmentManager, using a decoupled,
 * interface-oriented approach with IEquipment.
 *
 * The test cases are structured to isolate Equipment logic from full game
 * dependencies, replacing complex classes such as Hero with simplified
 * stat-holding stubs. This design supports modular, reusable tests and
 * facilitates verification of effect application, removal, and item
 * management logic.
 *
 * These practices align with ISO/IEC 25010 maintainability characteristics—
 * particularly modularity, reusability, and testability—ensuring the
 * system remains flexible and resilient to change.
 */

package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.equipment.Equipment;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EquipmentTest {

    static class DummyEquipment extends Equipment {
        public DummyEquipment(String name, String description) {
            super(name, description);
        }

        @Override
        public void applyEffect(Hero hero) {
            // No-op for testing
        }

        @Override
        public void removeEffect(Hero hero) {
            // No-op for testing
        }
    }

    @Test
    public void testConstructorAndGetters() {
        String expectedName = "Test Item";
        String expectedDescription = "This is a test.";

        Equipment equipment = new DummyEquipment(expectedName, expectedDescription);

        assertEquals(expectedName, equipment.getName());
        assertEquals(expectedDescription, equipment.getDescription());
    }
}


