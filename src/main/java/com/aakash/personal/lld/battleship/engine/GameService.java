package com.aakash.personal.lld.battleship.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final ConsoleReader reader;

    @Autowired
    public GameService(ConsoleReader consoleReader) {
        this.reader = consoleReader;
    }

    @Autowired
    private GameMemory memory;

    @Autowired
    private GameEngine engine;

    public void startup() {
        System.out.println("Welcome to BattleShip Game!");

        String player1 = reader.readString("Player 1 Enter your name: ");

        System.out.println("Hello, " + player1 + "!");
        Player playerA = new Player(player1, 0, memory.getGameFieldSize(), 0, memory.getGameFieldSize()/2);
        engine.initPossibleMoves(playerA, 0, memory.getGameFieldSize(), memory.getGameFieldSize()/2, memory.getGameFieldSize());
        setShipsPositions(playerA);

        String player2 = reader.readString("Player 2 Enter your name: ");
        System.out.println("Hello, " + player2 + "!");
        Player playerB = new Player(player2, 0, memory.getGameFieldSize(), memory.getGameFieldSize()/2, memory.getGameFieldSize());
        engine.initPossibleMoves(playerA, 0, memory.getGameFieldSize(), 0, memory.getGameFieldSize()/2);
        setShipsPositions(playerB);

        boolean isPlayerAMove = true;
        while (!memory.isOver()) {
            engine.viewBattleField();
            if(isPlayerAMove) {
                engine.nextMove(playerA);
            } else {
                engine.nextMove(playerB);
            }
            isPlayerAMove = !isPlayerAMove;
            memory.setOver(engine.isOver(playerA, playerB));
        }

    }

    private void setShipsPositions(Player player) {
        System.out.println(player.getName() + "Let's start placing your square shaped ships in the battlefield: you will be given " + memory.getShipFleetSize() + " ships. \n" +
                "You have to first enter the size of the ship and the starting point of the ship ");
        for(int i = 0; i < memory.getShipFleetSize(); i++) {
            setShipPosition(player);
        }
    }

    private void setShipPosition(Player player) {
        engine.viewPlayerBattleField(player);
        int size = reader.readInt("Enter your ship size: ");
        int x = reader.readInt("Enter your ship starting x coordinate: ");
        int y = reader.readInt("Enter your ship starting y coordinate: ");
        if(memory.getFieldValueAt(x, y).equalsIgnoreCase("-")) {
            for(int a = x; a<size+x; a++){
                for(int b = y; b<size+y; b++){
                    memory.setFieldValueAt(a,b, "S");
                }
            }
        } else {
            setShipsPositions(player);
        }
    }


}
