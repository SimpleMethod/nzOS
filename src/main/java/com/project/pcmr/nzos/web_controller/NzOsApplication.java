package com.project.pcmr.nzos.web_controller;

import com.project.pcmr.nzos.monitoring.ApiMonitoring;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.OutputStream;
import java.io.PrintStream;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class NzOsApplication {
    private static final Logger logger = LogManager.getLogger(NzOsApplication.class);

    /**
     * Metoda otwieracją przeglądarkę.
     * @throws Exception Wyjątek podczas otwierania przeglądarki.
     */
    private static void openUrl() throws Exception {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c start ", " http://127.0.0.1:8090/");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        p.exitValue();
        p.destroy();
    }






    /**
     * Główna metoda.
     * @param args argumenty wywołania.
     */
    public static void main(String[] args) {
        PrintStream originalStream = System.out;
        PrintStream dummyStream = new PrintStream(new OutputStream(){
            public void write(int b) {

            }
        });
        System.setOut(dummyStream);
        SpringApplication.run(NzOsApplication.class, args);
        ApiMonitoring start = new ApiMonitoring();
        start.startTheard();
        try {
            openUrl();

        } catch (Exception e) {
            logger.info("Problem with opening a web browser: "+e);
        }

    }
}
