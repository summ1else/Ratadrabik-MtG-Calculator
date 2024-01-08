package com.example.ratadrabikcalculator.creatures;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


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
        List<Callable<Void>> callbacksAfterSpawn = new ArrayList<>();
        creatures.forEach(creature -> {
            if (newCreature != creature && creature instanceof AnotherCreatureEntersTheBattleField) {
                ((AnotherCreatureEntersTheBattleField) creature).anotherCreatureEntersTheBattleField(this, newCreature);
            }
        });
        if (newCreature instanceof EntersTheBattleField) {
            callbacksAfterSpawn.addAll(((EntersTheBattleField) newCreature).entersTheBattleField(this));
        }
        callbacksAfterSpawn.forEach(callback -> {
            try {
                callback.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void killCreature(Creature dyingCreature) {
        List<Callable<Void>> callbacksAfterDeath = new ArrayList<>();
        if (dyingCreature instanceof DiesTrigger) {
           callbacksAfterDeath.addAll(((DiesTrigger) dyingCreature).dies(this));
        }
        List<Creature> creaturesList = creatures.stream().filter(creature -> creature != dyingCreature).collect(Collectors.toList());
        creaturesList.forEach(creature -> {
            if (creature instanceof AnotherCreatureLeavesTheBattleField) {
                callbacksAfterDeath.addAll(((AnotherCreatureLeavesTheBattleField) creature).anotherCreatureLeavesTheBattleField(this, dyingCreature));
            }
            if (creature instanceof AnotherCreatureDies) {
                callbacksAfterDeath.addAll(((AnotherCreatureDies) creature).anotherCreatureDies(this, dyingCreature));
            }
        });

        removeCreature(dyingCreature);

        callbacksAfterDeath.forEach(callback -> {
            try {
                callback.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void resetNotes() {
        creatures.forEach(creature -> {
            creature.notes = new ArrayList<>();
        });
    }

}
