package com.example.ratadrabikcalculator.creatures;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

public class MyriadTest {


    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    BoardState boardState = new BoardState();

    @Before
    public void beforeEach() {
        this.boardState = new BoardState();
    }

    @Test
    public void elendaBoardState() {
        Creature elenda1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda1.isToken = true;
        elenda1.isZombieType = true;
        Creature elenda2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda2.isToken = true;
        elenda2.isZombieType = true;
        Creature elenda3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda3.isToken = true;
        elenda3.isZombieType = true;
        boardState.addCreature(elenda1);
        boardState.addCreature(elenda2);
        boardState.addCreature(elenda3);
        Creature spawningElenda = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        spawningElenda.isLegendary = true;
        boardState.addCreature(spawningElenda);
        boardState.spawnCreature(spawningElenda);

        boardState.myriadCreature(spawningElenda);

        assertEquals(4, boardState.creatures.size());
        assertEquals(2, elenda1.counters);
        assertEquals(2, elenda2.counters);
        assertEquals(2, elenda3.counters);
        assertEquals(2, spawningElenda.counters);

    }

    @Test
    public void elendaBoardStateRoamingThrone() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));

        Creature elenda1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda1.isToken = true;
        elenda1.isZombieType = true;
        Creature elenda2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda2.isToken = true;
        elenda2.isZombieType = true;
        Creature elenda3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda3.isToken = true;
        elenda3.isZombieType = true;
        boardState.addCreature(elenda1);
        boardState.addCreature(elenda2);
        boardState.addCreature(elenda3);

        Creature spawningElenda = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        spawningElenda.isToken = true;
        spawningElenda.isZombieType = true;
        boardState.addCreature(spawningElenda);
        boardState.spawnCreature(spawningElenda);

        boardState.myriadCreature(spawningElenda);

        assertEquals(5, boardState.creatures.size());
        assertEquals(0, elenda1.counters);
        assertEquals(0, elenda2.counters);
        assertEquals(0, elenda3.counters);
        assertEquals(0, spawningElenda.counters);
    }

    @Test
    public void elendaBoardStateRoamingThroneNonToken() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));
        Creature elenda1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda1.isToken = true;
        elenda1.isZombieType = true;
        Creature elenda2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda2.isToken = true;
        elenda2.isZombieType = true;
        Creature elenda3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda3.isToken = true;
        elenda3.isZombieType = true;
        boardState.addCreature(elenda1);
        boardState.addCreature(elenda2);
        boardState.addCreature(elenda3);

        Creature spawningElenda = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        spawningElenda.isToken = false;
        spawningElenda.isZombieType = false;
        spawningElenda.isLegendary = true;
        boardState.addCreature(spawningElenda);
        boardState.spawnCreature(spawningElenda);

        boardState.myriadCreature(spawningElenda);

        assertEquals(5, boardState.creatures.size());
        assertEquals(4, elenda1.counters);
        assertEquals(4, elenda2.counters);
        assertEquals(4, elenda3.counters);
        assertEquals(2, spawningElenda.counters);
    }


    @Test
    public void elendaBoardStateRatadrabikNonToken() {
        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);

        Creature elenda1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elenda1.isLegendary = true;
        boardState.addCreature(elenda1);

        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        boardState.myriadCreature(elenda1);

        assertEquals(11, boardState.creatures.size());
        assertEquals(2, elenda1.counters);
        System.out.println(gson.toJson(boardState));
    }

    @Test
    public void elendaBoardStateRatadrabikNonTokenWithTokens() {
        BoardState boardState = new BoardState();

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);

        Creature elendaMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaMain.isLegendary = true;
        boardState.addCreature(elendaMain);

        Creature elendaToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken1.isToken = true;
        elendaToken1.isZombieType = true;
        boardState.addCreature(elendaToken1);

        Creature elendaToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken2.isToken = true;
        elendaToken2.isZombieType = true;
        boardState.addCreature(elendaToken2);

        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        boardState.killCreature(elendaMain);

        assertEquals(7, boardState.creatures.size());
        assertEquals(2, elendaToken1.counters);
        assertEquals(2, elendaToken2.counters);
    }

    @Test
    public void elendaBoardStateRoamingThroneTeysaRatadrabik() {
        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature elendaMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaMain.isLegendary = true;
        boardState.addCreature(elendaMain);

        Creature elendaToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken1.isToken = true;
        elendaToken1.isZombieType = true;
        boardState.addCreature(elendaToken1);

        Creature elendaToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken2.isToken = true;
        elendaToken2.isZombieType = true;
        boardState.addCreature(elendaToken2);

        boardState.killCreature(elendaMain);

        assertEquals(10, boardState.creatures.size());
        assertEquals(3, elendaToken1.counters);
        assertEquals(3, elendaToken2.counters);

    }


    @Test
    public void elendaBoardStateRoamingThroneTeysaRatadrabikAnnointedProcession() {
        boardState.isAnointedProcessionPresent = true;

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature elendaMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaMain.isLegendary = true;
        boardState.addCreature(elendaMain);

        Creature elendaToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken1.isToken = true;
        elendaToken1.isZombieType = true;
        boardState.addCreature(elendaToken1);

        Creature elendaToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken2.isToken = true;
        elendaToken2.isZombieType = true;
        boardState.addCreature(elendaToken2);

        boardState.killCreature(elendaMain);

        assertEquals(13, boardState.creatures.size());
        assertEquals(3, elendaToken1.counters);
        assertEquals(3, elendaToken2.counters);

    }

    @Test
    public void elendaBoardStateRoamingThroneTeysaRatadrabikAnnointedProcessionDrivnod() {
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

        Creature elendaMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaMain.isLegendary = true;
        boardState.addCreature(elendaMain);

        Creature elendaToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken1.isToken = true;
        elendaToken1.isZombieType = true;
        boardState.addCreature(elendaToken1);

        Creature elendaToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken2.isToken = true;
        elendaToken2.isZombieType = true;
        boardState.addCreature(elendaToken2);

        boardState.killCreature(elendaMain);

        assertEquals(17, boardState.creatures.size());
        assertEquals(4, elendaToken1.counters);
        assertEquals(4, elendaToken2.counters);

    }

    @Test
    public void elendaBoardStateRoamingThroneTeysaRatadrabikAnnointedProcessionDrivnodMondrak() {
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

        Creature elendaMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaMain.isLegendary = true;
        boardState.addCreature(elendaMain);

        Creature elendaToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken1.isToken = true;
        elendaToken1.isZombieType = true;
        boardState.addCreature(elendaToken1);

        Creature elendaToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.ELENDA);
        elendaToken2.isToken = true;
        elendaToken2.isZombieType = true;
        boardState.addCreature(elendaToken2);

        boardState.killCreature(elendaMain);

        assertEquals(26, boardState.creatures.size());
        assertEquals(4, elendaToken1.counters);
        assertEquals(4, elendaToken2.counters);

        boardState.killCreature(elendaToken1);
        assertEquals(45, boardState.creatures.size());
        assertEquals(8, elendaToken2.counters);

    }


}