package com.project.pcmr.nzos.web_controller;

import com.project.pcmr.nzos.data_base.PreDataBase;
import com.project.pcmr.nzos.json_reader.FileManagement;
import com.project.pcmr.nzos.management_control.ApiManagment;
import org.json.simple.JSONObject;

import java.io.StringWriter;

public class CurrentValue extends PreDataBase{

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


    public CurrentValue ()
    {
        super();

    }

    public byte[] GetColourPalette()
    {
        ColourPalette=FileMag.ColorArray(GetDEFAULT_FILENAME());
        return ColourPalette;
    }
    public Long GetCurrentColorMode()
    {
        CurrentColorMode = CV.CURRENT_COLOR_MODE=FileMag.ReadingFile(GetDEFAULT_FILENAME(),"color_settings","color_mode");
        return  CurrentColorMode;

    }

    public Long GetCurrentTemperature()
    {
        CurrentTemperature=CV.CURRENT_TEMPERATURE;
        return CurrentTemperature;

    }
    public Long GetCurrentFanSpeed()
    {
        CurrentFanSpeed=CV.CURRENT_FAN_SPEED;
        return CurrentFanSpeed;
    }

    public Long GetCurrentLiquidTemp() {
        CurrentLiquidTemp=CV.CURRENT_LIQUID_TEMPERATURE;
        return CurrentLiquidTemp;
    }

    public Long GetCurrentSetFanSpeed()
    {
        Long temp=Math.round(CurrentTemperature / 10.0) * 10;
        CV.CURRENT_SETTINGS_FAN_SPEED=FileMag.ReadingFile(GetDEFAULT_FILENAME(),"fan_settings", temp+ "_degrees");
        CurrentSetFanSpeed=CV.CURRENT_SETTINGS_FAN_SPEED;
        return CurrentSetFanSpeed;
    }

    public Long GetCurrentSetPumpSpeed()
    {
        Long temp=Math.round(CurrentTemperature / 10.0) * 10;
        CV.CURRENT_SETTINGS_PUMP_SPEED=FileMag.ReadingFile(GetDEFAULT_FILENAME(),"pump_settings", temp+ "_degrees");
        CurrentSetPumpSpeed=CV.CURRENT_SETTINGS_PUMP_SPEED;
        return CurrentSetPumpSpeed;
    }

    public Long GetCurrentProtocolVer()
    {
        CV.CURRENT_PROTOCOL_VERSION=FileMag.ReadingFile(GetDEFAULT_FILENAME(),"nzreal_class_version");
        CurrentProtocolVer=CV.CURRENT_PROTOCOL_VERSION;
        return CurrentProtocolVer;
    }

    public Long GetCurrentSafeCode()
    {
        CV.CURRENT_SAFE_CODE=FileMag.ReadingFile(GetDEFAULT_FILENAME(),"id");
        CurrentSafeCode=CV.CURRENT_SAFE_CODE;
        return CurrentSafeCode;
    }

}
