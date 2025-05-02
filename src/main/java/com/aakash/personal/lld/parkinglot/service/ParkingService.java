package com.aakash.personal.lld.parkinglot.service;

import com.aakash.personal.lld.parkinglot.Building;
import com.aakash.personal.lld.parkinglot.ConsoleReader;
import com.aakash.personal.lld.parkinglot.Level;
import com.aakash.personal.lld.parkinglot.Slot;
import com.aakash.personal.lld.parkinglot.vehicle.Vehicle;
import com.aakash.personal.lld.parkinglot.vehicle.VehicleFactory;
import com.aakash.personal.lld.parkinglot.vehicle.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ParkingService {

    private ConsoleReader consoleReader;
//    private List<Building> buildings;
    private List<Slot> SLOTS;

    @Autowired
    ParkingService(ConsoleReader reader){
        this.consoleReader = reader;
        SLOTS = new ArrayList<>();
    }

    public void startUp(){
        int noOfBuildings = consoleReader.readInt("Player Enter the number of Buildings: ");
//        buildings = new ArrayList<>(noOfBuildings);
        for (int i = 0; i < noOfBuildings; i++) {
            int noOfLevels = consoleReader.readInt("Player Enter the number of floors for building " + (i+1)  + ": ");
//            var levels = new HashSet<Level>(noOfLevels);
            for (int j = 0; j < noOfLevels; j++) {
                int noOfSlots = consoleReader.readInt("Player Enter the number of slots for level " + (j+1)  + " of Building " + (i+1)  + ": ");
                var slots = new HashSet<Slot>();
                for (int k = 0; k < noOfSlots; k++) {
                    String type = consoleReader.readString("Player Enter the type of vehicle to be stored for slot " + (k+1) + ": ");
                    SLOTS.add(new Slot(k, VehicleType.valueOf(type.toUpperCase()), i, j));
                }
//                levels.add(new Level(slots, noOfSlots));
            }
//            buildings.add(new Building(i, levels, noOfLevels));

            while(true) {
                System.out.println("1. park the vehicle");
                System.out.println("2. Unpark the vehicle");
                System.out.println("3. Get vehicles info if parked");
                System.out.println("4. Get All vehicles parked");
                System.out.println("5. Exit");
                int noOfSlots = consoleReader.readInt("Player Enter the option you want to select ");
                switch (noOfSlots) {
                    case 1 -> {
                        String vehicleNo = consoleReader.readString("Player Enter your vehicle number ");
                        String vehicleColour = consoleReader.readString("Player Enter your vehicle colour ");
                        String vehicleType = consoleReader.readString("Player Enter the vehicle type: CAR, TRUCK, OR BIKE");
                        parkAVehicle(vehicleNo, vehicleColour, vehicleType);
                    }
                    case 2 -> {
                        String vehicleNo = consoleReader.readString("Player Enter your vehicle number ");
                        unParkAVehicle(vehicleNo);
                    }
                    case 3 -> {
                        String vehicleNo = consoleReader.readString("Player Enter your vehicle number ");
                        getVehicleInfoIfParked(vehicleNo);
                    }
                    case 4 -> getAllVehiclesParked();
                    default -> {
                        System.exit(0);
                        break;
                    }
                }
            }
        }
    }

    public void getAllVehiclesParked() {
        SLOTS.stream().filter(s -> s.getVehicle()!=null).map(Slot::getVehicle).forEach(System.out::println);
    }

    public Vehicle getVehicleInfoIfParked(String vehicleNo) {
        Slot slot = SLOTS.stream().filter(s -> s.getVehicle() != null && s.getVehicle().getVehicleNumber().equalsIgnoreCase(vehicleNo)).findFirst().orElse(null);
        if (slot != null) {
            Vehicle vehicle = slot.getVehicle();
            System.out.println(vehicle);
            return vehicle;
        } else {
            System.out.println("No such vehicle parked");
            return null;
        }
    }

    public void parkAVehicle(String vehicleNo, String vehicleColor, String vehicleType) {
        Slot slot = SLOTS.stream().filter(s -> s.getVehicle()==null && s.getTypeOfSlot() == (VehicleType.valueOf(vehicleType.toUpperCase()))).findFirst().orElse(null);
        if(slot == null) {
            System.out.println("No SLOTS are empty at this time");
        } else {
            Vehicle vehicle = new VehicleFactory().setVehicleColour(vehicleColor).setVehicleType(vehicleType).setVehicleNumber(vehicleNo).build();
            slot.setVehicle(vehicle);
            System.out.println(vehicle + String.format(" has been parked in slot %d, level %d, building %d : ", slot.getSlotNo(), slot.getLevelNo(), slot.getBuildingNo()));
        }
    }

    public void unParkAVehicle(String vehicleNo) {
        Slot slot = SLOTS.stream().filter(s -> s.getVehicle()!=null && s.getVehicle().getVehicleNumber().equalsIgnoreCase(vehicleNo)).findFirst().orElse(null);
        if(slot == null) {
            System.out.println("No such vehicle is parked at this time");
        } else {
            Vehicle vehicle = slot.getVehicle();
            slot.setVehicle(null);
            System.out.println(vehicle + " has been un-parked");
        }
    }

}
