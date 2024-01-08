package com.example.ratadrabikcalculator.creatures;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElasilkorTest {


    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    BoardState boardState = new BoardState();

    @Before
    public void beforeEach() {
        this.boardState = new BoardState();
    }

    @Test
    public void elasIlKorBoardState() {
        Creature elasilkor1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor1.isToken = true;
        elasilkor1.isZombieType = true;
        Creature elasilkor2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor2.isToken = true;
        elasilkor2.isZombieType = true;
        Creature elasilkor3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor3.isToken = true;
        elasilkor3.isZombieType = true;
        boardState.addCreature(elasilkor1);
        boardState.addCreature(elasilkor2);
        boardState.addCreature(elasilkor3);
        Creature spawningIlkor = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        spawningIlkor.isLegendary = true;
        boardState.addCreature(spawningIlkor);
        boardState.spawnCreature(spawningIlkor);

        assertEquals(3, boardState.getLifeGained());
        assertEquals(0, boardState.oppLifeLost);
        assertEquals(4, boardState.creatures.size());

    }

    @Test
    public void elasIlKorBoardStateRoamingThrone() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));

        Creature elasilkor1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor1.isToken = true;
        elasilkor1.isZombieType = true;
        Creature elasilkor2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor2.isToken = true;
        elasilkor2.isZombieType = true;
        Creature elasilkor3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor3.isToken = true;
        elasilkor3.isZombieType = true;
        boardState.addCreature(elasilkor1);
        boardState.addCreature(elasilkor2);
        boardState.addCreature(elasilkor3);

        Creature spawningIlkor = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        spawningIlkor.isToken = true;
        spawningIlkor.isZombieType = true;
        boardState.addCreature(spawningIlkor);
        boardState.spawnCreature(spawningIlkor);

        assertEquals(6, boardState.getLifeGained());
        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());
    }

    @Test
    public void elasIlKorBoardStateRoamingThroneNonToken() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));
        Creature elasilkor1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor1.isToken = true;
        elasilkor1.isZombieType = true;
        Creature elasilkor2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor2.isToken = true;
        elasilkor2.isZombieType = true;
        Creature elasilkor3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkor3.isToken = true;
        elasilkor3.isZombieType = true;
        boardState.addCreature(elasilkor1);
        boardState.addCreature(elasilkor2);
        boardState.addCreature(elasilkor3);

        Creature spawningIlkor = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        spawningIlkor.isToken = false;
        spawningIlkor.isZombieType = false;
        boardState.addCreature(spawningIlkor);
        boardState.spawnCreature(spawningIlkor);

        assertEquals(6, boardState.getLifeGained());
        assertEquals(0, boardState.oppLifeLost);
        assertEquals(5, boardState.creatures.size());
    }


    @Test
    public void elasIlKorBoardStateRatadrabikNonToken() {
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

        assertEquals(4, boardState.getLifeGained());

        assertEquals(0, boardState.oppLifeLost);
        assertEquals(4, boardState.creatures.size());

    }

    @Test
    public void elasIlKorBoardStateRatadrabikNonTokenWithTokens() {
        BoardState boardState = new BoardState();

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);

        Creature elasilkorMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorMain.isLegendary = true;
        boardState.addCreature(elasilkorMain);

        Creature elasilkorToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken1.isToken = true;
        elasilkorToken1.isZombieType = true;
        boardState.addCreature(elasilkorToken1);

        Creature elasilkorToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken2.isToken = true;
        elasilkorToken2.isZombieType = true;
        boardState.addCreature(elasilkorToken2);

        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        boardState.removeCreature(elasilkorMain);
        boardState.killCreature(elasilkorMain);

        assertEquals(12, boardState.getLifeGained());
        assertEquals(2, boardState.oppLifeLost);
        assertEquals(6, boardState.creatures.size());
    }

    @Test
    public void elasIlKorBoardStateRoamingThroneTeysaRatadrabik() {
        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature elasilkorMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorMain.isLegendary = true;
        boardState.addCreature(elasilkorMain);

        Creature elasilkorToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken1.isToken = true;
        elasilkorToken1.isZombieType = true;
        boardState.addCreature(elasilkorToken1);

        Creature elasilkorToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken2.isToken = true;
        elasilkorToken2.isZombieType = true;
        boardState.addCreature(elasilkorToken2);

        boardState.removeCreature(elasilkorMain);
        boardState.killCreature(elasilkorMain);

        assertEquals(24, boardState.getLifeGained());
        assertEquals(6, boardState.oppLifeLost);
        assertEquals(8, boardState.creatures.size());

    }


    @Test
    public void elasIlKorBoardStateRoamingThroneTeysaRatadrabikAnnointedProcession() {
        boardState.isAnointedProcessionPresent = true;

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature elasilkorMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorMain.isLegendary = true;
        boardState.addCreature(elasilkorMain);

        Creature elasilkorToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken1.isToken = true;
        elasilkorToken1.isZombieType = true;
        boardState.addCreature(elasilkorToken1);

        Creature elasilkorToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken2.isToken = true;
        elasilkorToken2.isZombieType = true;
        boardState.addCreature(elasilkorToken2);

        boardState.removeCreature(elasilkorMain);
        boardState.killCreature(elasilkorMain);

        assertEquals(84, boardState.getLifeGained());
        assertEquals(6, boardState.oppLifeLost);
        assertEquals(11, boardState.creatures.size());

    }

    @Test
    public void elasIlKorBoardStateRoamingThroneTeysaRatadrabikAnnointedProcessionDrivnod() {
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

        Creature elasilkorMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorMain.isLegendary = true;
        boardState.addCreature(elasilkorMain);

        Creature elasilkorToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken1.isToken = true;
        elasilkorToken1.isZombieType = true;
        boardState.addCreature(elasilkorToken1);

        Creature elasilkorToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken2.isToken = true;
        elasilkorToken2.isZombieType = true;
        boardState.addCreature(elasilkorToken2);

        boardState.removeCreature(elasilkorMain);
        boardState.killCreature(elasilkorMain);

        assertEquals(144, boardState.getLifeGained());
        assertEquals(8, boardState.oppLifeLost);
        assertEquals(14, boardState.creatures.size());

    }

    @Test
    public void elasIlKorBoardStateRoamingThroneTeysaRatadrabikAnnointedProcessionDrivnodMondrak() {
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

        Creature elasilkorMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorMain.isLegendary = true;
        boardState.addCreature(elasilkorMain);

        Creature elasilkorToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken1.isToken = true;
        elasilkorToken1.isZombieType = true;
        boardState.addCreature(elasilkorToken1);

        Creature elasilkorToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELASILKOR);
        elasilkorToken2.isToken = true;
        elasilkorToken2.isZombieType = true;
        boardState.addCreature(elasilkorToken2);

        boardState.removeCreature(elasilkorMain);
        boardState.killCreature(elasilkorMain);

        assertEquals(544, boardState.getLifeGained());
        assertEquals(8, boardState.oppLifeLost);
        assertEquals(23, boardState.creatures.size());

    }


}