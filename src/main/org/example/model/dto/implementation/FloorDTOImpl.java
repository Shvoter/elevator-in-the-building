package org.example.model.dto.implementation;

import org.example.model.implementation.Direction;
import org.example.model.Floor;
import org.example.model.Passenger;
import org.example.model.dto.FloorDTO;
import org.example.model.dto.PassengerDTO;

import java.util.ArrayList;
import java.util.List;

public class FloorDTOImpl implements FloorDTO {

    int numberOfFloor;
    List<Direction> directions;
    List<PassengerDTO> passengerDTOS;


    public FloorDTOImpl(Floor floor) {
        this.numberOfFloor = floor.getNumberOfFloor();
        this.directions = floor.getNeededDirectionsOfElevator();

        this.passengerDTOS = new ArrayList<>();
        createDTOOfPassengers(floor.getPassengers());
    }

    @Override
    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    @Override
    public List<Direction> getNeededDirectionsOfElevator() {
        return directions;
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
