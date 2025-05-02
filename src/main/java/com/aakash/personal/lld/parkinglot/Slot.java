package com.aakash.personal.lld.parkinglot;

import com.aakash.personal.lld.parkinglot.vehicle.Vehicle;
import com.aakash.personal.lld.parkinglot.vehicle.VehicleType;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Slot {
    @NonNull private Integer slotNo;
    @NonNull private VehicleType typeOfSlot;
    @NonNull private Integer buildingNo;
    @NonNull private Integer LevelNo;
    private Vehicle vehicle;
}
