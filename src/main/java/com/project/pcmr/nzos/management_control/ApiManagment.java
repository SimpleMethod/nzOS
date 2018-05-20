package com.project.pcmr.nzos.management_control;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;

import com.project.pcmr.nzos.usb_api.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Klasa służaca jako interfejs dla obsługi urządzenia.
 */
public class ApiManagment extends Api implements InterfaceApiManagment {
    private static Logger logger = LogManager.getLogger(ApiManagment.class);
    boolean firstUsage = false;
    int counterWarning = 0;

    /**
     * Metoda służaca zmiany pracy pompy.
     *
     * @param VALUE Wartość w zakresie 60-100.
     */
    public void changingPumpSpeed(Long VALUE) {
        byte[] PUMP = getPumpData();
        PUMP[4] = (byte) VALUE.intValue();
        if (VALUE >= 60 && VALUE <= 100) {
            writeToDevice(PUMP);
        }
    }

    /**
     * Metoda służaca zmiany pracy wentylatorów.
     *
     * @param value Wartość w zakresie 25-100.
     */
    public void changingFanSpeed(Long value) {
        byte[] FAN = getFanData();
        FAN[4] = (byte) value.intValue();

        if (value >= 25 && value <= 100) {
            writeToDevice(FAN);
        }

    }

    /**
     * Metoda służaca do zmiany braw.
     *
     * @param colorMode Tryb zmiany kolorów.
     */
    public void changingColor(Integer colorMode) {
        byte[] ctm = new byte[]{0x02, 0x4c, 0x00, 0x01, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        ctm[3] = COLOR_CUSTOM[colorMode];
        ctm[4] = COLOR_CUSTOM[colorMode + 1];
        byte[] CALLBACK = colorArray(getDEFAULT_FILENAME());
        for (int i = 5; i <= 31; i++) {
            ctm[i] = CALLBACK[i - 5];
        }
        writeToDevice(ctm);
    }

    /**
     * Metoda służaca do zwracania temperatury CPU.
     *
     * @return Zwraca listę zawierającą temperatury rdzeni procesora.
     */
    public List getCpuTemps() {
        return getTEMPS();
    }

    /**
     * Metoda służaca do zwracania informacji o CPU.
     */
    public void getCpuInfo() {
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus) {
                setCPUNAME(cpu.name);
                if (cpu.sensors != null) {
                    setTEMPS(cpu.sensors.temperatures);
                    setFANS(cpu.sensors.fans);
                    setLOAD(cpu.sensors.loads);
                }
            }
        }
        setTEMP(getCpuTemp().longValue());
        setFAN(getFanSpeed());
        setLIQUID(getLiquidTemp());
    }

    /**
     * Metoda służaca do zwracania temperatury CPU.
     *
     * @return Zwraca wartość zawierającą uśrednioną temperaturę procesora.
     */
    public Double getCpuTemp() {
        Temperature temp = getTEMPS().get(getTEMPS().size() - 1);
        return temp.value;
    }

    /**
     * Metoda służaca monitorowania pracy nzOS.
     */
    public void theardHelper() {
        getCpuInfo();
        if (!firstUsage) {
            changingColor((int) (long)readingFile(getDEFAULT_FILENAME(), "color_settings", "color_mode"));
        }
        firstUsage = true;
        if (getCpuTemp() > readingFile(getDEFAULT_FILENAME(), "temperature_warning") && counterWarning == 0) {
            try {
                sendNotification("nzOS", "Your CPU temperature exceded " + getCpuTemp() + "°C Prolonged use at this temperature may shorten the CPU lifespan.");
            } catch (Exception e) {
                logger.error("Message cannot be displayed");
            }

        }
        counterWarning++;
        if (counterWarning == 600) {
            counterWarning = 0;
        }
        Long MathTemp = Math.round(getCpuTemp() / 10.0) * 10;
        Long ResultFanSpeed, ResultPumpSpeed;
        if (MathTemp <= 0) {
            logger.error("The temperature can not be less than zero!");
            throw new ExceptionApiManagment("The temperature can not be less than zero!");
        }
        if (MathTemp > 100 || MathTemp == 0) {
            ResultPumpSpeed = ResultFanSpeed = 100L;
        } else {
            ResultFanSpeed = readingFile(getDEFAULT_FILENAME(), "fan_settings", MathTemp + "_degrees");
            ResultPumpSpeed = readingFile(getDEFAULT_FILENAME(), "pump_settings", MathTemp + "_degrees");
        }
        changingFanSpeed(ResultFanSpeed);
        changingPumpSpeed(ResultPumpSpeed);

        logger.info("CPU temperature:" + getCpuTemp() + "°C, fan speed:" + ResultFanSpeed + " percent, pump speed:" + ResultPumpSpeed + " percent.");
    }

    /**
     * Metoda służacado wyświetlania powiadomień.
     *
     * @param title    Tytuł powiadomienia
     * @param subtitle Treść powiadomienia
     */
    public void sendNotification(String title, String subtitle) throws Exception {
        String powershell = "[reflection.assembly]::loadwithpartialname('System.Windows.Forms');[reflection.assembly]::loadwithpartialname('System.Drawing');$notify = new-object system.windows.forms.notifyicon;$notify.icon = [System.Drawing.SystemIcons]::Shield;$notify.visible = $true;$notify.showballoontip(3,'" + title + "','" + subtitle + "',[system.windows.forms.tooltipicon]::None)";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "powershell -c \"" + powershell + "\"");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            logger.info(line);
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

