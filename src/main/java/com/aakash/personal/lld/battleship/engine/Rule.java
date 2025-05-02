package com.aakash.personal.lld.battleship.engine;

import java.util.function.BiFunction;

public class Rule {

    private BiFunction<int[], Player, Boolean> rule;

    public Rule(BiFunction<int[], Player, Boolean> rule) {
        this.rule = rule;
    }

//    public boolean isOkMove()
}
