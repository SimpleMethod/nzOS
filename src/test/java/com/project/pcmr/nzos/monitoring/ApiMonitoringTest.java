package com.project.pcmr.nzos.monitoring;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;


class ApiMonitoringTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    @Test
    void monitoringTemperature() {
        exceptions.expect(IllegalAccessError.class);
        ApiMonitoring UnitTest1 = new ApiMonitoring();
        UnitTest1.startTheard();
    }

}