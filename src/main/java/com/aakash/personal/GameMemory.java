package com.aakash.personal;

import com.aakash.personal.rule.Rule;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@ToString
public class GameMemory {

    private Integer gameFieldSize;

    @Value("${game.battle.field.ship.fleet.size}")
    private Integer shipFleetSize;

    private String[][] field;

    private String winner;

    private boolean isOver;

    private final ConsoleReader reader;
    private final List<Rule> rules = new ArrayList<>();

    @PostConstruct
    public void init() {
        rules.add(new Rule((coordinate, player) -> {
            var field = getField()[coordinate[0]][coordinate[1]];
            if(field.isBlank()) {
                getField()[coordinate[0]][coordinate[1]] = "X";
            } else return false;
            return true;
        }
        ));
    }

    @Autowired
    public GameMemory(ConsoleReader reader) {
        this.reader = reader;
        gameFieldSize = reader.readInt("Please Enter the BattleField Size: ");
        field = new String[gameFieldSize][gameFieldSize];
        for(int i = 0; i < gameFieldSize; i++) {
            for(int j = 0; j < gameFieldSize; j++) {
                field[i][j] = "-";
            }
        }
    }

    public String getFieldValueAt(int x, int y) {
        return field[x][y];
    }

    public void setFieldValueAt(int x, int y, String val) {
        field[x][y] = val;
    }
}
