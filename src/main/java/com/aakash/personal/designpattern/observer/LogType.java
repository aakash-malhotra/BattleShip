package com.aakash.personal.designpattern.observer;

public enum LogType {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3),
    FATAL(4);

    private final int level;

    LogType(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
