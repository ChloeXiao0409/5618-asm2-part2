package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AugmentTest {

    @Test
    void testAugmentValues() {
        Augment[] values = Augment.values();
        assertEquals(3, values.length);
        assertArrayEquals(
            new Augment[]{Augment.SPEED, Augment.DAMAGE, Augment.NONE},
            values
        );
    }

    @Test
    void testAugmentValueOf() {
        assertEquals(Augment.SPEED, Augment.valueOf("SPEED"));
        assertEquals(Augment.DAMAGE, Augment.valueOf("DAMAGE"));
        assertEquals(Augment.NONE, Augment.valueOf("NONE"));
    }

    @Test
    void testInvalidAugmentValueOf() {
        assertThrows(IllegalArgumentException.class, () -> {
            Augment.valueOf("INVALID");
        });
    }

    @Test
    void testDamageFactorCalculation() {
        assertEquals(7, Augment.SPEED.damageFactor(10));    // 10 * 0.7 = 7
        assertEquals(15, Augment.DAMAGE.damageFactor(10));  // 10 * 1.5 = 15
        assertEquals(10, Augment.NONE.damageFactor(10));    // 10 * 1.0 = 10
    }

    @Test
    void testDelayFactorCalculation() {
        assertEquals(2f / 3f, Augment.SPEED.delayFactor(1.0f), 0.0001f);
        assertEquals(5f / 3f, Augment.DAMAGE.delayFactor(1.0f), 0.0001f);
        assertEquals(1.0f,     Augment.NONE.delayFactor(1.0f), 0.0001f);
    }


}
