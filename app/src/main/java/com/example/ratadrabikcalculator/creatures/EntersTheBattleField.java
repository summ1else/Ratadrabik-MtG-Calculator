package com.example.ratadrabikcalculator.creatures;

import java.util.List;
import java.util.concurrent.Callable;

public interface EntersTheBattleField {
    public List<Callable<Void>> entersTheBattleField(BoardState boardState);
}


