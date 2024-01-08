package com.example.ratadrabikcalculator.creatures;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Creature {

    public Creature(CreatureFactory.CreatureName creatureName) {
        this.name = creatureName;
    }
    UUID id = UUID.randomUUID();

    List<String> notes = new ArrayList<>();
    CreatureFactory.CreatureName name;

    int counters = 0;

    boolean isZombieType = false;
    boolean isLegendary = false;

    boolean isToken = false;

    boolean isFlying = false;

}

