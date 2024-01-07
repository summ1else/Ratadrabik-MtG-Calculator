package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;


public class Elasilkor extends Creature implements AnotherCreatureEntersTheBattleField, AnotherCreatureLeavesTheBattleField {


    public Elasilkor(CreatureFactory.CreatureName creatureName) {
        super(creatureName);
    }

    public int enteringLifeGained(int triggeredEntries) {
        if (triggeredEntries == 0 || triggeredEntries == 1) {
            return 0;
        } else {
            return (triggeredEntries * (triggeredEntries -1)) / 2;
        }
    }


    public int leavingLifeLost(int currentCopiesIncludingDyingIfNecessary) {
        return currentCopiesIncludingDyingIfNecessary;
    }

    @Override
    public void anotherCreatureEntersTheBattleField(BoardState boardState) {
        int ELAS_LIFE_ADD = 1;
        int iterations = 1;
        for (Creature creature : boardState.creatures) {
            if (creature instanceof EffectAdder) {
                if (((EffectAdder) creature).shouldAddAdditionalEffect(this)) {
                    iterations++;
                }
            }
        }
        boardState.gainLife(ELAS_LIFE_ADD * iterations);
    }

    @Override
    public List<Callable<Void>> anotherCreatureLeavesTheBattleField(BoardState boardState, Creature creature) {
        boardState.oppLifeLost++;
        return new ArrayList<>();
    }
}
