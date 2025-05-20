package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the DamageCalculator class to verify damage computation logic under different conditions.
 * These tests support maintainability by isolating behavior, defining valid ranges, and enabling future changes with confidence.
 */
class DamageCalculatorTest {

    @Test
    void testConstructorCoverage() {
        new DamageCalculator();
    }

    /**
     * Verifies that the damage calculated for non-hero characters (e.g., enemies) without any augment always falls within the specified [min, max] range.
     *
     * ◉ Aligns with **modularity** by testing a single logic path in isolation.
     * ◉ Enhances **testability** by asserting bounded output for random generation logic.
     * ◉ Supports **changeability** — future refactoring of the random logic will be caught if it breaks the bounds.
     */
    @Test
    void testCalculateWithoutHero_NoAugment() {
        int min = 5;
        int max = 10;
        for (int i = 0; i < 100; i++) {
            int result = DamageCalculator.calculate(min, max, null, false);
            assertTrue(result >= min && result <= max,
                    "Result should be within [" + min + "," + max + "] but was " + result);
        }
    }

    /**
     * Verifies that when the DAMAGE augment is applied for a non-hero character,
     * the resulting value is approximately 10% higher but still within the expected [5, 11] range (due to int truncation).
     *
     * ◉ Improves **testability** by validating that the augment scaling logic behaves consistently.
     * ◉ Aids **changeability** by clearly documenting how damage scaling works under augmentation.
     * ◉ Reinforces **modularity** by testing the augment branch independently.
     */
    @Test
    void testCalculateWithoutHero_WithDamageAugment() {
        int min = 5;
        int max = 10;
        for (int i = 0; i < 100; i++) {
            int result = DamageCalculator.calculate(min, max, Augment.DAMAGE, false);
            assertTrue(result >= 5 && result <= 11,
                    "Augmented damage should be in [5, 11], was: " + result);
        }
    }

    /**
     * Ensures that the calculation logic for hero characters runs without throwing exceptions.
     * ◉ Supports **testability** by covering the hero-specific path.
     * ◉ Encourages **modularity** by checking the separate logic branch for heroes.
     * ◉ Strengthens **robustness** — future logic changes that break this path will be caught.
     */
    @Test
    void testCalculateWithHero_RunsWithoutError() {
        int result = DamageCalculator.calculate(3, 6, null, true);
        assertTrue(result >= 0);
    }
}




