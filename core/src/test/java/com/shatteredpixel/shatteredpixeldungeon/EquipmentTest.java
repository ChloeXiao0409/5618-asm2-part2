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


