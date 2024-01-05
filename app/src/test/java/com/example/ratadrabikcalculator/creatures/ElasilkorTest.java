package com.example.ratadrabikcalculator.creatures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ElasilkorTest {

    @Test
    public void lifeGainedIsCorrect() {

        Elasilkor elasilkor = new Elasilkor();
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

        boardState.addCreature(new Elasilkor());
        boardState.addCreature(new Elasilkor());
        boardState.addCreature(new Elasilkor());

        boardState.spawnCreature(new Elasilkor());
        assertEquals(3, boardState.lifeGained);
        boardState.spawnCreature(new Elasilkor());

        assertEquals(7, boardState.lifeGained);
        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());

    }

    @Test
    public void elasIlKorBoardStateRoamingThrone() {
        BoardState boardState = new BoardState();

        boardState.addCreature(new RoamingThrone());
        Elasilkor elasilkor1 = new Elasilkor();
        elasilkor1.isToken = true;
        Elasilkor elasilkor2 = new Elasilkor();
        elasilkor2.isToken = true;
        Elasilkor elasilkor3 = new Elasilkor();
        elasilkor3.isToken = true;
        boardState.addCreature(elasilkor1);
        boardState.addCreature(elasilkor2);
        boardState.addCreature(elasilkor3);

        Elasilkor spawningIlkor = new Elasilkor();
        spawningIlkor.isToken = true;
        boardState.spawnCreature(spawningIlkor);
        assertEquals(6, boardState.lifeGained);

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());
    }

    @Test
    public void elasIlKorBoardStateRoamingThroneNonToken() {
        BoardState boardState = new BoardState();

        boardState.addCreature(new RoamingThrone());
        Elasilkor elasilkor1 = new Elasilkor();
        elasilkor1.isToken = true;
        Elasilkor elasilkor2 = new Elasilkor();
        elasilkor2.isToken = true;
        Elasilkor elasilkor3 = new Elasilkor();
        elasilkor3.isToken = true;
        boardState.addCreature(elasilkor1);
        boardState.addCreature(elasilkor2);
        boardState.addCreature(elasilkor3);

        Elasilkor spawningIlkor = new Elasilkor();
        spawningIlkor.isToken = false;
        boardState.spawnCreature(spawningIlkor);
        assertEquals(6, boardState.lifeGained);

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());
    }


}