package com.project.pcmr.nzos.web_controller;

import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.data_base.PreDataBase;
import com.project.pcmr.nzos.json_reader.FileManagement;
import com.project.pcmr.nzos.management_control.ApiManagment;
import org.json.simple.JSONObject;

import java.io.StringWriter;
import java.util.List;

public class CurrentValue extends PreDataBase {

    static com.project.pcmr.nzos.data_base.CurrentValue CV = new com.project.pcmr.nzos.data_base.CurrentValue();
    FileManagement<Long> FileMag = new FileManagement<>();
    ApiManagment ApiMag = new ApiManagment();
    private Long CurrentTemperature;

    private Long CurrentFanSpeed;

    private Long CurrentLiquidTemp;

    private Long CurrentSetFanSpeed;

    private Long CurrentSetPumpSpeed;

    private Long CurrentProtocolVer;

    private Long CurrentColorMode;

    private Long CurrentColorValue;

    private Long CurrentSafeCode;

    private byte[] ColourPalette;



    /**
     * Lista zawierająca temperaturę rdzeni procesora.
     */
    public  List<Temperature> TEMPS;

    /**
     * Lista zawierająca obciążenie rdzeni procesora.
     */
    public  List<Load> LOAD;

    /**
     * Lista zawierająca prędkość wentylatorów.
     */
    public  List<Fan> FAN;
    /**
     * Pole zawierające nazwę CPU.
     */
    public String CVCPUNAME;




    public CurrentValue() {
        super();

    }

    public List<Temperature> GetCVTEMPS() {
        TEMPS=CVTEMPS;
        return TEMPS;
    }

    public List<Load> GetCVLOAD() {
        LOAD=CVLOAD;
        return LOAD;
    }

    public List<Fan> GetCVFAN() {
        FAN=CVFAN;
        return FAN;
    }

    public String getCVCPUNAME() {
        CVCPUNAME=CPUNAME;
        return CVCPUNAME;
    }


    public byte[] GetColourPalette() {
        ColourPalette = FileMag.ColorArray(GetDEFAULT_FILENAME());
        return ColourPalette;
    }

    public Long GetCurrentColorMode() {
        CurrentColorMode = CV.CURRENT_COLOR_MODE = FileMag.ReadingFile(GetDEFAULT_FILENAME(), "color_settings", "color_mode");
        return CurrentColorMode;

    }

    public Long GetCurrentTemperature() {
        CurrentTemperature = CV.CURRENT_TEMPERATURE;
        return CurrentTemperature;

    }

    public Long GetCurrentFanSpeed() {
        CurrentFanSpeed = CV.CURRENT_FAN_SPEED;
        return CurrentFanSpeed;
    }

    public Long GetCurrentLiquidTemp() {
        CurrentLiquidTemp = CV.CURRENT_LIQUID_TEMPERATURE;
        return CurrentLiquidTemp;
    }

    public Long GetCurrentSetFanSpeed() {
        Long temp = Math.round(CurrentTemperature / 10.0) * 10;
        CV.CURRENT_SETTINGS_FAN_SPEED = FileMag.ReadingFile(GetDEFAULT_FILENAME(), "fan_settings", temp + "_degrees");
        CurrentSetFanSpeed = CV.CURRENT_SETTINGS_FAN_SPEED;
        return CurrentSetFanSpeed;
    }

    public Long GetCurrentSetPumpSpeed() {
        Long temp = Math.round(CurrentTemperature / 10.0) * 10;
        CV.CURRENT_SETTINGS_PUMP_SPEED = FileMag.ReadingFile(GetDEFAULT_FILENAME(), "pump_settings", temp + "_degrees");
        CurrentSetPumpSpeed = CV.CURRENT_SETTINGS_PUMP_SPEED;
        return CurrentSetPumpSpeed;
    }

    public Long GetCurrentProtocolVer() {
        CV.CURRENT_PROTOCOL_VERSION = FileMag.ReadingFile(GetDEFAULT_FILENAME(), "nzreal_class_version");
        CurrentProtocolVer = CV.CURRENT_PROTOCOL_VERSION;
        return CurrentProtocolVer;
    }

    public Long GetCurrentSafeCode() {
        CV.CURRENT_SAFE_CODE = FileMag.ReadingFile(GetDEFAULT_FILENAME(), "id");
        CurrentSafeCode = CV.CURRENT_SAFE_CODE;
        return CurrentSafeCode;
    }

}
