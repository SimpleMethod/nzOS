package com.project.pcmr.nzos.web_controller;


import com.project.pcmr.nzos.monitoring.ApiMonitoring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class NzOsApplication {



    public static void main(String[] args) {
        SpringApplication.run(NzOsApplication.class, args);
        ApiMonitoring start  = new ApiMonitoring();
        start.MonitoringTemperature();

    }
}
