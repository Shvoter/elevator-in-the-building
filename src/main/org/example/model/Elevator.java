package org.example.model;

import org.example.model.implementation.Direction;

import java.util.List;

public interface Elevator extends AbilityToHavePassengers {

    int getSizeOfElevator();

    int getCurrentNumberOfFloor();

    void setCurrentNumberOfFloor(int numberOfFloor);

    int getNumberOfFreePlaceInElevator();

    List<Passenger> getAndDeletePassengersToMoveToFloor();

    Direction getDirection();

    void setDirection(Direction direction);

    void move();
}
