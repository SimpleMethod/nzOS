package com.project.pcmr.nzos.web_controller;

import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.management_control.ApiManagment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CurrentValueController extends CurrentValue {
    ApiManagment Api = new ApiManagment();

    @RequestMapping("/")
    public String test() {
        return "<div id=\"d1\" style=\"width:100%; text-align:center\"><img id=\"d2\" src=\"http://seriouscat.com/serious_cat.jpg\"/></div>";
    }

    @RequestMapping("/execution/color")
    public void executionColor() {
        Api.changingColor((int) (long) GetCurrentColorMode());
    }

    @RequestMapping("/update/colorvalue/{range}/{range2}/{value}")
    public void updateColorValue(@PathVariable Long range, @PathVariable String range2, @PathVariable Long value) {
        writingFile(getDEFAULT_FILENAME(), "color_settings", "color_" + range, "color_" + range2, value);

    }

    @RequestMapping("/update/colormode/{value}")
    public void updateColorMode(@PathVariable Long value) {
        if ((value >= 0 && value <= 32)) {
            writingFile(getDEFAULT_FILENAME(), "color_settings", "color_mode", value);
        }
    }

    @RequestMapping("/update/pumpspeed/{range}/{value}")
    public void updatePumpSpeed(@PathVariable Long range, @PathVariable Long value) {
        if ((range >= 0 && range <= 100) && (value >= 25 && value <= 100)) {
            writingFile(getDEFAULT_FILENAME(), "pump_settings", range + "_degrees", value);
        }
    }

    @RequestMapping("/update/fanspeed/{range}/{value}")
    public void updateFanSpeed(@PathVariable Long range, @PathVariable Long value) {
        if ((range >= 0 && range <= 100) && (value >= 25 && value <= 100)) {
            writingFile(getDEFAULT_FILENAME(), "fan_settings", range + "_degrees", value);
        }
    }


        @RequestMapping(value = "/show/errors", produces = "application/json", method = RequestMethod.GET)
        public void getErrorshow() {
             showLogFile("app_log.log");
        }

    @RequestMapping(value = "/show/cpu/name", produces = "application/json", method = RequestMethod.GET)
    public List<String> getCpuNameshow() {
        ArrayList<String> al = new ArrayList<>();
        al.add(getCVCPUNAME());
        return al;
    }

    @RequestMapping(value = "/show/cpu/temp", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getTempshow() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentTemperature());
        return al;
    }

    @RequestMapping(value = "/show/cpu/temps", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getTempsshow() {
        ArrayList<Double> al = new ArrayList<>();
        for (final Temperature temp : GetCVTEMPS()) {
            al.add(temp.value);
        }

        return al;
    }

    @RequestMapping(value = "/show/cpu/loads", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getLoadsshow() {
        ArrayList<Double> al = new ArrayList<>();
        for (final Load loads : GetCVLOAD()) {
            al.add(loads.value);
        }
        return al;
    }

    @RequestMapping(value = "/show/cpu/fans", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getFanshow() {
        ArrayList<Double> al = new ArrayList<>();
        for (final Fan fan : GetCVFAN()) {
            al.add(fan.value);
        }

        return al;
    }

    @RequestMapping(value = "/show/nzos/fanspeed", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getFanSpeed() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentFanSpeed());
        return al;
    }

    @RequestMapping(value = "/show/nzos/liquidtemp", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getLiquidTemp() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentLiquidTemp());
        return al;
    }

    @RequestMapping(value = "/show/nzos/mainshow", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getCriticalVariables() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentTemperature());
        al.add(GetCurrentFanSpeed());
        al.add(GetCurrentLiquidTemp());
        return al;
    }


    @RequestMapping(value = "/show/nzos/dumpsettings", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getVariables() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentSetFanSpeed());
        al.add(GetCurrentSetPumpSpeed());
        al.add(GetCurrentProtocolVer());
        al.add(GetCurrentSafeCode());
        return al;
    }

    @RequestMapping(value = "/show/nzos/colormode", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getColorMode() {

        ArrayList<Long> aMODE = new ArrayList<>();
        aMODE.add(GetCurrentColorMode());
        return aMODE;
    }

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
        a0.add(Long.valueOf(dump[0]));
        a0.add(Long.valueOf(dump[1]));
        a0.add(Long.valueOf(dump[2]));
        a1.add(Long.valueOf(dump[3]));
        a1.add(Long.valueOf(dump[4]));
        a1.add(Long.valueOf(dump[5]));
        a2.add(Long.valueOf(dump[6]));
        a2.add(Long.valueOf(dump[7]));
        a2.add(Long.valueOf(dump[8]));
        a3.add(Long.valueOf(dump[9]));
        a3.add(Long.valueOf(dump[10]));
        a3.add(Long.valueOf(dump[11]));
        a4.add(Long.valueOf(dump[12]));
        a4.add(Long.valueOf(dump[13]));
        a4.add(Long.valueOf(dump[14]));
        a5.add(Long.valueOf(dump[15]));
        a5.add(Long.valueOf(dump[16]));
        a5.add(Long.valueOf(dump[17]));
        a6.add(Long.valueOf(dump[18]));
        a6.add(Long.valueOf(dump[19]));
        a6.add(Long.valueOf(dump[20]));
        a7.add(Long.valueOf(dump[21]));
        a7.add(Long.valueOf(dump[22]));
        a7.add(Long.valueOf(dump[23]));
        a8.add(Long.valueOf(dump[24]));
        a8.add(Long.valueOf(dump[25]));
        a8.add(Long.valueOf(dump[26]));
        result.add(a0);
        result.add(a1);
        result.add(a2);
        result.add(a3);
        result.add(a4);
        result.add(a5);
        result.add(a6);
        result.add(a7);
        result.add(a8);
        return result;
    }
}