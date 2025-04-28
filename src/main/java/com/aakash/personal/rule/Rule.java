package com.aakash.personal.rule;

import com.aakash.personal.player.Player;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Rule {

    private BiFunction<int[], Player, Boolean> rule;

    public Rule(BiFunction<int[], Player, Boolean> rule) {
        this.rule = rule;
    }

//    public boolean isOkMove()
}
