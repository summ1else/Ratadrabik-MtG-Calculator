package com.example.ratadrabikcalculator.creatures;

public class Drivnod extends Creature implements EffectAdder{
    public Drivnod() {
        super(CreatureFactory.CreatureName.DRIVNOD);
    }

    @Override
    public boolean shouldAddAdditionalEffectOnDeath(Creature creature) {
        return true;
    }

    @Override
    public boolean shouldAddAdditionalEffectOnETB(Creature creature) {
        return false;
    }
}
