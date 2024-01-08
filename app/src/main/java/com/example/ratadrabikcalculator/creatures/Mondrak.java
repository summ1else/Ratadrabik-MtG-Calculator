package com.example.ratadrabikcalculator.creatures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Mondrak extends Creature implements TokenMultiplier {


    public Mondrak() {
        super(CreatureFactory.CreatureName.MONDRAK);
    }

    @Override
    public int shouldMultiplyTokens(Creature creature) {
        return 2;
    }
}
