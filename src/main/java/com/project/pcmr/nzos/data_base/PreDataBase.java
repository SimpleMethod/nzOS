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
    public static byte[] OUT_DUMP = new byte[17];
    /**
     * Domyślna nazwa pliku ustawień.
     */
    private static String DEFAULT_FILENAME;
    /**
     * Lista zawierająca temperaturę rdzeni procesora.
     */
    private static List<Temperature> TEMPS;

    /**
     * Zibieranie aktualnych informacji o systemie.
     */
    private static Integer[] CURRENT_VALUE;


    /**
     * Lista zawierająca temperaturę rdzeni procesora.
     */
    public static List<Temperature> CVTEMPS;

    /**
     * Lista zawierająca obciążenie rdzeni procesora.
     */
    public static List<Load> CVLOAD;

    /**
     * Lista zawierająca prędkość wentylatorów.
     */
    public  static List<Fan> CVFAN;
    /**
     * Pole zawierające nazwę CPU.
     */
    public static String CPUNAME;

    /**
     * Lista zawierająca krytyczne błędy.
     */
    public static List<String> ERRORS;



    public PreDataBase() {
        this.DEFAULT_FILENAME = "main.nzprofile";
    }

    public PreDataBase(String FILE_NAME) {
        this.DEFAULT_FILENAME = FILE_NAME;
    }

    /**
     * Metoda pobierające aktualne informacje o systemie.
     *
     * @return Zwraca tablicę z danymi.
     */
    public Integer[] Get_CURRENT_VALUE() {
        return CURRENT_VALUE;
    }

    /**
     * Metoda zwracająca domyślną nazwę pliku.
     *
     * @return Zwraca domyślną nazwę pliku.
     */
    public String GetDEFAULT_FILENAME() {
        return DEFAULT_FILENAME;
    }


    /**
     * Metoda zwracająca temperaturę.
     *
     * @return Zwraca dane urządzenia.
     */
    public List<Temperature> GetTEMPS() {
        return TEMPS;
    }

    /**
     * Metoda zwracająca protokół dla wentylatorów.
     *
     * @return Zwraca dane urządzenia.
     */
    public byte[] GetFAN_DATA() {
        return FAN_DATA;
    }

    /**
     * Metoda zwracająca protokół dla pompy.
     *
     * @return Zwraca dane urządzenia.
     */
    public byte[] GetPUMP_DATA() {
        return PUMP_DATA;
    }

    /**
     * Metoda zwracająca vendor ID urządzenia.
     *
     * @return Zwraca vendor urządzenia.
     */
    public short GetVENDOR_ID() {
        return VENDOR_ID;
    }

    /**
     * Metoda zwracająca product ID urządzenia.
     *
     * @return Zwraca product ID urządzenia.
     */
    public short GetPRODUCT_ID() {
        return PRODUCT_ID;
    }

    /**
     * Metoda zwracająca rodzaj interfejsu urządzenia.
     *
     * @return Zwraca rodzaj interfejsu urządzenia.
     */
    public byte GetINTERFACE() {
        return INTERFACE;
    }

    /**
     * Metoda zwracająca znacznik stanu dla wysyłanych bajtów do urządzenia.
     *
     * @return Zwraca znacznik stanu dla wysyłanych bajtów.
     */
    public byte GetIN_ENDPOINT() {
        return IN_ENDPOINT;
    }

    /**
     * Metoda zwracająca znacznik stanu dla odbieranych bajtów od urządzenia.
     *
     * @return Zwraca znacznik stanu dla odbieranych bajtów.
     */
    public byte GetOUT_ENDPOINT() {
        return OUT_ENDPOINT;
    }

    /**
     * Metoda zwracająca limit czasu połączenia od/do urządzenia.
     *
     * @return Zwraca limit czasu połączenia od/do urządzenia.
     */
    public int GetTIMEOUT() {
        return TIMEOUT;
    }

    /**
     * Metoda zwracająca odebrane bajty od urządzenia.
     *
     * @return Zwraca odebrane bajty odurządzenia.
     */
    public byte[] GetREADING_DUMP() {
        return OUT_DUMP;
    }

    /**
     * Metoda zapisująca bajty odebrane od urządzenia.
     */
    public void SetREADING_DUMP(byte[] ReadingDump) {
        this.OUT_DUMP = ReadingDump;
    }


    /**
     * Metoda zapisująca bajty odebrane od urządzenia.
     */
    public void SetTEMPS(List<Temperature> ReadingDump) {
        this.TEMPS = ReadingDump;
    }


    /**
     * Metoda zapisująca bajty protokołu dla wentylatorów.
     */
    public void SetFAN_DATA(byte[] FAN_DATA) {
        this.FAN_DATA = FAN_DATA;
    }

    /**
     * Metoda zapisująca bajty protokołu dla pompy.
     */
    public void SetPUMP_DATA(byte[] PUMP_DATA) {
        this.PUMP_DATA = PUMP_DATA;
    }


    /**
     * Metoda zapisująca domyślną nazwę pliku.
     */
    public void SetDEFAULT_FILENAME(String DEFAULT_FILENAME) {
        this.DEFAULT_FILENAME = DEFAULT_FILENAME;
    }

    /**
     * Metoda zapisująca aktualne informacje o systemie.
     *
     * @param ReadingDump dane do zapisu.
     */
    public void Set_CURRENT_VALUE(Integer[] ReadingDump) {
        this.CURRENT_VALUE = ReadingDump;
    }


}


