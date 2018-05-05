package com.project.pcmr.nzos.management_control;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.data_base.CurrentValue;
import com.project.pcmr.nzos.json_reader.FileManagement;
import com.project.pcmr.nzos.usb_api.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.List;


public class ApiManagment extends Api implements InterfaceApiManagment {
    private static Logger logger = LogManager.getLogger(ApiManagment.class);
    boolean firstusage = false;
    FileManagement<Long> FileM = new FileManagement<>();
    static CurrentValue CV = new CurrentValue();

    /**
     * Metoda służaca zmiany pracy pompy.
     *
     * @param VALUE Wartość w zakresie 60-100.
     */
    public void ChangingPumpSpeed(Long VALUE) {
        byte[] PUMP = GetPUMP_DATA();
        PUMP[4] = (byte) VALUE.intValue();
        if (VALUE >= 60 && VALUE <= 100) {
            WriteToDevice(PUMP);
        }

    }

    /**
     * Metoda służaca zmiany pracy wentylatorów.
     *
     * @param VALUE Wartość w zakresie 25-100.
     */
    public void ChangingFanSpeed(Long VALUE) {
        byte[] FAN = GetFAN_DATA();
        FAN[4] = (byte) VALUE.intValue();

        if (VALUE >= 25 && VALUE <= 100) {
            WriteToDevice(FAN);
        }

    }

    /**
     * Metoda służaca do zmiany braw.
     *
     * @param COLOR_MODE Tryb zmiany kolorów.
     */
    public void ChangingColor(Integer COLOR_MODE) {
        byte[] CUSTOMCOLOR = new byte[]{0x02, 0x4c, 0x00, 0x01, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        CUSTOMCOLOR[3] = COLOR_CUSTOM[COLOR_MODE];
        CUSTOMCOLOR[4] = COLOR_CUSTOM[COLOR_MODE + 1];
        byte[] CALLBACK = FileM.ColorArray(GetDEFAULT_FILENAME());
        for (int i = 5; i <= 31; i++) {
            CUSTOMCOLOR[i] = CALLBACK[i - 5];
        }
        WriteToDevice(CUSTOMCOLOR);
    }

    /**
     * Metoda służaca do zwracania temperatury CPU.
     *
     * @return Zwraca listę zawierającą temperatury rdzeni procesora.
     */
    public List GetCpuTemps() {
        SetTEMPS(CVTEMPS);
        return CVTEMPS;
    }

    /**
     * Metoda służaca do zwracania informacji o CPU.
     */
    public void GetCpuInfo() {
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus) {
                CPUNAME = cpu.name;
                if (cpu.sensors != null) {
                    CVTEMPS = cpu.sensors.temperatures;
                    CVFAN = cpu.sensors.fans;
                    CVLOAD = cpu.sensors.loads;
                }
            }
        }
        CV.CURRENT_TEMPERATURE = GetCpuTemp().longValue();
        CV.CURRENT_FAN_SPEED = Long.valueOf(GetFanSpeed());
        CV.CURRENT_LIQUID_TEMPERATURE = Long.valueOf(GetLiquidTemp());
    }

    /**
     * Metoda służaca do zwracania temperatury CPU.
     *
     * @return Zwraca wartość zawierającą uśrednioną temperaturę procesora.
     */
    public Double GetCpuTemp() {

        Temperature temp = CVTEMPS.get(CVTEMPS.size() - 1);
        return temp.value;
    }

    /**
     * Metoda służaca monitorowania pracy nzOS.
     */
    public void TheardHelper() {
        GetCpuInfo();
        if (!firstusage) {
            ChangingColor((int) (long) FileM.ReadingFile(GetDEFAULT_FILENAME(), "color_settings", "color_mode"));
        }
        firstusage = true;
        Long MathTemp = Math.round(GetCpuTemp() / 10.0) * 10;
        Long ResultFanSpeed, ResultPumpSpeed;
        if (MathTemp <= 0) {
            logger.error("The temperature can not be less than zero!");
            throw new ExceptionApiMonitoring("The temperature can not be less than zero!");
        }
        if (MathTemp > 100 || MathTemp == 0) {
            ResultPumpSpeed = ResultFanSpeed = 100L;
        } else {
            ResultFanSpeed = FileM.ReadingFile(GetDEFAULT_FILENAME(), "fan_settings", MathTemp + "_degrees");
            ResultPumpSpeed = FileM.ReadingFile(GetDEFAULT_FILENAME(), "pump_settings", MathTemp + "_degrees");
        }
        ChangingFanSpeed(ResultFanSpeed);
        ChangingPumpSpeed(ResultPumpSpeed);

        logger.info("Temp:" + GetCpuTemp() + " Fan speed:" + ResultFanSpeed + " Pump speed:" + ResultPumpSpeed + " .");

    }

    /**
     * Metoda służacado wyświetlania powiadomień.
     *
     * @param title    Tytuł powiadomienia
     * @param subtitle Treść powiadomienia
     */
    public void sendNotification(String title, String subtitle) {
        SystemTray mainTray = SystemTray.getSystemTray();
        Image trayIconImage = Toolkit.getDefaultToolkit().getImage("");
        TrayIcon mainTrayIcon = new TrayIcon(trayIconImage);
        mainTrayIcon.setImageAutoSize(true);
        try {
            mainTray.add(mainTrayIcon);
            mainTrayIcon.displayMessage(title, subtitle, TrayIcon.MessageType.INFO);
        } catch (Exception e) {
            logger.error("Problem with sending notification.");
            e.printStackTrace();
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

