package com.project.pcmr.nzos.json_reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa służaca do obsługi pliku z konfiguracją.
 *
 * @param <T> typ generyczny.
 */
@SuppressWarnings("unchecked")
public class FileManagement<T> implements InterfaceFileManagment<T> {
    private static final Logger logger = LogManager.getLogger(FileManagement.class);
    /**
     * Zmienna przechowująca scieżkę do katalogu systemowego.
     **/
    final static private String DIR = System.getProperty("user.dir");


    /**
     * Metoda służąca do czytania pojedynczych obiektów.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa pojedynczego obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    @Override
    public T readingFile(String filename, String OBJECT) {
        T name = null;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(DIR + "\\" + filename));
            JSONObject jsonObject = (JSONObject) obj;
            name = (T) jsonObject.get(OBJECT);

        } catch (Exception e) {
            logger.error("Problem with opening the file or no value in the file: " + e);
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Metoda służąca do czytania obiektu w obiekcie.
     *
     * @param filename  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do odczytu.
     * @param ALTOBJECT Nazwa obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    @Override
    public T readingFile(String filename, String OBJECT, String ALTOBJECT) {
        T name = null;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(DIR + "\\" + filename));
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
     * Metoda służąca do czytania obiektu w obiekcie w obiekcie (Obiekto incepcja).
     *
     * @param filename     Nazwa pliku do odczytu.
     * @param OBJECT       Nazwa obiektu do odczytu.
     * @param ALTOBJECT    Nazwa obiektu do odczytu.
     * @param ALTALTOBJECT Nazwa obiektu do odczytu.
     * @return Zwraca wartość zapisaną w obiekcie.
     */
    @Override
    public T readingFile(String filename, String OBJECT, String ALTOBJECT, String ALTALTOBJECT) {
        T name = null;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(DIR + "\\" + filename));
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
     * Metoda służąca do zapisania obiektu.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa obiektu do zapisu.
     * @param VALUE    Wartość do zapisu.
     */
    @Override
    public void writingFile(String filename, String OBJECT, T VALUE) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(DIR + "\\" + filename));
            JSONObject jsonObject = (JSONObject) obj;
            jsonObject.put(OBJECT, VALUE);
            writingFile(filename, jsonObject);
        } catch (Exception e) {
            logger.error("File saving problem or no search value in the file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda służąca do zapisania obiektu w obiekcie.
     *
     * @param filename  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do zapisu.
     * @param ALTOBJECT Nazwa obiektu do odczytu.
     * @param VALUE     Wartość do zapisu.
     */
    @Override
    public void writingFile(String filename, String OBJECT, String ALTOBJECT, T VALUE) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(DIR + "\\" + filename));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1 = (JSONObject) jsonObject.get(OBJECT);
            jsonObject1.put(ALTOBJECT, VALUE);
            writingFile(filename, jsonObject);
        } catch (Exception e) {
            logger.error("File saving problem or no search value in the file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda służąca do zapisania obiektu w obiekcie w obiekcie.
     *
     * @param filename     Nazwa pliku do odczytu.
     * @param OBJECT       Nazwa obiektu do zapisu.
     * @param ALTOBJECT    Nazwa obiektu do odczytu.
     * @param ALTALTOBJECT Nazwa obiektu do odczytu.
     * @param VALUE        Wartość do zapisu.
     */
    @Override
    public void writingFile(String filename, String OBJECT, String ALTOBJECT, String ALTALTOBJECT, T VALUE) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(DIR + "\\" + filename));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1 = (JSONObject) jsonObject.get(OBJECT);
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get(ALTOBJECT);
            jsonObject2.put(ALTALTOBJECT, VALUE);
            writingFile(filename, jsonObject);

        } catch (Exception e) {
            logger.error("File saving problem or no search value in the file: " + e);
            e.printStackTrace();
        }
    }


    /**
     * Metoda służąca do zapisu pliku.
     *
     * @param filename    Nazwa pliku do odczytu.
     * @param JSON_OBJECT Wskaźnik na obiekt Json.
     */
    @Override
    public void writingFile(String filename, JSONObject JSON_OBJECT) {
        try {
            FileWriter file = new FileWriter(DIR + "\\" + filename);
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
     * Metoda służąca do zapisu pliku.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param TEXT     Tekst do zapisu.
     */
    public void writingFile(String filename, String TEXT) {
        final String DIR = System.getProperty("user.dir");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(DIR + "\\" + filename), "utf-8"))) {
            writer.write(TEXT);
        } catch (Exception e) {
            logger.error("Problem with creating a file: " + e);
        }
    }

    /**
     * Metoda służąca do odczytu barw i zapisu jej do tablicy.
     *
     * @param filename Nazwa pliku do odczytu.
     * @return zwraca tablicę z kolorami
     */
    public byte[] colorArray(String filename) {
        byte[] CALLBACK = new byte[27];
        ByteBuffer byteBuffer = ByteBuffer.allocate(216);
        LongBuffer intBuffer = byteBuffer.asLongBuffer();
        String[] temp = {"G", "R", "B"};
        for (int i = 0; i < 9; i++) {
            for (String s : temp) {
                intBuffer.put((Long) readingFile(filename, "color_settings", "color_" + i, "color_" + s));
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
     * Metoda zwracająca tablicę z barwami odczytaną z pliku z konfiguracją.
     *
     * @param filename Nazwa pliku do odczytu.
     * @return Zwraca listę z kolorami.
     */
    public long[] colorLongArray(String filename) {
        LongBuffer byteBuffer = LongBuffer.allocate(27);
        String[] temp = {"G", "R", "B"};
        for (int i = 0; i < 9; i++) {
            for (String s : temp) {
                byteBuffer.put((Long) readingFile(filename, "color_settings", "color_" + i, "color_" + s));
            }
        }
        return byteBuffer.array();
    }

    /**
     * Metoda służąca do zamiany tablicy na listę.
     *
     * @param filename  Nazwa pliku do odczytu.
     * @param OBJECT    Nazwa obiektu do pobrania.
     * @param ALTOBJECT Nazwa obiektu do pobrania.
     * @return Lista z obiektami.
     */
    public List<T> arrayToList(String filename, String OBJECT, String ALTOBJECT) {
        List<T> List = new ArrayList();
        String AltObject;

        for (int i = 0; i <= 100; i = i + 10) {
            AltObject = i + ALTOBJECT;
            List.add(readingFile(filename, OBJECT, AltObject));
        }

        return List;
    }

    /**
     * Metoda służąca do zamiany tablicy na listę.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT   Nazwa obiektu do pobrania.
     * @return Lista z obiektami.
     */
    public List<T> arrayToList(String filename, String OBJECT) {
        List<T> List = new ArrayList();
        for (int i = 0; i <= 100; i = i + 10) {
            List.add(readingFile(filename, OBJECT, i + "_degrees"));
        }

        return List;
    }

    /**
     * Metoda odczytująca plik z logami błędów.
     *
     * @param filename Nazwa pliku do odczytu.
     * @return Zwraca tekst w postaci linii z błędami.
     */
    public ArrayList<String> showLogFile(String filename) {
        String fileName = DIR + "\\logs\\" + filename;
        ArrayList<String> al = new ArrayList<>();

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(fileName));
            String lineText;
            while ((lineText = lineReader.readLine()) != null) {
                al.add(lineText);
            }
            lineReader.close();
        } catch (IOException ex) {
            logger.error("Problem with log reading: " + ex);
        }
        return al;
    }

    /**
     * Metoda zapisująca docelowy kolor w pliku konfiguracyjnym.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT   Obiekt do edycji.
     * @param VALUE    Wartość do edycji.
     */
    public void forceWritingFile(String filename, String OBJECT, T VALUE) {
        try {
            JSONParser parser = new JSONParser();
            String[] temp = {"G", "R", "B"};
            Object obj = parser.parse(new FileReader(DIR + "\\" + filename));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1 = (JSONObject) jsonObject.get(OBJECT);
            for (int i = 0; i < 9; i++) {
                for (String s : temp) {
                    JSONObject jsonObject2 = (JSONObject) jsonObject1.get("color_" + i);
                    jsonObject2.put("color_" + s, VALUE);
                }
            }
            writingFile(filename, jsonObject);
        } catch (Exception e) {
            logger.error("File saving problem or no search value in the file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Metoda zapisująca docelowe kolory w pliku konfiguracyjnym.
     *
     * @param filename Nazwa pliku do odczytu.
     * @param OBJECT   Obiekt do edycji.
     * @param Type     Typ koloru do edycji.
     * @param VALUE    Wartość koloru do edycji.
     */
    public void forceWritingFile(String filename, String OBJECT, String Type, T VALUE) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(DIR + "\\" + filename));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObject1 = (JSONObject) jsonObject.get(OBJECT);
            for (int i = 0; i < 9; i++) {
                JSONObject jsonObject2 = (JSONObject) jsonObject1.get("color_" + i);
                jsonObject2.put("color_" + Type, VALUE);
            }
            writingFile(filename, jsonObject);
        } catch (Exception e) {
            logger.error("File saving problem or no search value in the file: " + e);
            e.printStackTrace();
        }
    }
}
