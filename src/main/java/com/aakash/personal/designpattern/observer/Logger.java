package com.aakash.personal.designpattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
    private final List<EventListener> listeners;
    private final ReentrantLock lock;
    private LogType minimumLogLevel;
    private boolean includeStackTrace;

    private Logger() {
        listeners = new ArrayList<>();
        lock = new ReentrantLock();
        minimumLogLevel = LogType.INFO;
        includeStackTrace = true;
    }

    private static final class LoggerHolder {
        private static final Logger logger = new Logger();
    }

    public static Logger getLogger() {
        return LoggerHolder.logger;
    }

    public void setMinimumLogLevel(LogType level) {
        this.minimumLogLevel = level;
    }

    public void setIncludeStackTrace(boolean include) {
        this.includeStackTrace = include;
    }

    public boolean register(EventListener listener) {
        if (listener == null) return false;
        try {
            lock.lock();
            return listeners.add(listener);
        } finally {
            lock.unlock();
        }
    }

    public boolean unregister(EventListener listener) {
        if (listener == null) return false;
        try {
            lock.lock();
            return listeners.remove(listener);
        } finally {
            lock.unlock();
        }
    }

    public void debug(String message) {
        log(message, LogType.DEBUG, null);
    }

    public void info(String message) {
        log(message, LogType.INFO, null);
    }

    public void warn(String message) {
        log(message, LogType.WARN, null);
    }

    public void error(String message, Throwable throwable) {
        log(message, LogType.ERROR, throwable);
    }

    public void fatal(String message, Throwable throwable) {
        log(message, LogType.FATAL, throwable);
    }

    private void log(String message, LogType logType, Throwable throwable) {
        if (logType.getLevel() < minimumLogLevel.getLevel()) {
            return;
        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[3].getClassName();
        String methodName = stackTrace[3].getMethodName();

        Message logMessage = new Message(
            message,
            logType,
            className,
            methodName,
            includeStackTrace ? throwable : null
        );

        notifyAllListeners(logMessage);
    }

    private void notifyAllListeners(Message message) {
        if (message == null) return;
        try {
            lock.lock();
            for (EventListener listener : listeners) {
                try {
                    listener.onLog(message);
                } catch (Exception e) {
                    System.err.println("Error notifying listener: " + e.getMessage());
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
