package com.project.pcmr.nzos.usb_api;

import java.nio.ByteBuffer;

interface InterfaceAPI {
    /**
     * Deklaracja metody służącej do wysłania bajtów.
     *
     * @param Data Dane do wysłania do urządzenia.
     */
    void writeToDevice(byte[] Data);

    /**
     * Deklaracja metody służącej do odbieranie danych.
     *
     * @param size Rozmiar ramki do odczytu.
     * @return Zwraca odebrane bajty.
     */
    ByteBuffer readDataFromDevice(int size);

    /**
     * Deklaracja metody służącej do zwracania temperatury płynu.
     *
     * @return Zwraca temp. płynu.
     */
    long getLiquidTemp();

    /**
     * Deklaracja metody służącej do zwracania wartości obrotów wentylatorów.
     *
     * @return Zwraca wartości obrotów wentylatorów.
     */
    long getFanSpeed();
}
