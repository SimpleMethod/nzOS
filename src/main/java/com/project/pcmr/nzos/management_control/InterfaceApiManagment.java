package com.project.pcmr.nzos.management_control;

import java.util.List;

interface InterfaceApiManagment {
    /**
     * Deklacaja metody służacej do zmiany pracy pompy.
     *
     * @param VALUE Wartość w zakresie 60-100.
     */
    void ChangingPumpSpeed(Long VALUE);

    /**
     * Deklacaja metody służacej do zmiany pracy wentylator.
     *
     * @param VALUE Wartość w zakresie 25-100.
     */
    void ChangingFanSpeed(Long VALUE);

    /**
     * Deklacaja metody służacej do zmiany braw.
     *
     * @param COLOR_MODE Tryb zmiany kolorów.
     */
    void ChangingColor(Integer COLOR_MODE);

    /**
     * Deklacaja metody służacej do zwracania temperatury CPU.
     *
     * @return Zwraca listę zawierającą temperatury rdzeni procesora.
     */
    List GetCpuTemps();

    /**
     * Deklacaja metody służacej do zwracania temperatury CPU.
     *
     * @return Zwraca listę zawierającą uśrednioną temperaturę procesora.
     */
    Double GetCpuTemp();

    /**
     * Deklacaja metody służacej do monitorowania pracy nzOS.
     */
    void TheardHelper();

    /**
     * Deklacaja metody służacej do wyświetlania powiadomień.
     *
     * @param title    Tytuł powiadomienia
     * @param subtitle Treść powiadomienia
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
    void GetCpuInfo();
}
