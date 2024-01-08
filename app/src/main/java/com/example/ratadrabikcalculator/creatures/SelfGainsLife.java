package com.example.ratadrabikcalculator.creatures;

import java.util.List;
import java.util.concurrent.Callable;

public interface SelfGainsLife {
    public List<Callable<Void>> onGainLife(BoardState boardState, int amount);
}


