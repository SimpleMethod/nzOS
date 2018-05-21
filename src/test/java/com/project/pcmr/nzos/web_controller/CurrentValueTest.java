package com.project.pcmr.nzos.web_controller;

import com.project.pcmr.nzos.json_reader.FileManagement;
import com.project.pcmr.nzos.monitoring.ApiMonitoring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CurrentValueTest {
    ApiMonitoring start = new ApiMonitoring();
    CurrentValue UnitTest1 = new CurrentValue();
    @Test
    void getPumpSettings() {
        assertNotNull(UnitTest1.GetPumpSettings());
    }

    @Test
    void getFanSettings() {
        assertNotNull(UnitTest1.GetFanSettings());
    }

    @Test
    void getCVTEMPS() {
        assertNotNull(UnitTest1.GetCVTEMPS());
    }

    @Test
    void getCVLOAD() {
        assertNotNull(UnitTest1.GetCVLOAD());
    }

    @Test
    void getCVFAN() {
        assertNotNull(UnitTest1.GetCVFAN());
    }

    @Test
    void getCVCPUNAME() {
        assertNotNull(UnitTest1.getCVCPUNAME());
    }

    @Test
    void getColourPalette() {
        assertNotNull(UnitTest1.GetColourPalette());
    }

    @Test
    void getCurrentColorMode() {
        assertNotNull(UnitTest1.GetCurrentColorMode());
    }

    @Test
    void getCurrentTemperature() {
        assertNotNull(UnitTest1.GetCurrentTemperature());
    }

    @Test
    void getCurrentFanSpeed() {
        assertNotNull(UnitTest1.GetCurrentFanSpeed());
    }

    @Test
    void getCurrentLiquidTemp() {
        assertNotNull(UnitTest1.GetCurrentLiquidTemp());
    }

    @Test
    void getCurrentSetFanSpeed() {
        assertNotNull(UnitTest1.GetCurrentSetFanSpeed());
    }

    @Test
    void getCurrentSetPumpSpeed() {
        assertNotNull(UnitTest1.GetCurrentSetPumpSpeed());
    }

    @Test
    void getCurrentProtocolVer() {
        assertNotNull(UnitTest1.GetCurrentProtocolVer());
    }

    @Test
    void getCurrentSafeCode() {
        assertNotNull(UnitTest1.GetCurrentSafeCode());
    }

    @Test
    void getWarningTemperature() {
        assertNotNull(UnitTest1.GetWarningTemperature());
    }

    @BeforeEach
    void setUp() {
        start.startTheard();
    }
}