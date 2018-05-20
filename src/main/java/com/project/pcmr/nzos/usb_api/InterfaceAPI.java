package com.project.pcmr.nzos.usb_api;

import java.nio.ByteBuffer;

/**
 * Interfejs dla klasy Api
 */
interface InterfaceAPI {
    /**
     * Deklaracja metody służącej do wysłania bajtów.
     *
     * @param data Dane do wysłania do urządzenia.
     */
    void writeToDevice(byte[] data);

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
