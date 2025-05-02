package com.aakash.personal.lld.parkinglot.vehicle;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor
public class Vehicle {
    private String vehicleNumber;
    private String colour;
    private VehicleType vehicleType;

}
