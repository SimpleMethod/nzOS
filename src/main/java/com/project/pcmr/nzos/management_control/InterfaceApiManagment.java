package com.project.pcmr.nzos.management_control;

import java.util.List;

/**
 * Interfejs do obsługi klasy ApiManagment.
 */
interface InterfaceApiManagment {
    /**
     * Deklacaja metody służacej do zmiany pracy pompy.
     *
     * @param value Wartość w zakresie 60-100.
     */
    void changingPumpSpeed(Long value);

    /**
     * Deklacaja metody służacej do zmiany pracy wentylator.
     *
     * @param value Wartość w zakresie 25-100.
     */
    void changingFanSpeed(Long value);

    /**
     * Deklacaja metody służacej do zmiany braw.
     *
     * @param colorMode Tryb zmiany kolorów.
     */
    void changingColor(Integer colorMode);

    /**
     * Deklacaja metody służacej do zwracania temperatury CPU.
     *
     * @return Zwraca listę zawierającą temperatury rdzeni procesora.
     */
    List getCpuTemps();

    /**
     * Deklacaja metody służacej do zwracania temperatury CPU.
     *
     * @return Zwraca listę zawierającą uśrednioną temperaturę procesora.
     */
    Double getCpuTemp();

    /**
     * Deklacaja metody służacej do monitorowania pracy nzOS.
     */
    void theardHelper();


    /**
     * Deklacaja metody służacej do wyświetlania powiadomień.
     *
     * @param title    Tytuł powiadomienia
     * @param subtitle Treść powiadomienia
     * @throws Exception Wyjątek obsługujący problem z wyświetleniem komunikatu
     */
    void sendNotification(String title, String subtitle)  throws  Exception;

    /**
     * Deklacaja metody sprawdzająca, czy aplikacja została uruchomiona z prawami administratora.
     *
     * @return Zwraca prawdę w momencie, gdy aplikacja została uruchomiona z prawami administratora.
     */
    boolean isAdmin();

    /**
     * Deklacaja metody służacej do zwracania informacji o CPU.
     */
    void getCpuInfo();
}
