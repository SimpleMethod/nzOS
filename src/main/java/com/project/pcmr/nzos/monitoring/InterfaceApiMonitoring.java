package com.project.pcmr.nzos.monitoring;

import java.io.IOException;

/**
 * Interfejs dla klasy ApiMonitoring
 */
interface InterfaceApiMonitoring {

    /**
     * Deklacaja metody służacej startu wątku.
     * @throws IOException wyjątek interfejsu.
     */
    void startTheard() throws IOException;
    
}
