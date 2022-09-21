package org.example.model.dto.implementation;

import org.example.model.Passenger;
import org.example.model.dto.PassengerDTO;

public class PassengerDTOImpl implements PassengerDTO {

    int numberOfNextFloor;

    public PassengerDTOImpl(Passenger passenger) {
        numberOfNextFloor = passenger.getNumberOfNextFloor();
    }
    @Override
    public int getNumberOfNextFloor() {
        return numberOfNextFloor;
    }
}
