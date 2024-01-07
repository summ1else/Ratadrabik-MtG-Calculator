package com.example.ratadrabikcalculator.creatures;

public class CreatureFactory {
    public static Creature createCreature(CreatureName creatureName) throws RuntimeException {
        switch (creatureName) {
            case ELASILKOR:
                return new Elasilkor(CreatureName.ELASILKOR);
            case RATADRABIK:
                return new Ratadrabik(CreatureName.RATADRABIK);
            case ROAMING_THRONE:
                return new RoamingThrone(CreatureName.ROAMING_THRONE);
            default: throw new RuntimeException("Name not found");
        }
    }

    public enum CreatureName {
        ELASILKOR,
        RATADRABIK,
        ROAMING_THRONE
    }
}
