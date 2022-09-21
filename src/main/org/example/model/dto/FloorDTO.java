package org.example.model.dto;

import org.example.model.implementation.Direction;

import java.util.List;

public interface FloorDTO {

    int getNumberOfFloor();

    List<Direction> getNeededDirectionsOfElevator();

    List<PassengerDTO> getPassengerDTOS();



}
