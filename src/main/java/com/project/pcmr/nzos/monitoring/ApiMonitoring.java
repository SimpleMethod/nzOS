package com.project.pcmr.nzos.monitoring;

import com.project.pcmr.nzos.management_control.ApiManagment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

public class ApiMonitoring extends  ApiManagment implements InterfaceApiMonitoring {
    private static Logger logger = LogManager.getLogger(ApiMonitoring.class);

    public static class ReadingTimer extends TimerTask {
        ApiManagment Api = new ApiManagment();

        public void run() {
            Api.theardHelper();
            Api.ReadDataFromDevice(17);
        }
    }

    public void MonitoringTemperature() {
        logger.info("The thread has been launched");
        if (isAdmin()) {
            Timer timer = new Timer();
            timer.schedule(new ReadingTimer(), 1000, 1000);
        } else {
            logger.error("A user without administrator rights cannot use an application!");
        }
    }

}
