package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Augment enum, verifying value integrity and augmentation logic.
 * These tests enhance maintainability by ensuring reliable behavior of critical multipliers
 * and reducing the risk of silent errors from incorrect enum usage.
 */
public class AugmentTest {

    /**
     * Validates that the Augment enum has exactly 3 values in the expected order.
     *
     * ◉ Supports **modularity** by isolating the enum’s integrity check.
     * ◉ Enhances **testability** by ensuring any addition or reordering of values causes a test failure.
     * ◉ Improves **changeability**, as future modifications to the enum must update this test.
     */
    @Test
    void testAugmentValues() {
        Augment[] values = Augment.values();
        assertEquals(3, values.length);
        assertArrayEquals(
            new Augment[]{Augment.SPEED, Augment.DAMAGE, Augment.NONE},
            values
        );
    }

    /**
     * Ensures that each Augment value can be correctly resolved from its string name.
     *
     * ◉ Promotes **testability** by verifying string-enum conversion for valid inputs.
     * ◉ Aids **modularity**, as it tests the built-in enum functionality in isolation.
     * ◉ Increases **changeability** since renaming enum values will require this test to be updated.
     */
    @Test
    void testAugmentValueOf() {
        assertEquals(Augment.SPEED, Augment.valueOf("SPEED"));
        assertEquals(Augment.DAMAGE, Augment.valueOf("DAMAGE"));
        assertEquals(Augment.NONE, Augment.valueOf("NONE"));
    }

    /**
     * Ensures that an invalid string throws the expected IllegalArgumentException.
     *
     * ◉ Strengthens **testability** by explicitly verifying failure conditions.
     * ◉ Supports **robustness** and safe handling of invalid inputs.
     */
    @Test
    void testInvalidAugmentValueOf() {
        assertThrows(IllegalArgumentException.class, () -> {
            Augment.valueOf("INVALID");
        });
    }

    /**
     * Validates that the damageFactor method applies the correct multiplier per augment type.
     *
     * ◉ Promotes **modularity** by testing pure mathematical logic in isolation.
     * ◉ Increases **testability** by asserting deterministic output for controlled inputs.
     * ◉ Supports **changeability**, allowing safe refactoring of multiplier constants.
     */
    @Test
    void testDamageFactorCalculation() {
        assertEquals(7, Augment.SPEED.damageFactor(10));    // 10 * 0.7 = 7
        assertEquals(15, Augment.DAMAGE.damageFactor(10));  // 10 * 1.5 = 15
        assertEquals(10, Augment.NONE.damageFactor(10));    // 10 * 1.0 = 10
    }

    /**
     * Validates that the delayFactor method applies the correct delay multiplier for each augment.
     *
     * ◉ Improves **testability** by validating numeric output with floating-point precision.
     * ◉ Enhances **modularity** as this logic can be reused across different weapon systems.
     * ◉ Supports **reusability** of augment behavior in future time-based computations.
     */
    @Test
    void testDelayFactorCalculation() {
        assertEquals(2f / 3f, Augment.SPEED.delayFactor(1.0f), 0.0001f);
        assertEquals(5f / 3f, Augment.DAMAGE.delayFactor(1.0f), 0.0001f);
        assertEquals(1.0f,     Augment.NONE.delayFactor(1.0f), 0.0001f);
    }
}

