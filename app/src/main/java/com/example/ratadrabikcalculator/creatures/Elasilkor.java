package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;


public class Elasilkor extends Creature implements AnotherCreatureEntersTheBattleField, AnotherCreatureDies {


    public Elasilkor() {
        super(CreatureFactory.CreatureName.ELASILKOR);
    }


    @Override
    public void anotherCreatureEntersTheBattleField(BoardState boardState, Creature newCreature) {
        int ELAS_LIFE_ADD = 1;
        int iterations = 1;
        for (Creature creature : boardState.creatures) {
            if (creature != newCreature && creature instanceof EffectAdder) {
                if (((EffectAdder) creature).shouldAddAdditionalEffectOnETB(this)) {
                    this.notes.add("Additional effect from " + creature.name);
                    iterations++;
                }
            }
        }
        boardState.gainLife(ELAS_LIFE_ADD * iterations);
        this.notes.add("Added " + ELAS_LIFE_ADD * iterations + " life from board");
    }

    @Override
    public List<Callable<Void>> anotherCreatureDies(BoardState boardState, Creature dyingCreature) {
        int ELAS_LIFE_LOSS_OPP = 1;
        int iterations = 1;
        for (Creature creature : boardState.creatures) {
            if (creature != dyingCreature && creature instanceof EffectAdder) {
                if (((EffectAdder) creature).shouldAddAdditionalEffectOnDeath(this)) {
                    iterations++;
                }
            }
        }
        boardState.oppLoseLife(ELAS_LIFE_LOSS_OPP * iterations);
        return new ArrayList<>();
    }
}
