package com.project.pcmr.nzos.management_control;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.project.pcmr.nzos.data_base.CurrentValue;
import com.project.pcmr.nzos.json_reader.FileManagement;
import com.project.pcmr.nzos.usb_api.Api;

import java.awt.*;
import java.util.List;


public class ApiManagment extends Api implements InterfaceApiManagment {


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

        //  for (int j = 0; j < PUMP.length; j++) {
        //     System.out.format("%02X ", PUMP[j]);
        //  }
        //  System.out.println();
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

        // for (int j = 0; j < FAN.length; j++) {
        //     System.out.format(" %02X ", FAN[j]);
        // }
        // System.out.println();
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
        Components components = JSensors.get.components();
        List<Cpu> CPU = components.cpus;
        List<Temperature> temps = GetTEMPS();
        if (CPU != null) {
            for (final Cpu cpu : CPU) {
                if (cpu.sensors != null) {
                    temps = cpu.sensors.temperatures;
                }
            }
        }
        SetTEMPS(temps);
        return temps;
    }

    /**
     * Metoda służaca do zwracania temperatury CPU.
     *
     * @return Zwraca wartość zawierającą uśrednioną temperaturę procesora.
     */
    public Double GetCpuTemp() {
        List<Temperature> temperatures = GetCpuTemps();
        Temperature temp = temperatures.get(temperatures.size() - 1);
        CV.CURRENT_TEMPERATURE = temp.value.longValue();
        CV.CURRENT_FAN_SPEED=Long.valueOf(GetFanSpeed());
        CV.CURRENT_LIQUID_TEMPERATURE=Long.valueOf(GetLiquidTemp());

        return temp.value;
    }

    /**
     * Metoda służaca monitorowania pracy nzOS.
     */
    public void TheardHelper() {
        Long MathTemp = Math.round(GetCpuTemp() / 10.0) * 10;
        Long ResultFanSpeed, ResultPumpSpeed;
        if (MathTemp <= 0) {
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

        //System.out.println("Temp:" + GetCpuTemp() + " Fan speed:" + ResultFanSpeed + " Pump speed:" + ResultPumpSpeed);
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
            e.printStackTrace();
        }
    }
}

