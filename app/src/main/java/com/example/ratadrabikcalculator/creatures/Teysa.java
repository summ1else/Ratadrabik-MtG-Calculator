package com.example.ratadrabikcalculator.creatures;

public class Teysa extends Creature implements EffectAdder{
    public Teysa() {
        super(CreatureFactory.CreatureName.TEYSA);
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
