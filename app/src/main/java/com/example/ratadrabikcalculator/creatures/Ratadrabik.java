package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import lombok.Builder;

public class Ratadrabik extends Creature implements AnotherCreatureDies {


    public Ratadrabik(CreatureFactory.CreatureName creatureName) {
        super(creatureName);
        this.isZombieType = true;
    }

    @Override
    public List<Callable<Void>> anotherCreatureDies(BoardState boardState, Creature dyingCreature) {
        List<Callable<Void>> currentStateTriggers = new ArrayList<>();
        if (dyingCreature.isLegendary) {
            int iterations = 1;
            for (Creature creature : boardState.creatures) {
                if (creature instanceof EffectAdder) {
                    if (((EffectAdder) creature).shouldAddAdditionalEffect(this)) {
                        iterations++;
                    }
                }
            }
            for (int i = 0; i < iterations; i++) {
                Creature creature = CreatureFactory.createCreature(dyingCreature.name);
                creature.isToken = true;
                creature.isZombieType = true;
                boardState.creatures.add(creature);
                currentStateTriggers.add(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        boardState.spawnCreature(creature);
                        return null;
                    }
                });

            }

        }
        return currentStateTriggers;
    }

}