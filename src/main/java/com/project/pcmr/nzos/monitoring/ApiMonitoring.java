package com.project.pcmr.nzos.monitoring;

import com.project.pcmr.nzos.management_control.ApiManagment;
import org.apache.commons.lang3.SystemUtils;
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
            Api.readDataFromDevice(17);
        }
    }

    public void monitoringTemperature() {
        logger.info("The thread has been launched");
        if (isAdmin() && SystemUtils.IS_OS_WINDOWS) {
            Timer timer = new Timer();
            timer.schedule(new ReadingTimer(), 1000, 1000);
        } else {
            logger.error("The user without administrator rights cannot use the application or the application has been launched on a different system than Windows.");
        }
    }

}
