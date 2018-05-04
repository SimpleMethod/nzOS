package com.project.pcmr.nzos.usb_api;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.usb4java.LibUsbException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.matchers.JUnitMatchers.containsString;

class ApiTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();


    @Test
    void writeToDevice() {
        exceptions.expect(LibUsbException.class);
        exceptions.expectMessage(containsString("Library exception"));
        Api UnitTest1 = new Api();
        byte[] test={0x02, 0x4d, 0x00, 0x00, 0x64};
        UnitTest1.WriteToDevice(test);
    }

    @Test
    void readDataFromDevice() {
        Api UnitTest1 = new Api();
        int variable=17;
        assertNotNull(UnitTest1.ReadDataFromDevice(variable));
    }

    @Test
    void getLiquidTemp() {
        Api UnitTest1 = new Api();
        UnitTest1.ReadDataFromDevice(17);
        int result = UnitTest1.GetLiquidTemp();
        System.out.println("Result: " +result);
        int result1;
        if (result > 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false: "The value must not be less than zero.";
        }
        assertEquals(1, result1);
        assertNotNull(UnitTest1.GetFanSpeed());
    }

    @Test
    void getFanSpeed() {
        Api UnitTest1 = new Api();
        UnitTest1.ReadDataFromDevice(17);
        int result = UnitTest1.GetFanSpeed();
        System.out.println("Result: " +result);
        int result1;
        if (result > 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false: "The value must not be less than zero.";
        }
        assertEquals(1, result1);
        assertNotNull(UnitTest1.GetFanSpeed());
    }


}