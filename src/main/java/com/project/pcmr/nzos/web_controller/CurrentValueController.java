package com.project.pcmr.nzos.web_controller;

import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
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

    @RequestMapping("/")
    public String test() {
        return "<div id=\"d1\" style=\"width:100%; text-align:center\"><img id=\"d2\" src=\"http://seriouscat.com/serious_cat.jpg\"/></div>";
    }


    @RequestMapping("/update/fanspeed/{range}/{value2}/{hashcode}")
    public void updateFansSeed(@PathVariable Long range, @PathVariable Long value2) {
        System.out.println(range ^ (range >>> 16));
        System.out.println("Pierwszy" + range + " Drugi: " + value2);
    }




    @RequestMapping(value = "/information/cpu/name", produces = "application/json", method = RequestMethod.GET)
    public List<String> getCpuNameInformation() {
        ArrayList<String> al = new ArrayList<>();
        al.add(getCVCPUNAME());
        return al;
    }


    @RequestMapping(value = "/information/cpu/temp", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getTempInformation() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentTemperature());
        return al;
    }


    @RequestMapping(value = "/information/cpu/temps", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getTempsInformation() {
        ArrayList<Double> al = new ArrayList<>();
        for (final Temperature temp : GetCVTEMPS()) {
            al.add(temp.value);
        }

        return al;
    }

    @RequestMapping(value = "/information/cpu/loads", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getLoadsInformation() {
        ArrayList<Double> al = new ArrayList<>();
        for (final Load loads : GetCVLOAD()) {
            al.add(loads.value);
        }
        return al;
    }


    @RequestMapping(value = "/information/cpu/fans", produces = "application/json", method = RequestMethod.GET)
    public List<Double> getFanInformation() {
        ArrayList<Double> al = new ArrayList<>();
        for (final Fan fan : GetCVFAN()) {
            al.add(fan.value);
        }

        return al;
    }


    @RequestMapping(value = "/information/nzos/fanspeed", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getFanSpeed() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentFanSpeed());
        return al;
    }



    @RequestMapping(value = "/information/nzos/liquidtemp", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getLiquidTemp() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentLiquidTemp());
        return al;
    }


    @RequestMapping(value = "/information/nzos/maininformation", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getCriticalVariables() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentTemperature());
        al.add(GetCurrentFanSpeed());
        al.add(GetCurrentLiquidTemp());
        return al;
    }


    @RequestMapping(value = "/information/nzos/dumpsettings", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getVariables() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentSetFanSpeed());
        al.add(GetCurrentSetPumpSpeed());
        al.add(GetCurrentProtocolVer());
        al.add(GetCurrentSafeCode());
        return al;
    }


    @RequestMapping(value = "/information/nzos/colormode", produces = "application/json", method = RequestMethod.GET)
    public List<ArrayList> getColorMode() {

        ArrayList<Long> aMODE = new ArrayList<>();
        aMODE.add(GetCurrentColorMode());
        ArrayList<ArrayList> aall = new ArrayList<>();
        aall.add(aMODE);
        return aall;
    }

    @RequestMapping(value = "/information/nzos/colorarray", produces = "application/json", method = RequestMethod.GET)
    public List<ArrayList> getColor() {

        ArrayList<Long> a11 = new ArrayList<>();
        ArrayList<Long> a0 = new ArrayList<>();
        ArrayList<Long> a1 = new ArrayList<>();
        ArrayList<Long> a2 = new ArrayList<>();
        ArrayList<Long> a3 = new ArrayList<>();
        ArrayList<Long> a4 = new ArrayList<>();
        ArrayList<Long> a5 = new ArrayList<>();
        ArrayList<Long> a6 = new ArrayList<>();
        ArrayList<Long> a7 = new ArrayList<>();
        ArrayList<Long> a8 = new ArrayList<>();
        ArrayList<ArrayList> aall = new ArrayList<>();

        a11.add(GetCurrentColorMode());
        byte[] dump = GetColourPalette();
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
        aall.add(a0);
        aall.add(a1);
        aall.add(a2);
        aall.add(a3);
        aall.add(a4);
        aall.add(a5);
        aall.add(a6);
        aall.add(a7);
        aall.add(a8);
        return aall;
    }
}
