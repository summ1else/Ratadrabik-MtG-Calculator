package com.example.ratadrabikcalculator.creatures;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


public class BoardState {

    private int lifeGained = 0;

    public void gainLife(int life) {
        List<Callable<Void>> callbacksAfterSpawn = new ArrayList<>();
        creatures.forEach(creature -> {
            if (creature instanceof SelfGainsLife) {
                callbacksAfterSpawn.addAll(((SelfGainsLife) creature).onGainLife(this, life));
            }
        });
        callbacksAfterSpawn.forEach(callback -> {
            try {
                callback.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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

    int targetedOppLifeLost = 0;

    public void targetOppLoseLife(int life) {
        targetedOppLifeLost = targetedOppLifeLost + life;
    }

    public int getTargetedOppLifeLost() {
        return targetedOppLifeLost;
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
        List<Creature> creaturesList = creatures.stream().filter(creature -> creature != dyingCreature).collect(Collectors.toList());
        creaturesList.forEach(creature -> {
            if (creature instanceof AnotherCreatureLeavesTheBattleField) {
                callbacksAfterDeath.addAll(((AnotherCreatureLeavesTheBattleField) creature).anotherCreatureLeavesTheBattleField(this, dyingCreature));
            }
            if (creature instanceof AnotherCreatureDies) {
                callbacksAfterDeath.addAll(((AnotherCreatureDies) creature).anotherCreatureDies(this, dyingCreature));
            }
        });
        if (dyingCreature instanceof DiesTrigger) {
            callbacksAfterDeath.addAll(((DiesTrigger) dyingCreature).dies(this));
        }

        removeCreature(dyingCreature);

        callbacksAfterDeath.forEach(callback -> {
            try {
                callback.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void killCreatures(List<Creature> dyingCreatures) {
        List<Callable<Void>> callbacksAfterDeath = new ArrayList<>();
        for (Creature currentlyDyingCreature : dyingCreatures) {
            List<Creature> creaturesList = creatures.stream().filter(creature -> creature != currentlyDyingCreature).collect(Collectors.toList());
            creaturesList.forEach(creature -> {
                if (creature instanceof AnotherCreatureLeavesTheBattleField) {
                    callbacksAfterDeath.addAll(((AnotherCreatureLeavesTheBattleField) creature).anotherCreatureLeavesTheBattleField(this, currentlyDyingCreature));
                }
                if (creature instanceof AnotherCreatureDies) {
                    callbacksAfterDeath.addAll(((AnotherCreatureDies) creature).anotherCreatureDies(this, currentlyDyingCreature));
                }
            });
        }
        for (Creature currentlyDyingCreature : dyingCreatures) {
            if (currentlyDyingCreature instanceof DiesTrigger) {
                callbacksAfterDeath.addAll(((DiesTrigger) currentlyDyingCreature).dies(this));
            }
        }

        for (Creature currentlyDyingCreature : dyingCreatures) {
            removeCreature(currentlyDyingCreature);
        }

        callbacksAfterDeath.forEach(callback -> {
            try {
                callback.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void myriadCreature(Creature myriadCreature) {
        Creature myriad1 = CreatureFactory.createCreature(myriadCreature.name);
        Creature myriad2 = CreatureFactory.createCreature(myriadCreature.name);
        addCreature(myriad1);
        addCreature(myriad2);
        if (myriadCreature.isLegendary) {
            myriad1.isLegendary = true;
            myriad2.isLegendary = true;
        }
        if (myriadCreature.isToken) {
            myriad1.isToken = true;
            myriad2.isToken = true;
        }
        if (myriadCreature.isZombieType) {
            myriad1.isZombieType = true;
            myriad2.isZombieType = true;
        }

        spawnCreature(myriad1);
        spawnCreature(myriad2);
        if (myriadCreature.isLegendary) {
            killCreatures(Arrays.asList(myriad1, myriad2));
        } else {
            removeCreature(myriad1);
            removeCreature(myriad2);
        }
    }

    public void resetNotes() {
        creatures.forEach(creature -> {
            creature.notes = new ArrayList<>();
        });
    }

}
