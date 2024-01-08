package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Ratadrabik extends Creature implements AnotherCreatureDies {


    public Ratadrabik() {
        super(CreatureFactory.CreatureName.RATADRABIK);
        this.isZombieType = true;
    }

    // TODO: need to track whether creatures have spawned yet now? annoying. Ratadrabik adding creatures to board state and then spawning them later.

    @Override
    public List<Callable<Void>> anotherCreatureDies(BoardState boardState, Creature dyingCreature) {
        List<Callable<Void>> currentStateTriggers = new ArrayList<>();
        if (dyingCreature.isLegendary) {
            int iterations = 1;
            int multipliers = 1;
            for (Creature creature : boardState.creatures) {
                if (creature instanceof EffectAdder) {
                    if (((EffectAdder) creature).shouldAddAdditionalEffectOnDeath(this)) {
                        iterations++;
                    }
                }
                if (creature instanceof TokenMultiplier) {
                    multipliers = multipliers * ((TokenMultiplier) creature).shouldMultiplyTokens(this);
                }
            }
            iterations = iterations * multipliers;
            if (boardState.isAnointedProcessionPresent) {
                iterations = iterations * 2;
            }
            for (int i = 0; i < iterations; i++) {
                Creature creature = CreatureFactory.createCreature(dyingCreature.name);
                creature.isToken = true;
                creature.isZombieType = true;
                boardState.creatures.add(creature);
                currentStateTriggers.add(() -> {
                    boardState.spawnCreature(creature);
                    return null;
                });

            }

        }
        return currentStateTriggers;
    }

}
