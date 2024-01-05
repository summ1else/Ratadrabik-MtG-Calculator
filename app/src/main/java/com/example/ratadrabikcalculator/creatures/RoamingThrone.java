package com.example.ratadrabikcalculator.creatures;

public class RoamingThrone extends Creature implements EffectAdder{
    @Override
    public boolean shouldAddAdditionalEffect(Creature creature) {
        return creature.isToken;
    }
}
