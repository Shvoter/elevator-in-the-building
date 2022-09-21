package org.example.model.dto;

import org.example.model.implementation.Direction;

import java.util.List;

public interface ElevatorDTO {

    int getSizeOfElevator();

    int getCurrentNumberOfFloor();

    Direction getDirection();

    List<PassengerDTO> getPassengerDTOS();
}
