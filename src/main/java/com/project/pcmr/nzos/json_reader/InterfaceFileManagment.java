package com.project.pcmr.nzos.json_reader;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfejs dla klasy FileManagment
 * @param <T> Generyczny typ dla interfejsu.
 */
interface InterfaceFileManagment<T> {

    /**
     * Deklaracja metody służącej do czytania pojedynczych obiektów.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa pojedynczego obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    T readingFile(String filename, String OBJECT);

    /**
     * Deklaracja metody służącej do czytania obiektu w obiekcie.
     *
     * @param filename  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do odczytu.
     * @param ALTOBJECT Nazwa obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    T readingFile(String filename, String OBJECT, String ALTOBJECT);

    /**
     * Deklaracja metody służącej do czytania obiektu w obiekcie w obiekcie (Obiekto incepcja).
     *
     * @param filename     Nazwa pliku do odczytu.
     * @param OBJECT       Nazwa obiektu do odczytu.
     * @param ALTOBJECT    Nazwa obiektu do odczytu.
     * @param ALTALTOBJECT Nazwa obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    T readingFile(String filename, String OBJECT, String ALTOBJECT, String ALTALTOBJECT);

    /**
     * Deklaracja metody służącej do zapisania obiektu.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa obiektu do zapisu.
     * @param VALUE    Wartość do zapisu.
     */
    void writingFile(String filename, String OBJECT, T VALUE);

    /**
     * Deklaracja metody służącej do zapisania obiektu w obiekcie.
     *
     * @param filename  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do zapisu.
     * @param ALTOBJECT Nazwa obiektu do odczytu.
     * @param VALUE     Wartość do zapisu.
     */
    void writingFile(String filename, String OBJECT, String ALTOBJECT, T VALUE);

    /**
     * Deklaracja metody służącej do zapisania obiektu w obiekcie w obiekcie.
     *
     * @param filename     Nazwa pliku do odczytu.
     * @param OBJECT       Nazwa obiektu do zapisu.
     * @param ALTOBJECT    Nazwa obiektu do odczytu.
     * @param ALTALTOBJECT Nazwa obiektu do odczytu.
     * @param VALUE        Wartość do zapisu.
     */
    void writingFile(String filename, String OBJECT, String ALTOBJECT, String ALTALTOBJECT, T VALUE);

    /**
     * Deklaracja metody służącej do zapisu pliku.
     *
     * @param filename    Nazwa pliku do odczytu.
     * @param JSON_OBJECT Wskaźnik na obiekt Json.
     */
    void writingFile(String filename, JSONObject JSON_OBJECT);

    /**
     * Deklaracja metody służącej do odczytu barw i zapisu jej do tablicy.
     * @param filename Nazwa pliku do odczytu.
     * @return tablica z kolorami.
     */
    byte[] colorArray(String filename);

    /**
     * Deklaracja metody służącej do zamiany tablicy na listę.
     *
     * @param filename  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do pobrania.
     * @param ALTOBJECT Nazwa obiektu do pobrania.
     * @return Lista z obiektami.
     */
    List<T> arrayToList(String filename, String OBJECT, String ALTOBJECT);


    /**
     * Deklaracja metody służącej do zamiany tablicy na listę.
     *
     * @param filename  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do pobrania.
     * @return Lista z obiektami.
     */
    List<T> arrayToList(String filename, String OBJECT);

    /**
     * Deklaracja metody służącej do zamiany tablicy na listę.
     * @param filename azwa pliku do odczytu.
     * @return  Lista z obiektami.
     */
    long[] colorLongArray(String filename);

    /**
     * Deklaracja metody służącej do odczytu kluczowych logów.
     * @param filename nazwa pliku do odczytu.
     * @return Lista z obiektami.
     */
    ArrayList<String> showLogFile(String filename);

    /**
     * Deklaracja metody zapisującej docelowy kolor w pliku konfiguracyjnym.
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT Obiekt do edycji.
     * @param VALUE Wartość do edycji.
     */
    void forceWritingFile(String filename, String OBJECT, T VALUE);

    /**
     * Deklaracja metody zapisującej docelowe kolory w pliku konfiguracyjnym.
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT Obiekt do edycji.
     * @param Type Typ koloru do edycji.
     * @param VALUE Wartość koloru do edycji.
     */
    void forceWritingFile(String filename, String OBJECT,String Type, T VALUE);



    /**
     * Metoda służaca do zapisu pliku.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param TEXT     Tekst do zapisu.
     */
     void writingFile(String filename, String TEXT);
}



