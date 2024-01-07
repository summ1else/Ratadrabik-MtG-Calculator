package com.example.ratadrabikcalculator.creatures;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public interface AnotherCreatureLeavesTheBattleField {
    public List<Callable<Void>> anotherCreatureLeavesTheBattleField(BoardState boardState, Creature creature);
}
