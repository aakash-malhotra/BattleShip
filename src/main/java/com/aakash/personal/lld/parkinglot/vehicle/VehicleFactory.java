package com.aakash.personal.lld.parkinglot.vehicle;

import org.springframework.stereotype.Service;

@Service
public class VehicleFactory {
    private String vehicleNumber;
    private String colour;
    private VehicleType vehicleType;

    public VehicleFactory setVehicleNumber(String number) {
        this.vehicleNumber = number;
        return this;
    }

    public VehicleFactory setVehicleColour(String colour) {
        this.colour = colour;
        return this;
    }

    public VehicleFactory setVehicleType(String type) {
        this.vehicleType = VehicleType.valueOf(type.toUpperCase());
        return this;
    }

    public Vehicle build() {
        return new Vehicle(vehicleNumber, colour, vehicleType);
    }
}
