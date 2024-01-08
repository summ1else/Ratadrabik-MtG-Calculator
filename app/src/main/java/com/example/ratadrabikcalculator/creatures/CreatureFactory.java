package com.example.ratadrabikcalculator.creatures;

public class CreatureFactory {
    public static Creature createCreature(CreatureName creatureName) throws RuntimeException {
        switch (creatureName) {
            case ELASILKOR:
                return new Elasilkor();
            case RATADRABIK:
                return new Ratadrabik();
            case ROAMING_THRONE:
                return new RoamingThrone();
            case TEYSA:
                return new Teysa();
            case DRIVNOD:
                return new Drivnod();
            case MONDRAK:
                return new Mondrak();
            case NAZGUL:
                return new Nazgul();
            case YOSHIMARU:
                return new Yoshimaru();
            default: throw new RuntimeException("Name not found");
        }
    }

    public enum CreatureName {
        ELASILKOR,
        RATADRABIK,
        ROAMING_THRONE,
        TEYSA,
        DRIVNOD,
        MONDRAK,
        NAZGUL,
        YOSHIMARU
    }
}
