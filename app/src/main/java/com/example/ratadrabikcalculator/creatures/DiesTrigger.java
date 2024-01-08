package com.example.ratadrabikcalculator.creatures;

import java.util.List;
import java.util.concurrent.Callable;

public interface DiesTrigger {
    public List<Callable<Void>> dies(BoardState boardState);
}
