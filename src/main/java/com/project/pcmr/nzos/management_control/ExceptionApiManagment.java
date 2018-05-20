package com.project.pcmr.nzos.management_control;

/**
 * Klasa służaca jako wyjątek.
 */
class ExceptionApiManagment extends RuntimeException {

    /**
     * Konstruktor klasy.
     */
    public ExceptionApiManagment() {
        super();
    }

    /**
     *  Konstruktor klasy z obsługą tekstu.
     * @param message komunikat o błedzie.
     */
    public ExceptionApiManagment(String message) {
        super(message);
    }

    /**
     * Konstruktor klasy z obsługą tekstu i zgłaszaniem wyjątku.
     * @param message komunikat o błedzie.
     * @param cause wyjątek.
     */
    public ExceptionApiManagment(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Konstruktor klasy z zgłaszaniem wyjątku.
     * @param cause wyjątek.
     */
    public ExceptionApiManagment(Throwable cause) {
        super(cause);
    }
}
