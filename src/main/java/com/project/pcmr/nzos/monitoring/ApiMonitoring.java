package com.project.pcmr.nzos.monitoring;

import com.project.pcmr.nzos.management_control.ApiManagment;

import java.util.Timer;
import java.util.TimerTask;

public class ApiMonitoring implements InterfaceApiMonitoring {

    public static class ReadingTimer extends TimerTask {
        ApiManagment Api = new ApiManagment();
        public void run() {
            Api.TheardHelper();
            Api.ReadDataFromDevice(17);
        }
    }

    public void MonitoringTemperature() {
        Timer timer = new Timer();
        timer.schedule(new ReadingTimer(), 1000, 1000);
    }


}
