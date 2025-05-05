package com.aakash.personal.designpattern.observer;

public class Message {
    private final String content;
    private final LogType logType;
    private final String className;
    private final String methodName;
    private final Throwable throwable;

    public Message(String content, LogType logType, String className, String methodName) {
        this(content, logType, className, methodName, null);
    }

    public Message(String content, LogType logType, String className, String methodName, Throwable throwable) {
        this.content = content;
        this.logType = logType;
        this.className = className;
        this.methodName = methodName;
        this.throwable = throwable;
    }

    public String getContent() {
        return content;
    }

    public LogType getLogType() {
        return logType;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
