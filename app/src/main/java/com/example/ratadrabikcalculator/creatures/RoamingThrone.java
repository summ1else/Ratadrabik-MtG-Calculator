package com.example.ratadrabikcalculator.creatures;

public class RoamingThrone extends Creature implements EffectAdder{
    public RoamingThrone(CreatureFactory.CreatureName creatureName) {
        super(creatureName);
    }

    @Override
    public boolean shouldAddAdditionalEffect(Creature creature) {
        return creature.isZombieType;
    }
}
