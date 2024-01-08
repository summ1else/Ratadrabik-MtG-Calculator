package com.example.ratadrabikcalculator.creatures;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

public class KonradTest {


    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    BoardState boardState = new BoardState();

    @Before
    public void beforeEach() {
        this.boardState = new BoardState();
    }

    @Test
    public void konradBoardState() {
        Creature konrad1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad1.isToken = true;
        konrad1.isZombieType = true;
        Creature konrad2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad2.isToken = true;
        konrad2.isZombieType = true;
        Creature konrad3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad3.isToken = true;
        konrad3.isZombieType = true;
        boardState.addCreature(konrad1);
        boardState.addCreature(konrad2);
        boardState.addCreature(konrad3);
        Creature spawningIlkor = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        spawningIlkor.isLegendary = true;
        boardState.addCreature(spawningIlkor);
        boardState.spawnCreature(spawningIlkor);

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(4, boardState.creatures.size());

    }

    @Test
    public void konradBoardStateRoamingThrone() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));

        Creature konrad1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad1.isToken = true;
        konrad1.isZombieType = true;
        Creature konrad2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad2.isToken = true;
        konrad2.isZombieType = true;
        Creature konrad3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad3.isToken = true;
        konrad3.isZombieType = true;
        boardState.addCreature(konrad1);
        boardState.addCreature(konrad2);
        boardState.addCreature(konrad3);

        Creature spawningIlkor = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        spawningIlkor.isToken = true;
        spawningIlkor.isZombieType = true;
        boardState.addCreature(spawningIlkor);
        boardState.spawnCreature(spawningIlkor);

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());
    }

    @Test
    public void konradBoardStateRoamingThroneNonToken() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));
        Creature konrad1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad1.isToken = true;
        konrad1.isZombieType = true;
        Creature konrad2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad2.isToken = true;
        konrad2.isZombieType = true;
        Creature konrad3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad3.isToken = true;
        konrad3.isZombieType = true;
        boardState.addCreature(konrad1);
        boardState.addCreature(konrad2);
        boardState.addCreature(konrad3);

        Creature spawningIlkor = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        spawningIlkor.isToken = false;
        spawningIlkor.isZombieType = false;
        boardState.addCreature(spawningIlkor);
        boardState.spawnCreature(spawningIlkor);

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());
    }


    @Test
    public void konradBoardStateRatadrabikNonToken() {
        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);

        Creature konrad1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konrad1.isLegendary = true;
        boardState.addCreature(konrad1);

        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        boardState.killCreature(konrad1);
        boardState.removeCreature(konrad1);

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(4, boardState.creatures.size());

    }

    @Test
    public void konradBoardStateRatadrabikNonTokenWithTokens() {
        BoardState boardState = new BoardState();

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);

        Creature konradMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradMain.isLegendary = true;
        boardState.addCreature(konradMain);

        Creature konradToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken1.isToken = true;
        konradToken1.isZombieType = true;
        boardState.addCreature(konradToken1);

        Creature konradToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken2.isToken = true;
        konradToken2.isZombieType = true;
        boardState.addCreature(konradToken2);

        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        boardState.killCreature(konradMain);

        assertEquals(4, boardState.oppLifeLost);
        assertEquals(6, boardState.creatures.size());
    }

    @Test
    public void konradBoardStateRoamingThroneTeysaRatadrabik() {
        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature konradMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradMain.isLegendary = true;
        boardState.addCreature(konradMain);

        Creature konradToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken1.isToken = true;
        konradToken1.isZombieType = true;
        boardState.addCreature(konradToken1);

        Creature konradToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken2.isToken = true;
        konradToken2.isZombieType = true;
        boardState.addCreature(konradToken2);

        boardState.killCreature(konradMain);

        assertEquals(6, boardState.oppLifeLost);
        assertEquals(8, boardState.creatures.size());

    }


    @Test
    public void konradBoardStateRoamingThroneTeysaRatadrabikAnnointedProcession() {
        boardState.isAnointedProcessionPresent = true;

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature konradMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradMain.isLegendary = true;
        boardState.addCreature(konradMain);

        Creature konradToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken1.isToken = true;
        konradToken1.isZombieType = true;
        boardState.addCreature(konradToken1);

        Creature konradToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken2.isToken = true;
        konradToken2.isZombieType = true;
        boardState.addCreature(konradToken2);

        boardState.killCreature(konradMain);

        assertEquals(6, boardState.oppLifeLost);
        assertEquals(11, boardState.creatures.size());

    }

    @Test
    public void konradBoardStateRoamingThroneTeysaRatadrabikAnnointedProcessionDrivnod() {
        boardState.isAnointedProcessionPresent = true;

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature drivnod = CreatureFactory.createCreature(CreatureFactory.CreatureName.DRIVNOD);
        drivnod.isLegendary = true;
        boardState.addCreature(drivnod);

        Creature konradMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradMain.isLegendary = true;
        boardState.addCreature(konradMain);

        Creature konradToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken1.isToken = true;
        konradToken1.isZombieType = true;
        boardState.addCreature(konradToken1);

        Creature konradToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken2.isToken = true;
        konradToken2.isZombieType = true;
        boardState.addCreature(konradToken2);

        boardState.killCreature(konradMain);

        assertEquals(8, boardState.oppLifeLost);
        assertEquals(14, boardState.creatures.size());

    }

    @Test
    public void konradBoardStateRoamingThroneTeysaRatadrabikAnnointedProcessionDrivnodMondrak() {
        boardState.isAnointedProcessionPresent = true;

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature drivnod = CreatureFactory.createCreature(CreatureFactory.CreatureName.DRIVNOD);
        drivnod.isLegendary = true;
        boardState.addCreature(drivnod);

        Creature mondrak = CreatureFactory.createCreature(CreatureFactory.CreatureName.MONDRAK);
        mondrak.isLegendary = true;
        boardState.addCreature(mondrak);

        Creature konradMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradMain.isLegendary = true;
        boardState.addCreature(konradMain);

        Creature konradToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken1.isToken = true;
        konradToken1.isZombieType = true;
        boardState.addCreature(konradToken1);

        Creature konradToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.KONRAD);
        konradToken2.isToken = true;
        konradToken2.isZombieType = true;
        boardState.addCreature(konradToken2);

        boardState.killCreature(konradMain);

        assertEquals(8, boardState.oppLifeLost);
        assertEquals(23, boardState.creatures.size());

    }


}