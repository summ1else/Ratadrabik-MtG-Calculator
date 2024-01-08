package com.example.ratadrabikcalculator.creatures;

public class RoamingThrone extends Creature implements EffectAdder{
    public RoamingThrone() {
        super(CreatureFactory.CreatureName.ROAMING_THRONE);
    }

    @Override
    public boolean shouldAddAdditionalEffectOnDeath(Creature creature) {
        return creature != this && creature.isZombieType;
    }

    @Override
    public boolean shouldAddAdditionalEffectOnETB(Creature creature) {
        return creature != this && creature.isZombieType;
    }
}
