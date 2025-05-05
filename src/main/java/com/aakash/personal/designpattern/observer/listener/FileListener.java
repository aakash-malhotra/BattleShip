package com.aakash.personal.designpattern.observer.listener;

import com.aakash.personal.designpattern.observer.EventListener;
import com.aakash.personal.designpattern.observer.LogType;
import com.aakash.personal.designpattern.observer.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileListener implements EventListener {
    private final String logFile;
    private final PrintWriter writer;
    private final DateTimeFormatter formatter;

    public FileListener(String logFile) throws IOException {
        this.logFile = logFile;
        this.writer = new PrintWriter(new FileWriter(logFile, true));
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    @Override
    public void onLog(Message message) {
        String timestamp = LocalDateTime.now().format(formatter);
        StringBuilder logEntry = new StringBuilder();
        
        logEntry.append(String.format("[%s] [%s] [%s.%s] %s",
            timestamp,
            message.getLogType(),
            message.getClassName(),
            message.getMethodName(),
            message.getContent()
        ));

        if (message.getThrowable() != null) {
            logEntry.append("\n");
            message.getThrowable().printStackTrace(new PrintWriter(new StringBuilderWriter(logEntry)));
        }

        writer.println(logEntry);
        writer.flush();
    }

    public void close() {
        if (writer != null) {
            writer.close();
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
