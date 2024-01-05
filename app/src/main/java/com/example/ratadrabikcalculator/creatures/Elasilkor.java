package com.example.ratadrabikcalculator.creatures;

import lombok.Builder;

@Builder
public class Elasilkor extends Creature implements AnotherCreatureEntersTheBattleField, AnotherCreatureLeavesTheBattleField {

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
        boardState.lifeGained = boardState.lifeGained + (ELAS_LIFE_ADD * iterations);
    }

    @Override
    public void anotherCreatureLeavesTheBattleField(BoardState boardState) {
        boardState.oppLifeLost++;
    }
}
