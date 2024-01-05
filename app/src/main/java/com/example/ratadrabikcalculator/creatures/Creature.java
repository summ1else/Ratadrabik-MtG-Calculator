package com.example.ratadrabikcalculator.creatures;


import java.util.UUID;

import lombok.Data;

@Data
public class Creature {
    UUID id = UUID.randomUUID();
    boolean isLegendary = false;

    boolean isToken = false;

    boolean isFlying = false;

}

