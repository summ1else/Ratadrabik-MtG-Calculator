package com.example.ratadrabikcalculator.creatures;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;


public class BoardState {

    private int lifeGained = 0;

    public void gainLife(int life) {
        lifeGained = lifeGained + life;
    }

    public int getLifeGained() {
        return this.lifeGained;
    }

    int oppLifeLost = 0;

    public void oppLoseLife(int life) {
        oppLifeLost = oppLifeLost + life;
    }

    public int getOppLifeLost() {
        return this.oppLifeLost;
    }

    List<Creature> creatures = new ArrayList<Creature>();

    boolean isAnointedProcessionPresent = false;


    public void addCreature(Creature creature) {
        creatures.add(creature);
    }

    public void removeCreature(Creature creature) {
        creatures.remove(creature);
    }

    public void spawnCreature(Creature newCreature) {
        creatures.forEach(creature -> {
            if (newCreature != creature && creature instanceof AnotherCreatureEntersTheBattleField) {
                ((AnotherCreatureEntersTheBattleField) creature).anotherCreatureEntersTheBattleField(this);
            }
        });
        if (newCreature instanceof EntersTheBattleField) {
            ((EntersTheBattleField) newCreature).entersTheBattleField(this);
        }
    }

    public void killCreature(Creature dyingCreature) {

        List<Callable<Void>> callbacksAfterDeath = new ArrayList<>();
        if (dyingCreature instanceof LeavesTheBattleField) {
            ((LeavesTheBattleField) dyingCreature).exitBattlefield(this);
        }
        List<Creature> creaturesList = new ArrayList<>(creatures);
        creaturesList.forEach(creature -> {
            if (creature instanceof AnotherCreatureLeavesTheBattleField && creature != dyingCreature) {
                callbacksAfterDeath.addAll(((AnotherCreatureLeavesTheBattleField) creature).anotherCreatureLeavesTheBattleField(this, dyingCreature));
            }
            if (creature instanceof AnotherCreatureDies && creature != dyingCreature) {
                callbacksAfterDeath.addAll(((AnotherCreatureDies) creature).anotherCreatureDies(this, dyingCreature));
            }
        });


        callbacksAfterDeath.forEach(callback -> {
            try {
                callback.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}
