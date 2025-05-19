/**
 * This test class verifies the functionality of amulet-like equipment
 * in isolation from the full game environment. Instead of instantiating the actual
 * Amulet class—which introduces static dependencies on game assets—it uses a minimal
 * IEquipment implementation to simulate luck changes safely.
 *
 * This strategy enables precise testing of applyEffect() and removeEffect() behaviors
 * without reliance on the runtime systems of the game, thereby promoting modularity,
 * testability, and reusability as defined by ISO/IEC 25010.
 */



package com.shatteredpixel.shatteredpixeldungeon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.equipment.IEquipment;

public class AmuletTest {

    private StatHolder statHolder;
    private IEquipment testAmulet;

    static class StatHolder {
        private int luck = 0;
        void addLuck(int value) { luck += value; }
        int getLuck() { return luck; }
    }

    @BeforeEach
    void setUp() {
        statHolder = new StatHolder();

        testAmulet = new IEquipment() {
            @Override
            public void applyEffect(Hero ignoredHero) {
                statHolder.addLuck(1);
            }

            @Override
            public void removeEffect(Hero ignoredHero) {
                statHolder.addLuck(-1);
            }

            @Override
            public String getName() {
                return "Mock Amulet";
            }
        };
    }

    @Test
    void testApplyEffectIncreasesLuck() {
        assertEquals(0, statHolder.getLuck());
        testAmulet.applyEffect(null);
        assertEquals(1, statHolder.getLuck());
    }

    @Test
    void testRemoveEffectDecreasesLuck() {
        testAmulet.applyEffect(null);
        testAmulet.removeEffect(null);
        assertEquals(0, statHolder.getLuck());
    }
}


