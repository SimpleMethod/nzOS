package com.project.pcmr.nzos.web_controller;

import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.data_base.PreDataBase;

import com.project.pcmr.nzos.json_reader.FileManagement;

import java.util.List;

public class CurrentValue extends PreDataBase {

    FileManagement<Long> FileMag = new FileManagement<>();

    public CurrentValue() {
        super();

    }

    public List<Temperature> GetCVTEMPS() {
        return getTEMPS();
    }

    public List<Load> GetCVLOAD() {
        return getLOAD();
    }

    public List<Fan> GetCVFAN() {
        return getFANS();
    }

    public String getCVCPUNAME() {
        return getCPUNAME();
    }


    public byte[] GetColourPalette() {
        return FileMag.colorArray(getDEFAULT_FILENAME());
    }

    public Long GetCurrentColorMode() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "color_settings", "color_mode");
    }

    public Long GetCurrentTemperature() {
        return getTEMP();
    }

    public Long GetCurrentFanSpeed() {
        return getFAN();
    }

    public Long GetCurrentLiquidTemp() {
        return getLIQUID();
    }

    public Long GetCurrentSetFanSpeed() {
        Long temp = Math.round(getTEMP() / 10.0) * 10;
        return FileMag.readingFile(getDEFAULT_FILENAME(), "fan_settings", temp + "_degrees");
    }

    public Long GetCurrentSetPumpSpeed() {
        Long temp = Math.round(getTEMP() / 10.0) * 10;
        return FileMag.readingFile(getDEFAULT_FILENAME(), "pump_settings", temp + "_degrees");
    }

    public Long GetCurrentProtocolVer() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "nzreal_class_version");
    }

    public Long GetCurrentSafeCode() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "id");
    }

}
