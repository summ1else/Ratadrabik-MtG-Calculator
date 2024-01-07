package com.example.ratadrabikcalculator.creatures;

import java.util.List;
import java.util.concurrent.Callable;

public interface AnotherCreatureDies {
    public List<Callable<Void>> anotherCreatureDies(BoardState boardState, Creature creature);
}
