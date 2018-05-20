package com.project.pcmr.nzos.usb_api;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.usb4java.LibUsbException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ApiTest {

    @Rule
    public final ExpectedException exceptions = ExpectedException.none();


    @Test
    void writeToDevice() {
        exceptions.expect(LibUsbException.class);
        exceptions.expectMessage(("Library exception"));
        Api UnitTest1 = new Api();
        byte[] test={0x02, 0x4d, 0x00, 0x00, 0x64};
        UnitTest1.writeToDevice(test);
    }

    @Test
    void readDataFromDevice() {
        Api UnitTest1 = new Api();
        int variable=17;
        assertNotNull(UnitTest1.readDataFromDevice(variable));
    }

    @Test
    void getLiquidTemp() {
        Api UnitTest1 = new Api();
        UnitTest1.readDataFromDevice(17);
        long result = UnitTest1.getLiquidTemp();
        System.out.println("Result: " +result);
        int result1;
        if (result > 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false: "The value must not be less than zero.";
        }
        assertEquals(1, result1);
        assertNotNull(UnitTest1.getFanSpeed());
    }

    @Test
    void getFanSpeed() {
        Api UnitTest1 = new Api();
        UnitTest1.readDataFromDevice(17);
        long result = UnitTest1.getFanSpeed();
        System.out.println("Result: " +result);
        int result1;
        if (result > 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false: "The value must not be less than zero.";
        }
        assertEquals(1, result1);
        assertNotNull(UnitTest1.getFanSpeed());
    }


}