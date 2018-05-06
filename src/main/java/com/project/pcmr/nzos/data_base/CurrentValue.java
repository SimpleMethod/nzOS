package com.project.pcmr.nzos.data_base;

public class CurrentValue extends PreDataBase {

    /**
     * Aktualna temperatura;
     */
    public static volatile Long CURRENT_TEMPERATURE;

    /**
     * Aktualny poziom wentylatorów;
     */
    public static volatile Long CURRENT_FAN_SPEED;

    /**
     * Aktualna temperatura płynu;
     */
    public static volatile Long CURRENT_LIQUID_TEMPERATURE;

    /**
     * Aktualnie zapisana prędkość wentylatorów, przy aktualnej temperaturze.
     */
    public static volatile Long CURRENT_SETTINGS_FAN_SPEED;

    /**
     * Aktualnie zapisana prędkość pompy, przy aktualnej temperaturze.
     */
    public static volatile Long CURRENT_SETTINGS_PUMP_SPEED;

    /**
     * Aktualnie zapisana wersja protokołu.
     */
    public static volatile Long CURRENT_PROTOCOL_VERSION;

    /**
     * Aktualnie zapisany tryb pracy.
     */
    public static volatile Long CURRENT_COLOR_MODE;

    /**
     * Pole służace do zwracania koloru.
     */
    public static volatile Long CURRENT_COLOR_VALUE;

    /**
     * Pole służace do synchronizacji.
     */
    public static volatile Long CURRENT_SAFE_CODE;


}
