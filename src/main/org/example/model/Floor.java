package org.example.model;

import org.example.model.implementation.Direction;

import java.util.List;

public interface Floor extends AbilityToHavePassengers {

    int getNumberOfFloor();

    Direction getMostCommonDirectionToMove();
    List<Passenger> getAndDeletePassengersToMoveToElevator(int numberPlaceForPassengers, Direction directionOfElevator);

    List<Direction> getNeededDirectionsOfElevator();



}
