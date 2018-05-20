package com.project.pcmr.nzos.monitoring;

import com.project.pcmr.nzos.management_control.ApiManagment;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Klasa do obsługi wątku.
 */
public class ApiMonitoring extends  ApiManagment implements InterfaceApiMonitoring {
    private static Logger logger = LogManager.getLogger(ApiMonitoring.class);

    /**
     * Klasa do obsługi wątku.
     */
    public static class ReadingTimer extends TimerTask {
        ApiManagment Api = new ApiManagment();

        /**
         * Metoda do obsługi wątku.
         */
        public void run() {
            Api.theardHelper();
            Api.readDataFromDevice(17);
        }
    }

    /**
     * Metoda służaca do startu wątku.
     */
    public void startTheard() {
        logger.info("The thread has been launched");
        final String DIR = System.getProperty("user.dir");
        Path path = Paths.get(DIR + "\\" + getDEFAULT_FILENAME());
        if (Files.notExists(path)) {
            logger.warn("No configuration file, the default file was created.");
            String defaultSettings = "{\"color_settings\":{\"color_4\":{\"color_G\":255,\"color_R\":255,\"color_B\":255},\"color_5\":{\"color_G\":255,\"color_R\":255,\"color_B\":255},\"color_6\":{\"color_G\":255,\"color_R\":255,\"color_B\":255},\"color_7\":{\"color_G\":255,\"color_R\":255,\"color_B\":255},\"color_mode\":0,\"color_8\":{\"color_G\":255,\"color_R\":255,\"color_B\":255},\"color_0\":{\"color_G\":255,\"color_R\":255,\"color_B\":255},\"color_1\":{\"color_G\":2,\"color_R\":255,\"color_B\":255},\"color_2\":{\"color_G\":255,\"color_R\":255,\"color_B\":255},\"color_3\":{\"color_G\":255,\"color_R\":255,\"color_B\":255}},\"pump_settings\":{\"100_degrees\":100,\"50_degrees\":100,\"20_degrees\":100,\"70_degrees\":100,\"0_degrees\":100,\"90_degrees\":100,\"60_degrees\":100,\"30_degrees\":100,\"10_degrees\":100,\"40_degrees\":100,\"80_degrees\":100},\"fan_settings\":{\"100_degrees\":100,\"50_degrees\":100,\"20_degrees\":100,\"70_degrees\":100,\"0_degrees\":100,\"90_degrees\":100,\"60_degrees\":100,\"30_degrees\":100,\"10_degrees\":100,\"40_degrees\":100,\"80_degrees\":100},\"id\":1002,\"nzreal_class_version\":3,\"temperature_warning\":89, \"language_version\":0}";
            writingFile(getDEFAULT_FILENAME(), defaultSettings);
        }
        if (isAdmin() && SystemUtils.IS_OS_WINDOWS) {
            Timer timer = new Timer();
            timer.schedule(new ReadingTimer(), 1000, 1000);
        } else {
            logger.error("The user without administrator rights cannot use the application or the application has been launched on a different system than Windows.");
        }
    }

}
