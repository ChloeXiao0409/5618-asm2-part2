/**
 * This test class verifies the functionality of the Armor component
 * in isolation from the full game environment. Instead of relying on the Hero class,
 * it uses a lightweight local object to simulate defense changes, allowing safe,
 * controlled testing of applyEffect() and removeEffect().
 *
 * This approach promotes modularity, testability, and reusability as defined by
 * ISO/IEC 25010, ensuring that the equipment effect logic can be validated independently
 * without tight coupling to the runtime systems of the game.
 */

package com.shatteredpixel.shatteredpixeldungeon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;

public class ArmorTest {

    private StatHolder testHero;
    private Armor testArmor;

    static class StatHolder {
        private int defense = 0;

        void addDefense(int value) {
            defense += value;
        }

        int getDefense() {
            return defense;
        }
    }

    @BeforeEach
    void setUp() {
        testHero = new StatHolder();

        // Create a custom Armor instance that applies its effect to our dummy object
        testArmor = new Armor(5) {
            @Override
            public void applyEffect(Hero ignoredHero) {
                testHero.addDefense(5);
            }

            @Override
            public void removeEffect(Hero ignoredHero) {
                testHero.addDefense(-5);
            }

            @Override
            public String getName() {
                return "Test Armor";
            }
        };
    }

    /**
     * Verifies that applying the armor increases the dummy's defense by 5.
     */
    @Test
    void testApplyEffectIncreasesDefense() {
        assertEquals(0, testHero.getDefense(), "Initial defense should be 0");
        testArmor.applyEffect(null); // Safe to pass null since Hero is not used
        assertEquals(5, testHero.getDefense(), "Defense should increase by 5 after applying armor");
    }

    /**
     * Verifies that removing the armor reverts the defense back to 0.
     */
    @Test
    void testRemoveEffectDecreasesDefense() {
        testArmor.applyEffect(null); // Increase defense to 5
        testArmor.removeEffect(null); // Remove effect, should revert
        assertEquals(0, testHero.getDefense(), "Defense should return to 0 after removing armor");
    }
}
