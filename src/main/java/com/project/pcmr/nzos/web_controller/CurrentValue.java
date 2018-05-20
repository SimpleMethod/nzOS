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

    FileManagement<Long> FileMag = new FileManagement<>();

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
    public List<Long> GetPumpSettings(){
        return FileMag.arrayToList(getDEFAULT_FILENAME(),"pump_settings");
    }

    /**
     * Metoda zwracająca ustawienia wentylatorów.
     * @return zwraca ustawienia wentylatorów.
     */
    public List<Long> GetFanSettings(){
      return FileMag.arrayToList(getDEFAULT_FILENAME(),"fan_settings");
    }

    /**
     * Metoda zwaca listę z temp. CPU.
     * @return lusta z temp. CPU.
     */
    public List<Temperature> GetCVTEMPS() {
        return getTEMPS();
    }

    /**
     * Metoda zwracająca obciążenie CPU.
     * @return obciążenie CPU.
     */
    public List<Load> GetCVLOAD() {
        return getLOAD();
    }

    /**
     * Metoda zwracająca prędkość wentylatorów.
     * @return prędkość wentylatorów.
     */
    public List<Fan> GetCVFAN() {
        return getFANS();
    }

    /**
     * Metoda zwracająca nazwę procesora.
     * @return nazwę procesora.
     */
    public String getCVCPUNAME() {
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
     * Metoda zwracająca aktualny tyb pracy oświetlenia.
     * @return aktualny tyb pracy oświetlenia.
     */
    public Long GetCurrentColorMode() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "color_settings", "color_mode");
    }

    /**
     * Metoda zwracająca temperaturę procesora.
     * @return  temperatura procesora.
     */
    public Long GetCurrentTemperature() {
        return getTEMP();
    }

    /**
     * Metoda zwracająca szybkość obrotów wentylatorów.
     * @return  szybkość obrotów wentylatorów.
     */
    public Long GetCurrentFanSpeed() {
        return getFAN();
    }

    /**
     * Metoda zwracająca temp. płynu chłodniczego.
     * @return temp. płynu chłodniczego.
     */
    public Long GetCurrentLiquidTemp() {
        return getLIQUID();
    }

    /**
     * Metoda zwracając prędkosć wentylatora dla aktualnej temp.
     * @return  prędkosć wentylatora dla aktualnej temp.
     */
    public Long GetCurrentSetFanSpeed() {
        Long temp = Math.round(getTEMP() / 10.0) * 10;
        return FileMag.readingFile(getDEFAULT_FILENAME(), "fan_settings", temp + "_degrees");
    }

    /**
     * Metoda zwracając prędkosć pompy dla aktualnej temp.
     * @return  prędkosć pompy dla aktualnej temp.
     */
    public Long GetCurrentSetPumpSpeed() {
        Long temp = Math.round(getTEMP() / 10.0) * 10;
        return FileMag.readingFile(getDEFAULT_FILENAME(), "pump_settings", temp + "_degrees");
    }

    /**
     * Metoda zwracająca wersję protokołu.
     * @return wersję protokołu.
     */
    public Long GetCurrentProtocolVer() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "nzreal_class_version");
    }

    /**
     * Metoda zwraca indentyfikator produktu.
     * @return indentyfikator produktu.
     */
    public Long GetCurrentSafeCode() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "id");
    }

    /**
     * Metoda zwracająca temperaturę graniczną dla CPU.
     * @return temperaturę graniczną dla CPU.
     */
    public Long GetWarningTemperature() {
        return FileMag.readingFile(getDEFAULT_FILENAME(), "temperature_warning");
    }
}
