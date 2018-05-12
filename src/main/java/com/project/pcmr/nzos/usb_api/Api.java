package com.project.pcmr.nzos.usb_api;

import com.project.pcmr.nzos.data_base.PreDataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usb4java.BufferUtils;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Arrays;

public class Api extends PreDataBase implements InterfaceAPI {
    private static Logger logger = LogManager.getLogger(Api.class);
    public byte[] READINDUMP = new byte[17];

    /**
     * Metoda służąca do wysłania bajtów.
     *
     * @param Data Dane do wysłania do urządzenia.
     */
    public void writeToDevice(byte[] Data) {
        int result = LibUsb.init(null);
        if (result != LibUsb.SUCCESS) {
            logger.error("Libusb cannot be initiated.");
            throw new LibUsbException("Libusb cannot be initiated.", result);
        }
        // Otwarte urządzenie.
        DeviceHandle handle = LibUsb.openDeviceWithVidPid(null, getVendorId(), getProductId());
        if (handle == null) {
            logger.error("No test device found.");
            System.exit(1);
        }
        // Próba synchronizacji interfejsu.
        result = LibUsb.claimInterface(handle, getINTERFACE());
        if (result != LibUsb.SUCCESS) {
            logger.error("The interface could not be synchronized.");
            throw new LibUsbException("The interface could not be synchronized.", result);
        }
        // Wysyłanie danych.
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(Data.length);
        buffer.put(Data);
        IntBuffer transferred = BufferUtils.allocateIntBuffer();
        result = LibUsb.bulkTransfer(handle, getOutEndpoint(), buffer, transferred, getTIMEOUT());
        if (result != LibUsb.SUCCESS) {
            logger.error("Problem with sending information.");
            throw new LibUsbException("Problem with sending information.", result);
        }

        logger.info(transferred.get() + " bytes were sent to the device.");

        result = LibUsb.releaseInterface(handle, getINTERFACE());
        if (result != LibUsb.SUCCESS) {
            logger.error("The interface could not be released.");
            throw new LibUsbException("The interface could not be released.", result);
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
    public ByteBuffer readDataFromDevice(int size) {
        int result = LibUsb.init(null);
        if (result != LibUsb.SUCCESS) {
            logger.error("Libusb cannot be initiated.");
            throw new LibUsbException("Libusb cannot be initiated.", result);
        }
        // Otwarte urządzenie.
        DeviceHandle handle = LibUsb.openDeviceWithVidPid(null, getVendorId(), getProductId());
        if (handle == null) {
            logger.error("No test device found.");
            System.exit(1);
        }
        // Próba synchronizacji interfejsu.
        result = LibUsb.claimInterface(handle, getINTERFACE());
        if (result != LibUsb.SUCCESS) {
            logger.error("The interface could not be synchronized.");
            throw new LibUsbException("The interface could not be synchronized..", result);
        }
        // Odbieranie danych.
        ByteBuffer buffer = BufferUtils.allocateByteBuffer(size).order(ByteOrder.LITTLE_ENDIAN);
        IntBuffer transferred = BufferUtils.allocateIntBuffer();
        result = LibUsb.bulkTransfer(handle, getInEndpoint(), buffer, transferred, getTIMEOUT());
        if (result != LibUsb.SUCCESS) {
            logger.error("Problem with receiving information.");
            throw new LibUsbException("Problem with receiving information. \n", result);
        }
        buffer.rewind();
        buffer.get(READINDUMP, 0, 17);
        setOutDump(READINDUMP);
        result = LibUsb.releaseInterface(handle, getINTERFACE());
        if (result != LibUsb.SUCCESS) {
            logger.error("The interface could not be released.");
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
    public long getLiquidTemp() {
        return READINDUMP[1];
    }

    /**
     * Metoda służąca do zwracania wartości obrotów wentylatorów.
     *
     * @return Zwraca wartości obrotów wentylatorów.
     */
    public long getFanSpeed() {
        byte[] temp = READINDUMP;
        return (temp[3] & 0xFF) * (temp[10] & 0xFF) * (temp[14] & 0xFF);
    }
}