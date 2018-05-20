package com.project.pcmr.nzos.management_control;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ApiManagmentTest {
    @Rule
    public final ExpectedException exceptions = ExpectedException.none();


    @Test
    void getCpuTemp() {
        ApiManagment UnitTest1 = new ApiManagment();

        double result = UnitTest1.getCpuTemp();
        int result1;
        if (result > 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false : "The value must not be less than zero.";
        }
        assertEquals(1, result1);
        assertNotNull(UnitTest1.getCpuTemp());
    }

    @Test
    void getCpuTemps() {
        ApiManagment UnitTest1 = new ApiManagment();
        assertNotNull(UnitTest1.getCpuTemps());
    }

    @Test
    void changingPumpSpeed() {
        exceptions.expect(IllegalAccessError.class);
        exceptions.expectMessage(("Library exception"));
        ApiManagment UnitTest1 = new ApiManagment();
        Long test=100L;
        UnitTest1.changingPumpSpeed(test);
    }

    @Test
    void changingFanSpeed() {
        exceptions.expect(IllegalAccessError.class);
        exceptions.expectMessage(("Library exception"));
        ApiManagment UnitTest1 = new ApiManagment();
       Long test=100L;
        UnitTest1.changingFanSpeed(test);
    }

    @Test
    void changingColor() {
        exceptions.expect(IllegalAccessError.class);
        exceptions.expectMessage(("IO exception"));
        ApiManagment UnitTest1 = new ApiManagment();
        int test=12;
        UnitTest1.changingColor(test);
    }

    @Test
    void theardHelper() {
        exceptions.expect(IllegalAccessError.class);
        exceptions.expectMessage(("The temperature can not be less than zero!"));
        ApiManagment UnitTest1 = new ApiManagment();
        UnitTest1.theardHelper();
    }
}