package com.project.pcmr.nzos.json_reader;

import com.project.pcmr.nzos.data_base.PreDataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import java.util.ArrayList;
import java.util.List;

public class FileManagement<T> implements InterfaceFileManagment<T> {
    private static Logger logger = LogManager.getLogger(FileManagement.class);
    public static PreDataBase pre = new PreDataBase();
    /**
     * Zmienna przechowująca scieżkę do katalogu systemowego.
     **/
    final static String DIR = System.getProperty("user.dir");
    JSONParser parser = new JSONParser();

    /**
     * Metoda służaca do czytania pojedynczych obiektów.
     *
     * @param FILENAME Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa pojedynczego obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    @Override
    public T ReadingFile(String FILENAME, String OBJECT) {
        T name = null;
        try {
            Object obj = parser.parse(new FileReader(DIR + "\\" + FILENAME));
            JSONObject jsonObject = (JSONObject) obj;
            name = (T) jsonObject.get(OBJECT);

        } catch (Exception e) {
            logger.error("Problem with opening the file or no value in the file: " + e);
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Metoda służaca do czytania obiektu w obiekcie.
     *
     * @param FILENAME  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do odczytu.
     * @param ALTOBJECT Nazwa obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    @Override
    public T ReadingFile(String FILENAME, String OBJECT, String ALTOBJECT) {
        T name = null;
        try {

            Object obj = parser.parse(new FileReader(DIR + "\\" + FILENAME));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1 = (JSONObject) jsonObject.get(OBJECT);
            name = (T) jsonObject1.get(ALTOBJECT);

        } catch (Exception e) {
            logger.error("Problem with opening the file or no value in the file: " + e);
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Metoda służaca do czytania obiektu w obiekcie w obiekcie (Obiekto incepcja).
     *
     * @param FILENAME     Nazwa pliku do odczytu.
     * @param OBJECT       Nazwa obiektu do odczytu.
     * @param ALTOBJECT    Nazwa obiektu do odczytu.
     * @param ALTALTOBJECT Nazwa obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    @Override
    public T ReadingFile(String FILENAME, String OBJECT, String ALTOBJECT, String ALTALTOBJECT) {
        T name = null;
        try {

            Object obj = parser.parse(new FileReader(DIR + "\\" + FILENAME));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1 = (JSONObject) jsonObject.get(OBJECT);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get(ALTOBJECT);
            name = (T) jsonObject2.get(ALTALTOBJECT);

        } catch (Exception e) {
            logger.error("Problem with opening the file or no value in the file: " + e);
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Metoda służaca do zapisania obiektu.
     *
     * @param FILENAME Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa obiektu do zapisu.
     * @param VALUE    Wartość do zapisu.
     */
    @Override
    public void WritingFile(String FILENAME, String OBJECT, T VALUE) {
        try {
            Object obj = parser.parse(new FileReader(DIR + "\\" + FILENAME));
            JSONObject jsonObject = (JSONObject) obj;
            jsonObject.put(OBJECT, VALUE);
            WritingFile(FILENAME, jsonObject);
        } catch (Exception e) {
            logger.error("File saving problem or no search value in the file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda służaca do zapisania obiektu w obiekcie.
     *
     * @param FILENAME  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do zapisu.
     * @param ALTOBJECT Nazwa obiektu do odczytu.
     * @param VALUE     Wartość do zapisu.
     */
    @Override
    public void WritingFile(String FILENAME, String OBJECT, String ALTOBJECT, T VALUE) {
        try {
            Object obj = parser.parse(new FileReader(DIR + "\\" + FILENAME));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1 = (JSONObject) jsonObject.get(OBJECT);
            jsonObject1.put(ALTOBJECT, VALUE);
            WritingFile(FILENAME, jsonObject);
        } catch (Exception e) {
            logger.error("File saving problem or no search value in the file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda służaca do zapisania obiektu w obiekcie w obiekcie.
     *
     * @param FILENAME     Nazwa pliku do odczytu.
     * @param OBJECT       Nazwa obiektu do zapisu.
     * @param ALTOBJECT    Nazwa obiektu do odczytu.
     * @param ALTALTOBJECT Nazwa obiektu do odczytu.
     * @param VALUE        Wartość do zapisu.
     */
    @Override
    public void WritingFile(String FILENAME, String OBJECT, String ALTOBJECT, String ALTALTOBJECT, T VALUE) {
        try {
            Object obj = parser.parse(new FileReader(DIR + "\\" + FILENAME));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1 = (JSONObject) jsonObject.get(OBJECT);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get(ALTOBJECT);
            jsonObject2.put(ALTALTOBJECT, VALUE);
            WritingFile(FILENAME, jsonObject);

        } catch (Exception e) {
            logger.error("File saving problem or no search value in the file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda służaca do zapisu pliku.
     *
     * @param FILENAME    Nazwa pliku do odczytu.
     * @param JSON_OBJECT Wskaźnik na obiekt Json.
     */
    @Override
    public void WritingFile(String FILENAME, JSONObject JSON_OBJECT) {
        try {
            FileWriter file = new FileWriter(DIR + "\\" + FILENAME);
            try {
                file.write(JSON_OBJECT.toJSONString());

            } catch (Exception e) {
                logger.error("File saving problem or no search value in the file: " + e);
                e.printStackTrace();
            } finally {
                file.flush();
                file.close();
            }
        } catch (Exception e) {
            logger.error("Problem with creating a file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda służaca do zapisu pliku.
     *
     * @param FILENAME    Nazwa pliku do odczytu.
     * @param TEXT Tekst do zapisu.
     */
    public void WrittingFile(String FILENAME,  String TEXT)
    {
        final  String DIR = System.getProperty("user.dir");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(DIR + "\\" + FILENAME), "utf-8"))) {
            writer.write(TEXT);
        }
        catch (Exception e)
        {
            logger.error("Problem with creating a file: " + e);
            pre.ERRORS.add("Problem with creating a file");
        }
    }

    /**
     * Metoda służąca do odczytu barw i zapisu jej do tablicy.
     *
     * @param FILENAME Nazwa pliku do odczytu.
     */
    public byte[] ColorArray(String FILENAME) {
        byte[] CALLBACK = new byte[27];
        ByteBuffer byteBuffer = ByteBuffer.allocate(216);
        LongBuffer intBuffer = byteBuffer.asLongBuffer();
        String[] temp = {"G", "R", "B"};
        for (int i = 0; i < 9; i++) {
            for (String s : temp) {
                intBuffer.put((Long) ReadingFile(FILENAME, "color_settings", "color_" + i, "color_" + s));
            }
        }
        byte[] array = byteBuffer.array();
        int z = 0;
        for (int j = 7; j <= 216; j = j + 8) {
            CALLBACK[z] = array[j];
            z++;
        }
        return CALLBACK;
    }

    /**
     * Metoda służaca do zamiany tablicy na listę.
     *
     * @param FILENAME  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do pobrania.
     * @param ALTOBJECT Nazwa obiektu do pobrania.
     * @return Lista z obiektami.
     */
    public List<T> ArrayToList(String FILENAME, String OBJECT, String ALTOBJECT) {
        List<T> List = new ArrayList();
        String AltObject;

        for (int i = 0; i <= 100; i = i + 10) {
            AltObject = i + ALTOBJECT;
            List.add(ReadingFile(FILENAME, OBJECT, AltObject));
        }

        return List;
    }
}
