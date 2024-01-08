package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Yoshimaru extends Creature implements AnotherCreatureEntersTheBattleField{
    public Yoshimaru() {
        super(CreatureFactory.CreatureName.YOSHIMARU);
    }

    @Override
    public void anotherCreatureEntersTheBattleField(BoardState boardState, Creature newCreature) {
        int iterations = 1;
        for (Creature creature : boardState.creatures) {
            if (creature instanceof EffectAdder) {
                if (((EffectAdder) creature).shouldAddAdditionalEffectOnETB(this)) {
                    iterations = iterations + 1;
                }
            }
        }

        if (newCreature.isLegendary) {
            counters = counters + iterations;
        }

    }
}
