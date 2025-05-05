package com.aakash.personal.designpattern.observer.listener;

import com.aakash.personal.designpattern.observer.EventListener;
import com.aakash.personal.designpattern.observer.LogType;
import com.aakash.personal.designpattern.observer.Message;

import java.io.PrintWriter;
import java.io.StringBuilderWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleListener implements EventListener {
    private final DateTimeFormatter formatter;

    public ConsoleListener() {
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    @Override
    public void onLog(Message message) {
        String timestamp = LocalDateTime.now().format(formatter);
        StringBuilder logEntry = new StringBuilder();
        
        // Add color based on log level
        String color = getColorForLogType(message.getLogType());
        String reset = "\u001B[0m";
        
        logEntry.append(String.format("%s[%s] [%s] [%s.%s] %s%s",
            color,
            timestamp,
            message.getLogType(),
            message.getClassName(),
            message.getMethodName(),
            message.getContent(),
            reset
        ));

        if (message.getThrowable() != null) {
            logEntry.append("\n");
            message.getThrowable().printStackTrace(new PrintWriter(new StringBuilderWriter(logEntry)));
        }

        System.out.println(logEntry);
    }

    private String getColorForLogType(LogType type) {
        switch (type) {
            case DEBUG: return "\u001B[36m"; // Cyan
            case INFO: return "\u001B[32m";  // Green
            case WARN: return "\u001B[33m";  // Yellow
            case ERROR: return "\u001B[31m"; // Red
            case FATAL: return "\u001B[35m"; // Magenta
            default: return "\u001B[0m";     // Reset
        }
    }

    private static class StringBuilderWriter extends java.io.Writer {
        private final StringBuilder builder;

        public StringBuilderWriter(StringBuilder builder) {
            this.builder = builder;
        }

        @Override
        public void write(char[] cbuf, int off, int len) {
            builder.append(cbuf, off, len);
        }

        @Override
        public void flush() {}

        @Override
        public void close() {}
    }
}
