package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Nazgul extends Creature implements EntersTheBattleField{
    public Nazgul() {
        super(CreatureFactory.CreatureName.NAZGUL);
    }

    @Override
    public List<Callable<Void>> entersTheBattleField(BoardState boardState) {
        int iterations = 1;
        for (Creature creature : boardState.creatures) {
            if (creature instanceof EffectAdder) {
                if (((EffectAdder) creature).shouldAddAdditionalEffectOnETB(this)) {
                    iterations = iterations * 2;
                }
            }
        }

        List<Callable<Void>> callbacksAfterEtb = new ArrayList<>();
        for (Creature creature : boardState.creatures) {
            if (creature.name != CreatureFactory.CreatureName.NAZGUL) continue;
            int finalIterations = iterations;
            for (int i = 0; i < finalIterations; i++) {
                callbacksAfterEtb.add(() -> {
                    creature.counters = creature.counters + finalIterations;
                    return null;
                });
            }
        }
        callbacksAfterEtb.add(() -> {
            if (boardState.creatures.stream().noneMatch(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary)
                    && boardState.creatures.stream().anyMatch(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL)) {
                boardState.creatures
                        .stream()
                        .filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL)
                        .findFirst()
                        .ifPresentOrElse(creature -> creature.isLegendary = true, () -> {});
            }
            return null;
        });
        return callbacksAfterEtb;
    }
}
