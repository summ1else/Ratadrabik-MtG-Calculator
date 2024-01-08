package com.example.ratadrabikcalculator.creatures;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class YoshimaruTest {


    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    BoardState boardState = new BoardState();

    @Before
    public void beforeEach() {
        this.boardState = new BoardState();
    }

    @Test
    public void yoshimaruBoardState() {
        Creature yoshimaru1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru1.isToken = true;
        yoshimaru1.isZombieType = true;
        Creature yoshimaru2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru2.isToken = true;
        yoshimaru2.isZombieType = true;
        Creature yoshimaru3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru3.isToken = true;
        yoshimaru3.isZombieType = true;
        boardState.addCreature(yoshimaru1);
        boardState.addCreature(yoshimaru2);
        boardState.addCreature(yoshimaru3);
        Creature spawningYoshimaru = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        spawningYoshimaru.isLegendary = true;
        boardState.addCreature(spawningYoshimaru);
        boardState.spawnCreature(spawningYoshimaru);

        assertEquals(4, boardState.creatures.size());
        boardState.creatures.stream().filter(creature -> creature.name == CreatureFactory.CreatureName.YOSHIMARU && creature != spawningYoshimaru).forEach(creature -> {
            assertEquals(1, creature.counters);
        });

    }

    @Test
    public void yoshimaruBoardStateRoamingThrone() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));

        Creature yoshimaru1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru1.isToken = true;
        yoshimaru1.isZombieType = true;
        Creature yoshimaru2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru2.isToken = true;
        yoshimaru2.isZombieType = true;
        Creature yoshimaru3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru3.isToken = true;
        yoshimaru3.isZombieType = true;
        boardState.addCreature(yoshimaru1);
        boardState.addCreature(yoshimaru2);
        boardState.addCreature(yoshimaru3);

        Creature spawningYoshimaru = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        spawningYoshimaru.isToken = true;
        spawningYoshimaru.isZombieType = true;
        boardState.addCreature(spawningYoshimaru);
        boardState.spawnCreature(spawningYoshimaru);

        assertEquals(5, boardState.creatures.size());
        assertEquals(0, yoshimaru1.counters); // token being added
        assertEquals(0, yoshimaru2.counters);
        assertEquals(0, yoshimaru3.counters);
    }

    @Test
    public void yoshimaruBoardStateRoamingThroneNonToken() {
        boardState.addCreature(CreatureFactory.createCreature(CreatureFactory.CreatureName.ROAMING_THRONE));
        Creature yoshimaru1 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru1.isToken = true;
        yoshimaru1.isZombieType = true;
        Creature yoshimaru2 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru2.isToken = true;
        yoshimaru2.isZombieType = true;
        Creature yoshimaru3 = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        yoshimaru3.isToken = true;
        yoshimaru3.isZombieType = true;
        boardState.addCreature(yoshimaru1);
        boardState.addCreature(yoshimaru2);
        boardState.addCreature(yoshimaru3);

        Creature spawningYoshimaru = CreatureFactory.createCreature(CreatureFactory.CreatureName.YOSHIMARU);
        spawningYoshimaru.isLegendary = true;
        spawningYoshimaru.isToken = false;
        spawningYoshimaru.isZombieType = false;
        boardState.addCreature(spawningYoshimaru);
        boardState.spawnCreature(spawningYoshimaru);

        assertEquals(5, boardState.creatures.size());
        assertEquals(2, yoshimaru1.counters); // token being added
        assertEquals(2, yoshimaru2.counters);
        assertEquals(2, yoshimaru3.counters);
    }

}