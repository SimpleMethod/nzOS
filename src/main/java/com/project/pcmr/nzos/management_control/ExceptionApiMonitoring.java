package com.project.pcmr.nzos.management_control;

public class ExceptionApiMonitoring extends RuntimeException {

    public ExceptionApiMonitoring() { super(); }
    public ExceptionApiMonitoring(String message) { super(message); }
    public ExceptionApiMonitoring(String message, Throwable cause) { super(message, cause); }
    public ExceptionApiMonitoring(Throwable cause) { super(cause); }
}
