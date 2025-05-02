package com.aakash.personal.lld.battleship.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomStrategy implements MoveStrategy {

    @Autowired
    private GameMemory memory;

    public int[] move(Player player) {
        int moveIndex = new Random().nextInt(0, player.getPossibleMoves().size());
        return player.getPossibleMoves().get(moveIndex);
//        var field =  memory.getField()[move[0]][move[1]];
//
//        if(field.isBlank()) {
//            memory.getField()[move[0]][move[1]] = "M";
//        }
//        re
    }

}
