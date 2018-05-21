package com.project.pcmr.nzos.web_controller;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;


class NzOsApplicationTest {

    @Rule
    public final ExpectedException exceptions = ExpectedException.none();


    @Test
    void main() {
        exceptions.expect(IOException .class);
        exceptions.expectMessage(("IO exception"));
        return;
    }

}