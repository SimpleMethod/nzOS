package com.project.pcmr.nzos.web_controller;

import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.management_control.ApiManagment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Klasa obsługi zapytań REST.
 */
@RestController
public class CurrentValueController extends CurrentValue {
   private final ApiManagment Api = new ApiManagment();

    /**
     *  Metoda do obsługi statycznych plików.
     * @return zwraca plik index.html
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * Metoda wymuszająca zmianę koloru.
     */
    @RequestMapping("/execution/color")
    public void executionColor() {
        Api.changingColor((int) (long) GetCurrentColorMode());
    }

    /**
     * Metoda aktualizująca poszczególny kolor z osobna.
     * @param range numer koloru.
     * @param range2 rodzaj koloru.
     * @param value wartość.
     */
    @RequestMapping("/update/colorvalue/{range}/{range2}/{value}")
    public void updateColorValue(@PathVariable Long range, @PathVariable String range2, @PathVariable Long value) {
        if((range>=0 && range<=8) && (value>=0 && value<=255))
        {
            writingFile(getDEFAULT_FILENAME(), "color_settings", "color_" + range, "color_" + range2, value);
        }
    }

    /**
     * Metoda aktualizująca wszystkie kolory jako jeden.
     * @param value dany kolor.
     */
    @RequestMapping("/update/forcecolorvalue/{value}")
    public void updateForceColorValue( @PathVariable Long value) {
        if(value>=0 && value<=255)
        {
       forceWritingFile(getDEFAULT_FILENAME(),"color_settings",value);
        }
    }

    /**
     * Metoda aktualizująca dany kolor.
     * @param value wartość koloru.
     * @param type  typ koloru.
     */
    @RequestMapping("/update/forcecolorvalue/{type}/{value}")
    public void updateForceColorValueRGB( @PathVariable Long value, @PathVariable String type) {
        if(value>=0 && value<=255)
        {
            forceWritingFile(getDEFAULT_FILENAME(),"color_settings",type,value);
        }
    }

    /**
     * Metoda aktualizująca tryb pracy oświetlenia.
     * @param value tryb pracy.
     */
    @RequestMapping("/update/colormode/{value}")
    public void updateColorMode(@PathVariable Long value) {
        if ((value >= 0 && value <= 32)) {
            writingFile(getDEFAULT_FILENAME(), "color_settings", "color_mode", value);
        }
    }

    /**
     * Metoda aktualizująca temp. graniczną dla CPU.
     * @param value temp. graniczną dla CPU.
     */
    @RequestMapping("/update/temperaturewarning/{value}")
    public void updateTemperatureWarning(@PathVariable Long value) {
        if ((value >= 20 && value <= 100)) {
            writingFile(getDEFAULT_FILENAME(), "temperature_warning", value);
        }
    }

    /**
     * Metoda aktualizująca język aplikacji.
     * @param value język aplikacji.
     */
    @RequestMapping("/update/language/{value}")
    public void updateLanguage(@PathVariable Long value) {
        if ((value >= 0 && value <= 1)) {
            writingFile(getDEFAULT_FILENAME(), "language_version", value);
        }
    }

    /**
     * Metoda zwracająca ustawiony język.
     * @return zwraca język.
     */
    @RequestMapping(value = "/show/language", produces = "application/json", method = RequestMethod.GET)
    public List<Long>  showLanguage() {
        ArrayList<Long> al = new ArrayList<>();
        al.add( readingFile(getDEFAULT_FILENAME(), "language_version"));
        return al;

    }

    /**
     * Metoda aktualizacuje pędkość pompy dla danego progu temp.
     * @param range próg temp.
     * @param value wartość.
     */
    @RequestMapping("/update/pumpspeed/{range}/{value}")
    public void updatePumpSpeed(@PathVariable Long range, @PathVariable Long value) {
        if ((range >= 0 && range <= 100) && (value >= 20 && value <= 100)) {
            writingFile(getDEFAULT_FILENAME(), "pump_settings", range + "_degrees", value);
        }
    }

    /**
     * Metoda aktualizacuje pędkość wentylatorów dla danego progu temp.
     * @param range próg temp.
     * @param value wartość.
     */
    @RequestMapping("/update/fanspeed/{range}/{value}")
    public void updateFanSpeed(@PathVariable Long range, @PathVariable Long value) {
        if ((range >= 0 && range <= 100) && (value >= 20 && value <= 100)) {
            writingFile(getDEFAULT_FILENAME(), "fan_settings", range + "_degrees", value);
        }
    }

