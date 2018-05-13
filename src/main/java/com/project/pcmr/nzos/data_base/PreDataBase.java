package com.project.pcmr.nzos.data_base;


import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.json_reader.FileManagement;


import java.util.List;

public class PreDataBase extends FileManagement<Long> {


    /**
     * Sterowanie kolorem (Dodatkowe funkcje).
     */
    protected static final byte[] COLOR_CUSTOM = new byte[]{0x04, 0x02, 0x06, 0x02, 0x07, 0x02, 0x02, 0x01, 0x00, 0x02, 0x01, 0x02, 0x0D, 0x00, 0x0D, 0x01, 0x0D, 0x02, 0x0D, 0x03, 0x0D, 0x04, 0x06, 0x00, 0x06, 0x01, 0x06, 0x02, 0x06, 0x03, 0x06, 0x04, 0x00, 0x01};
           /*
       Dostępne zwykłe tryby:

0x04, 0x02	Fala
------
0x06, 0x02	Odychanie
------
0x07, 0x02	Pulsowanie
------
0x02, 0x01	Randomizacja barw
------
0x00, 0x02	Stały
------
0x01, 0x02	Zanikanie

Dostępne tryby custom:

0x0D, 0x00 Fala (Najwolniejszy)
0x0D, 0x01 Fala (Wolne)
0x0D, 0x02 Fala (Normalne)
0x0D, 0x03 Fala (Szybkie)
0x0D, 0x04 Fala (Najszybszy)
------
0x06, 0x00 Odychanie (Najwolniejszy)
0x06, 0x01 Odychanie (Wolne)
0x06, 0x02 Odychanie (Normalne)
0x06, 0x03 Odychanie (Szybkie)
0x06, 0x04 Odychanie (Najszybszy)
------
0x00, 0x01	Stały
------
        */

    public PreDataBase() {
        this.DEFAULT_FILENAME = "main.nzprofile";
    }

    public PreDataBase(String FILE_NAME) {
        this.DEFAULT_FILENAME = FILE_NAME;
    }

    /**
     * Sterowanie wentylatorami.
     */
    private static byte[] FAN_DATA = new byte[]{0x02, 0x4d, 0x00, 0x00, 0x64};
    /**
     * Sterowanie pracą pompy.
     */
    private static byte[] PUMP_DATA = new byte[]{0x02, 0x4d, 0x40, 0x00, 0x64};
    /**
     * Vendor ID dla urządzenia.
     */
    private static final short VENDOR_ID = 0x1e71;
    /**
     * Product ID dla urządzenia.
     */
    private static final short PRODUCT_ID = 0x170e;
    /**
     * Numer interfejsu dla urządzenia.
     */
    private static final byte INTERFACE = 0;
    /**
     * Wejściowy endpoint dla urządzenia.
     */
    private static final byte IN_ENDPOINT = (byte) 0x81;
    /**
     * Wyjściowy endpoint dla urządzenia.
     */
    private static final byte OUT_ENDPOINT = 0x01;
    /**
     * Czas komunikacji dla I/O.
     */
    private static final int TIMEOUT = 5000;

    /**
     * Dump informacji oderanych od urządzenia.
     */
    private static byte[] OUT_DUMP = new byte[17];
    /**
     * Domyślna nazwa pliku ustawień.
     */
    private static String DEFAULT_FILENAME;
    /**
     * Lista zawierająca temperaturę rdzeni procesora.
     */
    private static List<Temperature> TEMPS;

    /**
     * Lista zawierająca obciążenie rdzeni procesora.
     */
    private static List<Load> LOAD;

    /**
     * Lista zawierająca prędkość wentylatorów.
     */
    private static List<Fan> FANS;
    /**
     * Pole zawierające nazwę CPU.
     */
    private static String CPUNAME;

    /**
     * Pole zawierające temperaturę CPU.
     */
    private static long TEMP;

    /**
     * Pole zawierające prędkość wentylatora wyrażoną w RPM.
     */
    private static long FAN;

    /**
     * Pole zawierające temperaturę CPU.
     */
    private static long LIQUID;
    /**
     * Metoda zwracająca domyślną nazwę pliku.
     *
     * @return Zwraca domyślną nazwę pliku.
     */
    public String getDEFAULT_FILENAME() {
        return DEFAULT_FILENAME;
    }

    public static int getTIMEOUT() {
        return TIMEOUT;
    }

    public static void setCPUNAME(String CPUNAME) {
        PreDataBase.CPUNAME = CPUNAME;
    }

    public static byte getInEndpoint() {
        return IN_ENDPOINT;
    }

    public static byte getINTERFACE() {
        return INTERFACE;
    }

    public static byte getOutEndpoint() {
        return OUT_ENDPOINT;
    }

    public static byte[] getColorCustom() {
        return COLOR_CUSTOM;
    }

    public static byte[] getFanData() {
        return FAN_DATA;
    }

    public static byte[] getOutDump() {
        return OUT_DUMP;
    }

    public static byte[] getPumpData() {
        return PUMP_DATA;
    }

    public static List<Fan> getFANS() {
        return FANS;
    }

    public static List<Load> getLOAD() {
        return LOAD;
    }

    public static List<Temperature> getTEMPS() {
        return TEMPS;
    }

    public static short getProductId() {
        return PRODUCT_ID;
    }

    public static short getVendorId() {
        return VENDOR_ID;
    }

    public static String getCPUNAME() {
        return CPUNAME;
    }

    public static String getDefaultFilename() {
        return DEFAULT_FILENAME;
    }

    public static void setDefaultFilename(String defaultFilename) {
        DEFAULT_FILENAME = defaultFilename;
    }

    public static void setFanData(byte[] fanData) {
        FAN_DATA = fanData;
    }

    public static void setFANS(List<Fan> FANS) {
        PreDataBase.FANS = FANS;
    }

    public static void setLOAD(List<Load> LOAD) {
        PreDataBase.LOAD = LOAD;
    }

    public static void setOutDump(byte[] outDump) {
        OUT_DUMP = outDump;
    }

    public static void setPumpData(byte[] pumpData) {
        PUMP_DATA = pumpData;
    }

    public static void setTEMPS(List<Temperature> TEMPS) {
        PreDataBase.TEMPS = TEMPS;
    }

    public static long getTEMP() {
        return TEMP;
    }


    public static long getFAN() {
        return FAN;
    }

    public static long getLIQUID() {
        return LIQUID;
    }

    public static void setFAN(long FAN) {
        PreDataBase.FAN = FAN;
    }

    public static void setLIQUID(long LIQUID) {
        PreDataBase.LIQUID = LIQUID;
    }


    public static void setTEMP(long TEMP) {
        PreDataBase.TEMP = TEMP;
    }


}


