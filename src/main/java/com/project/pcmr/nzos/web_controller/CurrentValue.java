package com.project.pcmr.nzos.web_controller;

import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.data_base.PreDataBase;

import com.project.pcmr.nzos.json_reader.FileManagement;

import java.util.List;

/**
 * Klasa obsługująca klasę z zapytaniami.
 */
public class CurrentValue extends PreDataBase {

    private final FileManagement<Long> FileMag = new FileManagement<>();

    /**
     * Deklaracja klasy.
     */
    public CurrentValue() {
        super();

    }

    /**
     * Metoda zwracająca ustawienia pompy.
     * @return zwraca ustawienia pompy.
     */
    List<Long> GetPumpSettings(){
        return FileMag.arrayToList(getDEFAULT_FILENAME(),"pump_settings");
    }

    /**
     * Metoda zwracająca ustawienia wentylatorów.
     * @return zwraca ustawienia wentylatorów.
     */
    List<Long> GetFanSettings(){
      return FileMag.arrayToList(getDEFAULT_FILENAME(),"fan_settings");
    }

    /**
     * Metoda zwraca listę z temp. CPU.
     * @return lista z temp. CPU.
     */
    List<Temperature> GetCVTEMPS() {
        return getTEMPS();
    }

    /**
     * Metoda zwracająca obciążenie CPU.
     * @return Listę z obciążeniem CPU.
     */
    List<Load> GetCVLOAD() {
        return getLOAD();
    }

    /**
     * Metoda zwracająca prędkość wentylatorów.
     * @return Listę z prędkością wentylatorów.
     */
    List<Fan> GetCVFAN() {
        return getFANS();
    }

    /**
     * Metoda zwracająca nazwę procesora.
     * @return nazwę procesora.
     */
    String getCVCPUNAME() {
        return getCPUNAME();
    }

    /**
     * Metoda zwracająca paletę kolorów.
     * @return  paletę kolorów.
     */
    public byte[] GetColourPalette() {
        return FileMag.colorArray(getDEFAULT_FILENAME());
    }

    /**
     * Metoda zwracająca aktualny tryb pracy oświetlenia.
     * @return aktualny tryb pracy oświetlenia.
     */
    Long GetCurrentColorMode() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "color_settings", "color_mode");
    }

    /**
     * Metoda zwracająca temperaturę procesora.
     * @return  temperatura procesora.
     */
    Long GetCurrentTemperature() {
        return getTEMP();
    }

    /**
     * Metoda zwracająca szybkość obrotów wentylatorów.
     * @return  szybkość obrotów wentylatorów.
     */
    Long GetCurrentFanSpeed() {
        return getFAN();
    }

    /**
     * Metoda zwracająca temp. płynu chłodniczego.
     * @return temp. płynu chłodniczego.
     */
    Long GetCurrentLiquidTemp() {
        return getLIQUID();
    }

    /**
     * Metoda zwracając prędkość wentylatora dla aktualnej temp.
     * @return  prędkość wentylatora dla aktualnej temp.
     */
    Long GetCurrentSetFanSpeed() {
        Long temp = Math.round(getTEMP() / 10.0) * 10;
        return FileMag.readingFile(getDEFAULT_FILENAME(), "fan_settings", temp + "_degrees");
    }

    /**
     * Metoda zwracając prędkość  pompy dla aktualnej temp.
     * @return  prędkość  pompy dla aktualnej temp.
     */
    Long GetCurrentSetPumpSpeed() {
        Long temp = Math.round(getTEMP() / 10.0) * 10;
        return FileMag.readingFile(getDEFAULT_FILENAME(), "pump_settings", temp + "_degrees");
    }

    /**
     * Metoda zwracająca wersję protokołu.
     * @return wersję protokołu.
     */
    Long GetCurrentProtocolVer() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "nzreal_class_version");
    }

    /**
     * Metoda zwraca identyfikator produktu.
     * @return identyfikator  produktu.
     */
    Long GetCurrentSafeCode() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "id");
    }

    /**
     * Metoda zwracająca temperaturę graniczną dla CPU.
     * @return temperaturę graniczną dla CPU.
     */
    Long GetWarningTemperature() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "temperature_warning");
    }
}
