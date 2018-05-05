package com.project.pcmr.nzos.monitoring;

import com.project.pcmr.nzos.management_control.ApiManagment;
import com.project.pcmr.nzos.usb_api.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

public class ApiMonitoring implements InterfaceApiMonitoring {
    private static Logger logger = LogManager.getLogger(ApiMonitoring.class);

    public static class ReadingTimer extends TimerTask {
        ApiManagment Api = new ApiManagment();

        public void run() {
            Api.TheardHelper();
            Api.ReadDataFromDevice(17);
        }
    }

    public void MonitoringTemperature() {
        logger.info("The thread has been launched");
        if (isAdmin()) {
            Timer timer = new Timer();
            timer.schedule(new ReadingTimer(), 1000, 1000);
        }
    }


    /**
     * Metoda sprawdza, czy aplikacja została uruchomiona z prawami administratora.
     *
     * @return Zwraca prawdę w momencie, gdy aplikacja została uruchomiona z prawami administratora.
     */
    public boolean isAdmin() {
        try {
            String NTAuthority = "HKU\\S-1-5-19";
            String command = "reg query \"" + NTAuthority + "\"";
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            return (p.exitValue() == 0);
        } catch (Exception e) {
            logger.fatal("A user without administrator rights cannot use an application: " + e);
        }
        return false;
    }
}
