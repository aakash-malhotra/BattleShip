package com.aakash.personal.player;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Player {

    private String name;
    private Integer xBoundaryStart;
    private Integer xBoundaryEnd;
    private Integer yBoundaryStart;
    private Integer yBoundaryEnd;
    private List<int[]> possibleMoves = new ArrayList<>();

    public Player(String name, Integer xBoundaryStart, Integer xBoundaryEnd, Integer yBoundaryStart, Integer yBoundaryEnd) {
        this.name = name;
        this.xBoundaryStart = xBoundaryStart;
        this.xBoundaryEnd = xBoundaryEnd;
        this.yBoundaryStart = yBoundaryStart;
        this.yBoundaryEnd = yBoundaryEnd;
    }

//    private void initPossibleMoves(Player player, int size) {
//        for(int x = this.xBoundaryEnd; x < this.xBoundaryEnd; x++) {
//            for(int y = this.yBoundaryStart; y < this.xBoundaryEnd; y++) {
//                this.possibleMoves.add(new int[]{x, y});
//            }
//        }
//    }
}
