package com.project.pcmr.nzos.monitoring;

import java.io.IOException;

interface InterfaceApiMonitoring {

    /**
     * Deklacaja metody służacej do monitorowania temperatury.
     */
    void MonitoringTemperature() throws IOException;

    /**
     * Deklacaja metody sprawdzająca, czy aplikacja została uruchomiona z prawami administratora.
     *
     * @return Zwraca prawdę w momencie, gdy aplikacja została uruchomiona z prawami administratora.
     */
    boolean isAdmin();
}
