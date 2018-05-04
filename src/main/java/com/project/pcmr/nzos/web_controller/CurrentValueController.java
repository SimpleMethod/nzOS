package com.project.pcmr.nzos.web_controller;

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
    public String test()
    {
        return "<div id=\"d1\" style=\"width:100%; text-align:center\"><img id=\"d2\" src=\"http://seriouscat.com/serious_cat.jpg\"/></div>";
    }


    @RequestMapping("/update/fanspeed/{value}")
    public void updateValue(@PathVariable String value)
    {
        System.out.println(value);
    }


    @RequestMapping(value = "currentmaininformation", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getCriticalVariables() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentTemperature());
        al.add(GetCurrentFanSpeed());
        al.add(GetCurrentLiquidTemp());
        return al;
    }


    @RequestMapping(value = "allinformation", produces = "application/json", method = RequestMethod.GET)
    public List<Long> getVariables() {
        ArrayList<Long> al = new ArrayList<>();
        al.add(GetCurrentSetFanSpeed());
        al.add(GetCurrentSetPumpSpeed());
        al.add(GetCurrentProtocolVer());
        al.add(GetCurrentSafeCode());
        return al;
    }

    @RequestMapping(value = "colorinformation", produces = "application/json", method = RequestMethod.GET)
    public List<ArrayList> getColor() {

        ArrayList<Long> a11 = new ArrayList<>();
        ArrayList<Long> aMODE = new ArrayList<>();
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

        aMODE.add(GetCurrentColorMode());
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

        aall.add(aMODE);
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
