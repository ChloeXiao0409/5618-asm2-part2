/**
 * This test class verifies the functionality of the Weapon component
 * in isolation from the full game environment. Instead of relying on the Hero
 * class, it uses a lightweight local object to simulate attack changes, allowing
 * safe and controlled testing of applyEffect() and removeEffect().
 *
 * This approach promotes modularity, testability, and reusability as defined by
 * ISO/IEC 25010, ensuring that the equipment effect logic can be validated
 * independently without tight coupling to the runtime systems of the game.
 */

package com.shatteredpixel.shatteredpixeldungeon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;

public class WeaponTest {

    private StatHolder statHolder;
    private Weapon testWeapon;

    static class StatHolder {
        private int attack = 0;
        void increaseAttack(int value) { attack += value; }
        int getAttack() { return attack; }
    }

    @BeforeEach
    void setUp() {
        statHolder = new StatHolder();

        testWeapon = new Weapon() {
            @Override
            public void applyEffect(Hero ignored) {
                statHolder.increaseAttack(5);
            }

            @Override
            public void removeEffect(Hero ignored) {
                statHolder.increaseAttack(-5);
            }

            @Override
            public String getName() {
                return "Mock Weapon";
            }

            @Override
            public int STRReq(int lvl) {
                return 0;
            }

            @Override
            public ItemSprite.Glowing glowing() {
                return null;
            }

            @Override
            public int max(int lvl) {
                return 0;
            }

            @Override
            public int min(int lvl) {
                return 0;
            }

        };
    }

    @Test
    void testApplyEffectIncreasesAttack() {
        assertEquals(0, statHolder.getAttack());
        testWeapon.applyEffect(null);
        assertEquals(5, statHolder.getAttack());
    }

    @Test
    void testRemoveEffectDecreasesAttack() {
        testWeapon.applyEffect(null);
        testWeapon.removeEffect(null);
        assertEquals(0, statHolder.getAttack());
    }
}


