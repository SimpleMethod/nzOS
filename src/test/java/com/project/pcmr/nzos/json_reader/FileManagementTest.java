package com.project.pcmr.nzos.json_reader;

import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.matchers.JUnitMatchers.containsString;

class FileManagementTest {
    @Rule
    public ExpectedException exceptions = ExpectedException.none();


    @Test
    void readingFile() {
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        Long result;
        String Input[] = {"main.nzprofile", "nzreal_class_version"};
        result = UnitTest1.ReadingFile(Input[0], Input[1]);
        System.out.println("Result: " + result);
        int result1;
        if (result > 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false : "The value must not be less than zero.";
        }
        assertEquals(1, result1);

    }

    @Test
    void readingFile1() {
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        Long result;
        String Input[] = {"main.nzprofile", "pump_settings", "100_degrees"};
        result = UnitTest1.ReadingFile(Input[0], Input[1], Input[2]);
        System.out.println("Result: " + result);
        int result1;
        if (result > 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false : "The value must not be less than zero.";
        }
        assertEquals(1, result1);
    }

    @Test
    void readingFile2() {
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        Long result;
        String Input[] = {"main.nzprofile", "color_settings", "color_0", "color_G"};
        result = UnitTest1.ReadingFile(Input[0], Input[1], Input[2], Input[3]);
        System.out.println("Result: " + result);

        int result1;
        if (result >= 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false : "The value must not be less than zero.";
        }
        assertEquals(1, result1);
    }

    @Test
    void writingFile() {
        int testResult = 1;
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        JSONObject json = new JSONObject();
        json.put("nzOS", testResult);
        String Input[] = {"main.nzprofile.test.test", "nzOS"};
        exceptions.expect(IOException.class);
        exceptions.expectMessage(containsString("IO exception"));
        UnitTest1.WritingFile(Input[0], json);
        Long result = UnitTest1.ReadingFile(Input[0], Input[1]);
        assertEquals(Long.valueOf(testResult), result);

    }

    @Test
    void writingFile1() {
        Long testResult = (long)1;
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        String Input[] = {"main.nzprofile.test.test", "nzOS"};
        exceptions.expect(IOException.class);
        exceptions.expectMessage(containsString("IO exception"));
        UnitTest1.WritingFile(Input[0],Input[1], testResult);
        Long result = UnitTest1.ReadingFile(Input[0], Input[1]);
        assertEquals(Long.valueOf(testResult), result);
    }

    @Test
    void writingFile2() {
        Long testResult = (long)1;
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        JSONObject json = new JSONObject();
        json.put("nzOS", testResult);
        String Input[] = {"main.nzprofile.test", "fan_settings","100_degrees"};
        exceptions.expect(IOException.class);
        exceptions.expectMessage(containsString("IO exception"));
        UnitTest1.WritingFile(Input[0], Input[1],Input[2], testResult);
        Long result = UnitTest1.ReadingFile(Input[0], Input[1],Input[2]);
        assertEquals(Long.valueOf(testResult), result);
    }

    @Test
    void writingFile3() {
        Long testResult = (long)1;
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        JSONObject json = new JSONObject();
        json.put("nzOS", testResult);
        String Input[] = {"main.nzprofile.test", "color_settings","color_0","color_G"};
        exceptions.expect(IOException.class);
        exceptions.expectMessage(containsString("IO exception"));
        UnitTest1.WritingFile(Input[0], Input[1],Input[2],Input[3], testResult);
        Long result = UnitTest1.ReadingFile(Input[0], Input[1],Input[2],Input[3]);
        assertEquals(Long.valueOf(testResult), result);
    }

    @Test
    void colorArray() {
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        String Input[] = {"main.nzprofile.test"};
        assertNotNull(UnitTest1.ColorArray(Input[0]));
    }

    @Test
    void arrayToList() {
        exceptions.expect(ArithmeticException.class);
        exceptions.expectMessage(containsString("Arithmetic exception"));
        FileManagement<Long> UnitTest1 = new FileManagement<>();
        String Input[] = {"main.nzprofile.test", "fan_settings","_degrees"};
        UnitTest1.ArrayToList(Input[0],Input[1],Input[2]);
    }
}