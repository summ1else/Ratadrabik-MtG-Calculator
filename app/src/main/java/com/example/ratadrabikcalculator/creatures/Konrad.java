package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class Konrad extends Creature implements AnotherCreatureDies {


    public Konrad() {
        super(CreatureFactory.CreatureName.KONRAD);
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
        boardState.oppLoseLife(iterations);
        return new ArrayList<>();
    }
}
