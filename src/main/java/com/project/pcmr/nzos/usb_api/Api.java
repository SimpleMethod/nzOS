package com.project.pcmr.nzos.usb_api;

import com.project.pcmr.nzos.data_base.CurrentValue;
import com.project.pcmr.nzos.data_base.PreDataBase;
import org.usb4java.BufferUtils;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class Api extends PreDataBase implements InterfaceAPI {
    public byte[] READINDUMP = new byte[17];
    /**
     * Metoda służąca do wysłania bajtów.
     *
     * @param Data Dane do wysłania do urządzenia.
     */
    public void WriteToDevice(byte[] Data) {
        int result = LibUsb.init(null);
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Libusb cannot be initiated.", result);
        }
        // Otwarte urządzenie.
        DeviceHandle handle = LibUsb.openDeviceWithVidPid(null, GetVENDOR_ID(), GetPRODUCT_ID());
        if (handle == null) {
            System.err.println("No test device found.");
            System.exit(1);
        }
        // Próba synchronizacji interfejsu.
        result = LibUsb.claimInterface(handle, GetINTERFACE());
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("The interface could not be synchronized..", result);
        }
        // Wysyłanie danych.
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(Data.length);
        buffer.put(Data);
        IntBuffer transferred = BufferUtils.allocateIntBuffer();
        result = LibUsb.bulkTransfer(handle, GetOUT_ENDPOINT(), buffer, transferred, GetTIMEOUT());
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Problem with sending information. \n", result);
        }
       // System.out.println("Przesłano: " + transferred.get() + " bajtów.");
        result = LibUsb.releaseInterface(handle, GetINTERFACE());
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("The interface could not be released..", result);
        }
        LibUsb.close(handle);
        // Zwolnienie biblioteki.
        LibUsb.exit(null);
    }

    /**
     * Metoda służąca do odbieranie danych.
     *
     * @param size Rozmiar ramki do odczytu.
     * @return Zwraca odebrane bajty.
     */
    public ByteBuffer ReadDataFromDevice(int size) {
        int result = LibUsb.init(null);
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Libusb cannot be initiated.", result);
        }
        // Otwarte urządzenie.
        DeviceHandle handle = LibUsb.openDeviceWithVidPid(null, GetVENDOR_ID(), GetPRODUCT_ID());
        if (handle == null) {
            System.err.println("No test device found.");
            System.exit(1);
        }
        // Próba synchronizacji interfejsu.
        result = LibUsb.claimInterface(handle, GetINTERFACE());
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("The interface could not be synchronized..", result);
        }
        // Odbieranie danych.
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(size).order(ByteOrder.LITTLE_ENDIAN);
        IntBuffer transferred = BufferUtils.allocateIntBuffer();
        result = LibUsb.bulkTransfer(handle, GetIN_ENDPOINT(), buffer, transferred, GetTIMEOUT());
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Problem with receiving information. \n", result);
        }
        buffer.rewind();
        buffer.get(READINDUMP, 0, 17);
        SetREADING_DUMP(READINDUMP);
        OUT_DUMP=READINDUMP;
        result = LibUsb.releaseInterface(handle, GetINTERFACE());
        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("The interface could not be released..", result);
        }
        // Zamknięcie urządzenia.
        LibUsb.close(handle);
        // Zwolnienie biblioteki.
        LibUsb.exit(null);
        return buffer;
    }

    /**
     * Metoda służąca do zwracania temperatury płynu.
     *
     * @return Zwraca temp. płynu.
     */
    public int GetLiquidTemp() {
        READINDUMP = OUT_DUMP;
        return READINDUMP[1];
    }

    /**
     * Metoda służąca do zwracania wartości obrotów wentylatorów.
     *
     * @return Zwraca wartości obrotów wentylatorów.
     */
    public int GetFanSpeed() {
        READINDUMP = OUT_DUMP;
        byte[] temp = READINDUMP;
        return (temp[3] & 0xFF) * (temp[10] & 0xFF) * (temp[14] & 0xFF);
    }
}