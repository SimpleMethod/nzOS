package com.project.pcmr.nzos.management_control;

public class ExceptionApiManagment extends RuntimeException {

    public ExceptionApiManagment() {
        super();
    }

    public ExceptionApiManagment(String message) {
        super(message);
    }

    public ExceptionApiManagment(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionApiManagment(Throwable cause) {
        super(cause);
    }
}
