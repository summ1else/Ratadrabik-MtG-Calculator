package com.example.ratadrabikcalculator.creatures;

public interface EffectAdder {


    boolean shouldAddAdditionalEffectOnDeath(Creature creature);

    boolean shouldAddAdditionalEffectOnETB(Creature creature);

}
