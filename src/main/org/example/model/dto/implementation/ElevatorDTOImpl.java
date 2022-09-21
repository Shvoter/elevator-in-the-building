package org.example.model.dto.implementation;

import org.example.model.implementation.Direction;
import org.example.model.Elevator;
import org.example.model.Passenger;
import org.example.model.dto.ElevatorDTO;
import org.example.model.dto.PassengerDTO;

import java.util.ArrayList;
import java.util.List;

public class ElevatorDTOImpl implements ElevatorDTO {

    int sizeOfElevator;
    int currentNumberOfFloor;
    Direction direction;
    List<PassengerDTO> passengerDTOS;
    public ElevatorDTOImpl(Elevator elevator) {
        this.sizeOfElevator = elevator.getSizeOfElevator();
        this.currentNumberOfFloor = elevator.getCurrentNumberOfFloor();
        this.direction = elevator.getDirection();
        this.passengerDTOS =  new ArrayList<>();
        createDTOOfPassengers(elevator.getPassengers());
    }

    @Override
    public int getSizeOfElevator() {
        return sizeOfElevator;
    }

    @Override
    public int getCurrentNumberOfFloor() {
        return currentNumberOfFloor;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public List<PassengerDTO> getPassengerDTOS() {
        return passengerDTOS;
    }

    private void createDTOOfPassengers(List<Passenger> passengers) {
        for (Passenger passenger : passengers) {
            passengerDTOS.add(new PassengerDTOImpl(passenger));
        }
    }
}
