package com.project.pcmr.nzos.json_reader;

import org.json.simple.JSONObject;

import java.util.List;

interface InterfaceFileManagment<T> {

    /**
     * Deklaracja metody służącej do czytania pojedynczych obiektów.
     *
     * @param FILENAME Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa pojedynczego obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    T readingFile(String FILENAME, String OBJECT);

    /**
     * Deklaracja metody służącej do czytania obiektu w obiekcie.
     *
     * @param FILENAME  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do odczytu.
     * @param ALTOBJECT Nazwa obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    T readingFile(String FILENAME, String OBJECT, String ALTOBJECT);

    /**
     * Deklaracja metody służącej do czytania obiektu w obiekcie w obiekcie (Obiekto incepcja).
     *
     * @param FILENAME     Nazwa pliku do odczytu.
     * @param OBJECT       Nazwa obiektu do odczytu.
     * @param ALTOBJECT    Nazwa obiektu do odczytu.
     * @param ALTALTOBJECT Nazwa obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    T readingFile(String FILENAME, String OBJECT, String ALTOBJECT, String ALTALTOBJECT);

    /**
     * Deklaracja metody służącej do zapisania obiektu.
     *
     * @param FILENAME Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa obiektu do zapisu.
     * @param VALUE    Wartość do zapisu.
     */
    void writingFile(String FILENAME, String OBJECT, T VALUE);

    /**
     * Deklaracja metody służącej do zapisania obiektu w obiekcie.
     *
     * @param FILENAME  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do zapisu.
     * @param ALTOBJECT Nazwa obiektu do odczytu.
     * @param VALUE     Wartość do zapisu.
     */
    void writingFile(String FILENAME, String OBJECT, String ALTOBJECT, T VALUE);

    /**
     * Deklaracja metody służącej do zapisania obiektu w obiekcie w obiekcie.
     *
     * @param FILENAME     Nazwa pliku do odczytu.
     * @param OBJECT       Nazwa obiektu do zapisu.
     * @param ALTOBJECT    Nazwa obiektu do odczytu.
     * @param ALTALTOBJECT Nazwa obiektu do odczytu.
     * @param VALUE        Wartość do zapisu.
     */
    void writingFile(String FILENAME, String OBJECT, String ALTOBJECT, String ALTALTOBJECT, T VALUE);

    /**
     * Deklaracja metody służącej do zapisu pliku.
     *
     * @param FILENAME    Nazwa pliku do odczytu.
     * @param JSON_OBJECT Wskaźnik na obiekt Json.
     */
    void writingFile(String FILENAME, JSONObject JSON_OBJECT);

    /**
     * Deklaracja metody służącej do odczytu barw i zapisu jej do tablicy.
     *
     * @param FILENAME Nazwa pliku do odczytu.
     */
    byte[] colorArray(String FILENAME);

    /**
     * Deklaracja metody służącej do zamiany tablicy na listę.
     *
     * @param FILENAME  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do pobrania.
     * @param ALTOBJECT Nazwa obiektu do pobrania.
     * @return Lista z obiektami.
     */
    List<T> arrayToList(String FILENAME, String OBJECT, String ALTOBJECT);

    /**
     * Deklaracja metody służącej do zamiany tablicy na listę.
     * @param FILENAME azwa pliku do odczytu.
     * @return  Lista z obiektami.
     */
    long[] colorLongArray(String FILENAME);

}

