package com.project.pcmr.nzos.data_base;

import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.json_reader.FileManagement;


import java.util.List;

/**
 * Klasa slużaca jako kontener informacji.
 */
public class PreDataBase extends FileManagement<Long> {


    /**
     * Sterowanie kolorem (Dodatkowe funkcje).
     */
    protected static final byte[] COLOR_CUSTOM = new byte[]{0x04, 0x02, 0x06, 0x02, 0x07, 0x02, 0x02, 0x01, 0x00, 0x02, 0x01, 0x02, 0x0D, 0x00, 0x0D, 0x01, 0x0D, 0x02, 0x0D, 0x03, 0x0D, 0x04, 0x06, 0x00, 0x06, 0x01, 0x06, 0x02, 0x06, 0x03, 0x06, 0x04, 0x00, 0x01};

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

    /**
     * Metoda zwaracają czas TIMEOUT dla urządzenia.
     * @return Zwraca czas TIMEOUT dla urządzenia.
     */
    public static int getTIMEOUT() {
        return TIMEOUT;
    }

    /**
     * Metoda zapisująca nazwę procesora.
     * @param CPUNAME nazwa procesora.
     */
    public static void setCPUNAME(String CPUNAME) {
        PreDataBase.CPUNAME = CPUNAME;
    }

    /**
     * Metoda zwaracają bajt wejściowy.
     * @return bajt wejściowy.
     */
    public static byte getInEndpoint() {
        return IN_ENDPOINT;
    }

    /**
     * Metoda zwracająca numer interfejsu urządzenia.
     * @return numer interfejsu.
     */
    public static byte getINTERFACE() {
        return INTERFACE;
    }

    /**
     * Metoda zwaracają bajt wyjściowy.
     * @return bajt wyjściowy.
     */
    public static byte getOutEndpoint() {
        return OUT_ENDPOINT;
    }

    /**
     * Metoda zwraca paletę kolorów.
     * @return paleta kolorów.
     */
    public static byte[] getColorCustom() {
        return COLOR_CUSTOM;
    }

    /**
     * Metoda pobierająca informacje o wentylatorze.
     * @return zwraca informacje o wentylatorze.
     */
    public static byte[] getFanData() {
        return FAN_DATA;
    }

    /**
     * Metoda zwraca dump informacji odebranych od chłodzenia.
     * @return ump informacji odebranych od chłodzenia.
     */
    public static byte[] getOutDump() {
        return OUT_DUMP;
    }

    /**
     * Metoda zwraca informacje o pracy pompy.
     * @return  informacje o pracy pompy.
     */
    public static byte[] getPumpData() {
        return PUMP_DATA;
    }

    /**
     * Metoda zwracajaca informacje o wentylatorach zamontowanych w komputerze.
     * @return zwraca listę z wentylatorami.
     */
    public static List<Fan> getFANS() {
        return FANS;
    }

    /**
     * Metoda zwraca obciążenie każdego z rdzeni procesora.
     * @return zwraca listę użyciem rdzieni procesora.
     */
    public static List<Load> getLOAD() {
        return LOAD;
    }

    /**
     * Metoda zwraca listę z temperaturami procesora.
     * @return zwraca listę z temperaturami procesora.
     */
    public static List<Temperature> getTEMPS() {
        return TEMPS;
    }

    /**
     * Metoda zwraca identyfikator chłodzenia.
     * @return zwraca indentyfikator chłodzenia.
     */
    public static short getProductId() {
        return PRODUCT_ID;
    }

    /**
     * Metoda zwraca vendor urządzenia.
     * @return  vendor urządzenia.
     */
    public static short getVendorId() {
        return VENDOR_ID;
    }

    /**
     * Metoda zwracająca nazwę procesora.
     * @return zwraca nazwę cpu.
     */
    public static String getCPUNAME() {
        return CPUNAME;
    }

    /**
     * Metoda pobierająca nazwę pliku z konfiguracjami.
     * @return nazwa pliku z konfiguracją.
     */
    public static String getDefaultFilename() {
        return DEFAULT_FILENAME;
    }

    /**
     * Metoda, która ustawia domyślną nazwę dla pliku z konfiguracją.
     * @param defaultFilename parametr z nazwą pliku.
     */
    public static void setDefaultFilename(String defaultFilename) {
        DEFAULT_FILENAME = defaultFilename;
    }

    /**
     * Metoda do ustawienia prędkości wentylatorów.
     * @param fanData zmienna do zapisania rozkazów.
     */
    public static void setFanData(byte[] fanData) {
        FAN_DATA = fanData;
    }

    /**
     * Metoda do przypisania informacji o wentylatorach.
     * @param FANS lista z wentylatorami.
     */
    public static void setFANS(List<Fan> FANS) {
        PreDataBase.FANS = FANS;
    }

    /**
     * Metoda do przypisania obciążenia rdzenii cpu.
     * @param LOAD lista z obciążeniami rdzenii cpu.
     */
    public static void setLOAD(List<Load> LOAD) {
        PreDataBase.LOAD = LOAD;
    }


    /**
     * Metoda przypisania dumpu odebranych informacji.
     * @param outDump informacje odebrane od chłodzenia.
     */
    public static void setOutDump(byte[] outDump) {
        OUT_DUMP = outDump;
    }

    /**
     * Metoda zapisująca informacje odebrane od pompy.
     * @param pumpData zmienna do zapisu.
     */
    public static void setPumpData(byte[] pumpData) {
        PUMP_DATA = pumpData;
    }

    /**
     * Metoda zapisująca listę z temperaturami rdzenii procesora.
     * @param TEMPS zmienna do przypisania temp.
     */
    public static void setTEMPS(List<Temperature> TEMPS) {
        PreDataBase.TEMPS = TEMPS;
    }

    /**
     * Metoda zwracająca temperaturę procesora.
     * @return zwraca temp. procesora.
     */
    public static long getTEMP() {
        return TEMP;
    }

    /**
     * Metoda zwracająca prędkość wentylatorów.
     * @return zwraca prędkość wentylatorów.
     */
    public static long getFAN() {
        return FAN;
    }

    /**
     * Metoda zwracająca temperaturę płynu chłodniczego.
     * @return temperatura płynu chłodniczego.
     */
    public static long getLIQUID() {
        return LIQUID;
    }

    /**
     * Metoda przypisania prędkości wentylatorów.
     * @param FAN prędkość wentylatorów.
     */
    public static void setFAN(long FAN) {
        PreDataBase.FAN = FAN;
    }

    /**
     * Metoda przypisująca temperaturę płynu chłodzniczego.
     * @param LIQUID zmienna z temperaturą płynu chłodniczego.
     */
    public static void setLIQUID(long LIQUID) {
        PreDataBase.LIQUID = LIQUID;
    }

    /**
     * Metoda zapisująca średnią temperaturę CPU.
     * @param TEMP parametr z temperaturą.
     */
    public static void setTEMP(long TEMP) {
        PreDataBase.TEMP = TEMP;
    }


}


