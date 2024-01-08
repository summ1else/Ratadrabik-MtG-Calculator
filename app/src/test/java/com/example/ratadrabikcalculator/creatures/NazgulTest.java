package com.example.ratadrabikcalculator.creatures;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NazgulTest {


    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    BoardState boardState = new BoardState();

    @Before
    public void beforeEach() {
        this.boardState = new BoardState();
    }

    @Test
    public void nazgulBoardState() {
        Creature nazgul1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul1.isToken = true;
        nazgul1.isZombieType = true;
        Creature nazgul2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul2.isToken = true;
        nazgul2.isZombieType = true;
        Creature nazgul3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul3.isToken = true;
        nazgul3.isZombieType = true;
        boardState.addCreature(nazgul1);
        boardState.addCreature(nazgul2);
        boardState.addCreature(nazgul3);
        Creature spawningNazgul = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        spawningNazgul.isLegendary = true;
        boardState.addCreature(spawningNazgul);
        boardState.spawnCreature(spawningNazgul);

        assertEquals(4, boardState.creatures.size());
        boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).forEach(creature -> {
            assertEquals(1, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());

    }

    @Test
    public void nazgulBoardStateRoamingThrone() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));

        Creature nazgul1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul1.isToken = true;
        nazgul1.isZombieType = true;
        Creature nazgul2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul2.isToken = true;
        nazgul2.isZombieType = true;
        Creature nazgul3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul3.isToken = true;
        nazgul3.isZombieType = true;
        boardState.addCreature(nazgul1);
        boardState.addCreature(nazgul2);
        boardState.addCreature(nazgul3);

        Creature spawningNazgul = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        spawningNazgul.isToken = true;
        spawningNazgul.isZombieType = true;
        boardState.addCreature(spawningNazgul);
        boardState.spawnCreature(spawningNazgul);

        assertEquals(5, boardState.creatures.size());
        List<Creature> nazguls = boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).collect(Collectors.toList());
        assertEquals(4, nazguls.size());
        nazguls.forEach(creature -> {
            assertEquals(4, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());
    }

    @Test
    public void nazgulBoardStateRoamingThroneNonToken() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));
        Creature nazgul1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul1.isToken = true;
        nazgul1.isZombieType = true;
        Creature nazgul2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul2.isToken = true;
        nazgul2.isZombieType = true;
        Creature nazgul3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul3.isToken = true;
        nazgul3.isZombieType = true;
        boardState.addCreature(nazgul1);
        boardState.addCreature(nazgul2);
        boardState.addCreature(nazgul3);

        Creature spawningNazgul = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        spawningNazgul.isToken = false;
        spawningNazgul.isZombieType = false;
        boardState.addCreature(spawningNazgul);
        boardState.spawnCreature(spawningNazgul);

        assertEquals(5, boardState.creatures.size());
        List<Creature> nazguls = boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).collect(Collectors.toList());
        assertEquals(4, nazguls.size());
        nazguls.forEach(creature -> {
            assertEquals(1, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());
    }


    @Test
    public void nazgulBoardStateRatadrabikNonToken() {
        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);

        Creature nazgul1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgul1.isLegendary = true;
        boardState.addCreature(nazgul1);

        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        boardState.killCreature(nazgul1);
        boardState.removeCreature(nazgul1);

        assertEquals(4, boardState.creatures.size());
        List<Creature> nazguls = boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).collect(Collectors.toList());
        assertEquals(2, nazguls.size());
        nazguls.forEach(creature -> {
            assertEquals(8, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());
    }

    @Test
    public void nazgulBoardStateRatadrabikRoamingThroneNonTokenWithTokens() {
        BoardState boardState = new BoardState();

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);

        Creature nazgulMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulMain.isLegendary = true;
        boardState.addCreature(nazgulMain);

        Creature nazgulToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken1.isToken = true;
        nazgulToken1.isZombieType = true;
        boardState.addCreature(nazgulToken1);

        Creature nazgulToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken2.isToken = true;
        nazgulToken2.isZombieType = true;
        boardState.addCreature(nazgulToken2);

        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        boardState.killCreature(nazgulMain);

        assertEquals(6, boardState.creatures.size());
        List<Creature> nazguls = boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).collect(Collectors.toList());
        assertEquals(4, nazguls.size());
        nazguls.forEach(creature -> {
            assertEquals(8, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());
    }

    @Test
    public void nazgulBoardStateRoamingThroneTeysaRatadrabik() {
        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature nazgulMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulMain.isLegendary = true;
        boardState.addCreature(nazgulMain);

        Creature nazgulToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken1.isToken = true;
        nazgulToken1.isZombieType = true;
        boardState.addCreature(nazgulToken1);

        Creature nazgulToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken2.isToken = true;
        nazgulToken2.isZombieType = true;
        boardState.addCreature(nazgulToken2);

        boardState.killCreature(nazgulMain);

        assertEquals(8, boardState.creatures.size());
        List<Creature> nazguls = boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).collect(Collectors.toList());
        assertEquals(5, nazguls.size());
        nazguls.forEach(creature -> {
            assertEquals(12, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());
    }


    @Test
    public void nazgulBoardStateRoamingThroneTeysaRatadrabikAnnointedProcession() {
        boardState.isAnointedProcessionPresent = true;

        Creature ratadrabikOriginal = CreatureFactory.createCreature(CreatureFactory.CreatureName.RATADRABIK);
        ratadrabikOriginal.isLegendary = true;
        boardState.addCreature(ratadrabikOriginal);


        Creature roamingThrown = CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE);
        boardState.addCreature(roamingThrown);

        Creature teysa = CreatureFactory.createCreature(CreatureFactory.CreatureName.TEYSA);
        teysa.isLegendary = true;
        boardState.addCreature(teysa);

        Creature nazgulMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulMain.isLegendary = true;
        boardState.addCreature(nazgulMain);

        Creature nazgulToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken1.isToken = true;
        nazgulToken1.isZombieType = true;
        boardState.addCreature(nazgulToken1);

        Creature nazgulToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken2.isToken = true;
        nazgulToken2.isZombieType = true;
        boardState.addCreature(nazgulToken2);

        boardState.killCreature(nazgulMain);

        assertEquals(11, boardState.creatures.size());
        List<Creature> nazguls = boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).collect(Collectors.toList());
        assertEquals(8, nazguls.size());
        nazguls.forEach(creature -> {
            assertEquals(24, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());
    }

    @Test
    public void nazgulBoardStateRoamingThroneTeysaRatadrabikAnnointedProcessionDrivnod() {
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

        Creature nazgulMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulMain.isLegendary = true;
        boardState.addCreature(nazgulMain);

        Creature nazgulToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken1.isToken = true;
        nazgulToken1.isZombieType = true;
        boardState.addCreature(nazgulToken1);

        Creature nazgulToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken2.isToken = true;
        nazgulToken2.isZombieType = true;
        boardState.addCreature(nazgulToken2);

        boardState.killCreature(nazgulMain);

        assertEquals(14, boardState.creatures.size());
        List<Creature> nazguls = boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).collect(Collectors.toList());
        assertEquals(10, nazguls.size());
        nazguls.forEach(creature -> {
            assertEquals(32, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());
    }

    @Test
    public void nazgulBoardStateRoamingThroneTeysaRatadrabikAnnointedProcessionDrivnodMondrak() {
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

        Creature nazgulMain = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulMain.isLegendary = true;
        boardState.addCreature(nazgulMain);

        Creature nazgulToken1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken1.isToken = true;
        nazgulToken1.isZombieType = true;
        boardState.addCreature(nazgulToken1);

        Creature nazgulToken2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.NAZGUL);
        nazgulToken2.isToken = true;
        nazgulToken2.isZombieType = true;
        boardState.addCreature(nazgulToken2);

        boardState.killCreature(nazgulMain);

        assertEquals(23, boardState.creatures.size());
        List<Creature> nazguls = boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL).collect(Collectors.toList());
        assertEquals(18, nazguls.size());
        nazguls.forEach(creature -> {
            assertEquals(64, creature.counters);
        });
        assertEquals(1,boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.NAZGUL && creature.isLegendary).count());
    }


}