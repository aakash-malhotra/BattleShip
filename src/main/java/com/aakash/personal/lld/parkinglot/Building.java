package com.aakash.personal.lld.parkinglot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Building {
    private Integer buildingNumber;
    private Set<Level> levels = new HashSet<>();
    private Integer MAX_LEVELS;
}
