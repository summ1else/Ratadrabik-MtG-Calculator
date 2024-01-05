package com.example.ratadrabikcalculator.creatures;


import java.util.ArrayList;
import java.util.List;


public class BoardState {

    int lifeGained = 0;
    int oppLifeLost = 0;

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
            if (creature instanceof AnotherCreatureEntersTheBattleField) {
                ((AnotherCreatureEntersTheBattleField) creature).anotherCreatureEntersTheBattleField(this);
            }
        });
        creatures.add(newCreature);
        if (newCreature instanceof EntersTheBattleField) {
            ((EntersTheBattleField) newCreature).entersTheBattleField(this);
        }
    }

    public void killCreature(Creature dyingCreature) {
        if (creatures.contains(dyingCreature)) {
            if(dyingCreature instanceof LeavesTheBattleField) {
                ((LeavesTheBattleField) dyingCreature).exitBattlefield(this);
            }
            creatures.forEach(creature -> {
                if (creature instanceof AnotherCreatureLeavesTheBattleField && creature != dyingCreature) {
                    ((AnotherCreatureLeavesTheBattleField) creature).anotherCreatureLeavesTheBattleField(this);
                }
            });
            creatures.remove(dyingCreature);
        }



    }

}
