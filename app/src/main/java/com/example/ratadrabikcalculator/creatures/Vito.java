package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Vito extends Creature implements SelfGainsLife{
    public Vito() {
        super(CreatureFactory.CreatureName.VITO);
    }

    @Override
    public List<Callable<Void>> onGainLife(BoardState boardState, int amount) {
        int iterations = 1;
        for (Creature creature : boardState.creatures) {
            if (creature instanceof EffectAdder) {
                if (((EffectAdder) creature).shouldAddAdditionalEffectOnDeath(this)) {
                    iterations++;
                }
            }
        }
        boardState.targetOppLoseLife(iterations * amount);
        return new ArrayList<>();
    }
}
