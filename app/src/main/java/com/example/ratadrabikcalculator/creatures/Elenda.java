package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class Elenda extends Creature implements DiesTrigger, AnotherCreatureDies {


    public Elenda() {
        super(CreatureFactory.CreatureName.ELENDA);
    }

    @Override
    public List<Callable<Void>> anotherCreatureDies(BoardState boardState, Creature dyingCreature) {
        int iterations = 1;
        for (Creature creature : boardState.creatures) {
            if (creature != dyingCreature && creature instanceof EffectAdder) {
                if (((EffectAdder) creature).shouldAddAdditionalEffectOnDeath(this)) {
                    iterations++;
                }
            }
        }
        this.counters = this.counters + iterations;
        return new ArrayList<>();
    }

    @Override
    public List<Callable<Void>> dies(BoardState boardState) {
        List<Callable<Void>> callbacks = new ArrayList<>();
        int iterations = 1;
        for (Creature creature : boardState.creatures) {
            if (creature instanceof EffectAdder) {
                if (((EffectAdder) creature).shouldAddAdditionalEffectOnDeath(this)) {
                    iterations++;
                }
            }
        }
        for (int i = 0; i < (this.counters + 1) * iterations; i++) {
            callbacks.add(() -> {
                Creature vampire = CreatureFactory.createCreature(CreatureFactory.CreatureName.VAMPIRE);
                vampire.isToken = true;
                boardState.creatures.add(vampire);
                boardState.spawnCreature(vampire);
                return null;
            });
        }

        return callbacks;
    }
}