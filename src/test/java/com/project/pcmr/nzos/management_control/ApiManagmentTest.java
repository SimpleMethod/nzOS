package com.project.pcmr.nzos.management_control;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.matchers.JUnitMatchers.containsString;

class ApiManagmentTest {
    @Rule
    public ExpectedException exceptions = ExpectedException.none();


    @Test
    void getCpuTemp() {
        ApiManagment UnitTest1 = new ApiManagment();

        double result = UnitTest1.GetCpuTemp();
        int result1;
        if (result > 0) {
            result1 = 1;
        } else {
            result1 = 0;
            assert false : "The value must not be less than zero.";
        }
        assertEquals(1, result1);
        assertNotNull(UnitTest1.GetCpuTemp());
    }

    @Test
    void getCpuTemps() {
        ApiManagment UnitTest1 = new ApiManagment();
        assertNotNull(UnitTest1.GetCpuTemps());
    }

    @Test
    void changingPumpSpeed() {
        exceptions.expect(IllegalAccessError.class);
        exceptions.expectMessage(containsString("Library exception"));
        ApiManagment UnitTest1 = new ApiManagment();
        Long test=100L;
        UnitTest1.ChangingPumpSpeed(test);
    }

    @Test
    void changingFanSpeed() {
        exceptions.expect(IllegalAccessError.class);
        exceptions.expectMessage(containsString("Library exception"));
        ApiManagment UnitTest1 = new ApiManagment();
       Long test=100L;
        UnitTest1.ChangingFanSpeed(test);
    }

    @Test
    void changingColor() {
        exceptions.expect(IllegalAccessError.class);
        exceptions.expectMessage(containsString("IO exception"));
        ApiManagment UnitTest1 = new ApiManagment();
        int test=12;
        UnitTest1.ChangingColor(test);
    }

    @Test
    void theardHelper() {
        exceptions.expect(IllegalAccessError.class);
        exceptions.expectMessage(containsString("The temperature can not be less than zero!"));
        ApiManagment UnitTest1 = new ApiManagment();
        UnitTest1.TheardHelper();
    }
}