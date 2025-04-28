package com.aakash.personal.engine;

import com.aakash.personal.GameMemory;
import com.aakash.personal.move.MoveStrategy;
import com.aakash.personal.move.RandomStrategy;
import com.aakash.personal.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GameEngine {

    @Autowired
    private GameMemory memory;

    @Autowired
//    @Qualifier("RandomStrategy")
    RandomStrategy strategy;

    public void viewBattleField(){
        for (String[] x : memory.getField()) {
            for (String y : x) {
                System.out.print(" " + y + " ");
            }
            System.out.println();
        }
    }

    public String viewPlayerBattleField(Player player) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = player.getXBoundaryStart(); i < player.getXBoundaryEnd(); i++) {
            for (int j = player.getYBoundaryStart(); j < player.getYBoundaryEnd(); j++) {
                stringBuilder.append(" ").append(memory.getFieldValueAt(i, j)).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    public boolean isOver(Player a, Player b) {
        boolean isOver = false;
        if(!viewPlayerBattleField(a).contains("S")) {
            memory.setWinner(b.getName());
            isOver = true;
        } else if(!viewPlayerBattleField(b).contains("S")){
            memory.setWinner(a.getName());
            isOver = true;
        }
        return isOver;
    }

    public void nextMove(Player player) {
        int[] move = strategy.move(player);
        if(memory.getFieldValueAt(move[0], move[1]).equalsIgnoreCase("S")) {
            memory.setFieldValueAt(move[0], move[1], "X");
        }
        player.getPossibleMoves().remove(move);

    }

//    public void initPossibleMoves(Player player, int size) {
//        for(int x = player.xBoundaryEnd; x < this.xBoundaryEnd; x++) {
//            for(int y = this.yBoundaryStart; y < this.xBoundaryEnd; y++) {
//                this.possibleMoves.add(new int[]{x, y});
//            }
//        }
//    }

    public void initPossibleMoves(Player player, int xstart, Integer xend, int ystart, int yend) {
        for(int x = xstart; x < xend; x++) {
            for(int y = ystart; y < yend; y++) {
                player.getPossibleMoves().add(new int[]{x, y});
            }
        }
    }
}