    /**
     * Metoda zwracająca temp. graniczną dla CPU.
     * @return temp. graniczną dla CPU.
     */
    @RequestMapping(value = "/show/temperaturewarning", produces = "application/json", method = RequestMethod.GET)
    public ArrayList<Long> getWarningTemperature() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetWarningTemperature());
        return al;
    }

    /**
     * Metoda zwraca listę błędów.
     * @return listę błędów.
     */
    @RequestMapping(value = "/show/errors", produces = "application/json", method = RequestMethod.GET)
    public ArrayList<String> getErrorShow() {
        return showLogFile("app_log.log");
    }

    /**
     * Metoda zwraca logi błędów.
     * @param value  nazwa pliku do odczytu.
     * @return listę z błędami.
     */
    @RequestMapping(value = "/show/logs/{value}", produces = "application/json", method = RequestMethod.GET)
    public ArrayList<String> getLogsShow(@PathVariable String value) {
        return showLogFile(value + ".log");
    }

    /**
     * Metoda zwraca nazwę procesora.
     * @return nazwę procesora.
     */
    @RequestMapping(value = "/show/cpu/name", produces = "application/json", method = RequestMethod.GET)
    public List<String> getCpuNameShow() {
        ArrayList<String> al = new ArrayList<>();
        al.add(getCPUNAME());
        return al;
    }

    /**
     * Metoda zwracająca temp. procesora.
     * @return temp. procesora.
     */
    @RequestMapping(value = "/show/cpu/temp", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getTempShow() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentTemperature());
        return al;
    }

    /**
     * Metoda zwracająca temp. rdzeni procesora.
     * @return rdzeni procesora.
     */
    @RequestMapping(value = "/show/cpu/temps", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getTempsShow() {
        ArrayList<Double> al = new ArrayList<>();
        for (final Temperature temp : GetCVTEMPS()) {
            al.add(temp.value);
        }

        return al;
    }

    /**
     * Metoda zwraca listę z obciążeniem CPU.
     * @return listę z obciążeniem CPU.
     */
    @RequestMapping(value = "/show/cpu/loads", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getLoadsshow() {
        ArrayList<Double> al = new ArrayList<>();
        if(GetCVLOAD()== null)
        {
            return null;

        }
        else
        {
            for (final Load loads : GetCVLOAD()) {
                al.add(loads.value);
            }
            return al;
        }
    }

    /**
     * Metoda zwraca listę z wentylatorami.
     * @return listę z wentylatorami.
     */
    @RequestMapping(value = "/show/cpu/fans", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getFanshow() {
        ArrayList<Double> al = new ArrayList<>();
        for (final Fan fan : GetCVFAN()) {
            al.add(fan.value);
        }

        return al;
    }

    /**
     * Metoda zwraca prędkość wentylatorów.
     * @return prędkość wentylatorów.
     */
    @RequestMapping(value = "/show/nzos/fanspeed", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getFanSpeed() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentFanSpeed());
        return al;
    }

    /**
     * Metoda zwraca temp. płynu chłodniczego.
     * @return temp. płynu chłodniczego.
     */
    @RequestMapping(value = "/show/nzos/liquidtemp", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getLiquidTemp() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentLiquidTemp());
        return al;
    }

    /**
     * Metoda zwraca najważniejsze informacje o pracy systemu.
     * @return  najważniejsze informacje o pracy systemu.
     */
    @RequestMapping(value = "/show/nzos/mainshow", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getCriticalVariables() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentTemperature());
        al.add(GetCurrentFanSpeed());
        al.add(GetCurrentLiquidTemp());
        return al;
    }

    /**
     * Metoda zwraca ustawienia wentylatorów.
     * @return ustawienia wentylatorów.
     */
    @RequestMapping(value = "/show/nzos/fansettings", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getFanSettings() {
        return GetFanSettings();
    }

    /**
     * Metoda zwraca ustawienia pompy.
     * @return ustawienia pompy.
     */
    @RequestMapping(value = "/show/nzos/pumpsettings", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getPumpSettings() {
        return GetPumpSettings();
    }

    /**
     * Metoda zwraca aktualną prędkość wentylatorów, pompy, wersję protokołu i indentyfikator sprzętu.
     * @return aktualną prędkość wentylatorów, pompy, wersję protokołu i indentyfikator sprzętu.
     */
    @RequestMapping(value = "/show/nzos/dumpsettings", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getVariables() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentSetFanSpeed());
        al.add(GetCurrentSetPumpSpeed());
        al.add(GetCurrentProtocolVer());
        al.add(GetCurrentSafeCode());
        return al;
    }

    /**
     * Metoda zwraca tryb pracy oświetlenia.
     * @return tryb pracy oświetlenia.
     */
    @RequestMapping(value = "/show/nzos/colormode", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getColorMode() {

        ArrayList<Long> aMODE = new ArrayList<>();
        aMODE.add(GetCurrentColorMode());
        return aMODE;
    }

    /**
     * Metoda zwraca tablicę z kolorami.
     * @return tablicę z kolorami.
     */
    @RequestMapping(value = "/show/nzos/colorarray", produces = "application/json", method = RequestMethod.GET)
    public List<ArrayList> getColor() {

        long[] dump = colorLongArray(getDefaultFilename());
        ArrayList<Long> a0 = new ArrayList<>();
        ArrayList<Long> a1 = new ArrayList<>();
        ArrayList<Long> a2 = new ArrayList<>();
        ArrayList<Long> a3 = new ArrayList<>();
        ArrayList<Long> a4 = new ArrayList<>();
        ArrayList<Long> a5 = new ArrayList<>();
        ArrayList<Long> a6 = new ArrayList<>();
        ArrayList<Long> a7 = new ArrayList<>();
        ArrayList<Long> a8 = new ArrayList<>();
        ArrayList<ArrayList> result = new ArrayList<>();
        a0.add(dump[0]);
        a0.add(dump[1]);
        a0.add(dump[2]);
        a1.add(dump[3]);
        a1.add(dump[4]);
        a1.add(dump[5]);
        a2.add(dump[6]);
        a2.add(dump[7]);
        a2.add(dump[8]);
        a3.add(dump[9]);
        a3.add(dump[10]);
        a3.add(dump[11]);
        a4.add(dump[12]);
        a4.add(dump[13]);
        a4.add(dump[14]);
        a5.add(dump[15]);
        a5.add(dump[16]);
        a5.add(dump[17]);
        a6.add(dump[18]);
        a6.add(dump[19]);
        a6.add(dump[20]);
        a7.add(dump[21]);
        a7.add(dump[22]);
        a7.add(dump[23]);
        a8.add(dump[24]);
        a8.add(dump[25]);
        a8.add(dump[26]);
        result.add(a0);result.add(a1);result.add(a2);result.add(a3); result.add(a4);result.add(a5);result.add(a6);result.add(a7); result.add(a8);
        return result;
    }
}