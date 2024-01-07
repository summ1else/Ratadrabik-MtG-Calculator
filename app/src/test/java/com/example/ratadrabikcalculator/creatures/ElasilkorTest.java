package com.example.ratadrabikcalculator.creatures;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

public class ElasilkorTest {


    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void lifeGainedIsCorrect() {

        Elasilkor elasilkor = new Elasilkor(CreatureFactory.CreatureName.ELASILKOR);
        assertEquals(0, elasilkor.enteringLifeGained(0));
        assertEquals(0, elasilkor.enteringLifeGained(1));
        assertEquals(1, elasilkor.enteringLifeGained(2));
        assertEquals(3, elasilkor.enteringLifeGained(3));
        assertEquals(6, elasilkor.enteringLifeGained(4));
        assertEquals(10, elasilkor.enteringLifeGained(5));
    }


    @Test
    public void elasIlKorBoardState() {
        BoardState boardState = new BoardState();

        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR));
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR));
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR));

        boardState.spawnCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR));
        assertEquals(3, boardState.getLifeGained());
        boardState.spawnCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR));

        assertEquals(7, boardState.getLifeGained());
        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());

    }

    @Test
    public void elasIlKorBoardStateRoamingThrone() {
        BoardState boardState = new BoardState();

        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));
        Creature elasilkor1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor1.isToken = true;
        Creature elasilkor2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor2.isToken = true;
        Creature elasilkor3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor3.isToken = true;
        boardState.addCreature(elasilkor1);
        boardState.addCreature(elasilkor2);
        boardState.addCreature(elasilkor3);

        Creature spawningIlkor = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        spawningIlkor.isToken = true;
        boardState.spawnCreature(spawningIlkor);
        assertEquals(6, boardState.getLifeGained());

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());
    }

    @Test
    public void elasIlKorBoardStateRoamingThroneNonToken() {
        BoardState boardState = new BoardState();

        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));
        Creature elasilkor1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor1.isToken = true;
        Creature elasilkor2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor2.isToken = true;
        Creature elasilkor3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor3.isToken = true;
        boardState.addCreature(elasilkor1);
        boardState.addCreature(elasilkor2);
        boardState.addCreature(elasilkor3);

        Creature spawningIlkor = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        spawningIlkor.isToken = false;
        boardState.spawnCreature(spawningIlkor);
        assertEquals(6, boardState.getLifeGained());

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());
    }


    @Test
    public void elasIlKorBoardStateRatadrabikNonToken() {
        BoardState boardState = new BoardState();

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);

        Creature elasilkor1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor1.isLegendary = true;
        boardState.addCreature(elasilkor1);

        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        boardState.removeCreature(elasilkor1);
        boardState.killCreature(elasilkor1);

        System.out.println(gson.toJson(boardState));

    }


}