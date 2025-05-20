package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.utils.Random;

public class DamageCalculator {

    public static int calculate(int min, int max, Augment augment, boolean isHero) {
        int baseDamage = isHero
            ? Hero.heroDamageIntRange(min, max)
            : Random.NormalIntRange(min, max);

        if (augment == Augment.DAMAGE) {
            baseDamage *= 1.1f;
        }

        return baseDamage;
    }
}