package com.aakash.personal.lld.parkinglot;

import com.aakash.personal.lld.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingLot implements CommandLineRunner {

    @Autowired
    ParkingService service;
    public static void main(String []args) {
        SpringApplication.run(ParkingLot.class);
    }



    @Override
    public void run(String... args) throws Exception {
        service.startUp();
    }
}
